package fr.miage.agents.fournisseur.fournisseur;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;
import fr.miage.agents.api.message.negociation.*;
import fr.miage.agents.api.message.production.Production;
import fr.miage.agents.api.message.recherche.Rechercher;
import fr.miage.agents.api.message.recherche.ResultatRecherche;
import fr.miage.agents.api.message.util.AppelMethodeIncorrect;
import fr.miage.agents.api.message.util.ResultatAide;
import fr.miage.agents.api.model.Categorie;
import fr.miage.agents.fournisseur.model.CompteActuel;
import fr.miage.agents.fournisseur.model.Panier;
import fr.miage.agents.fournisseur.model.Produit;
import fr.miage.agents.fournisseur.strategie.Strategie;
import fr.miage.agents.fournisseur.util.HibernateUtil;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import org.hibernate.Query;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Arthur on 06/12/2016.
 */
public class AgentFournisseurBehaviour extends Behaviour {

    Agent myAgent;
    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    HashMap<UUID, Panier> sessionPanier = new HashMap<>();
    private static float recettes = 0;

    public AgentFournisseurBehaviour(AgentFournisseur agentFournisseur) {
        this.myAgent = agentFournisseur;
    }

    public static float getRecettes() {
        return recettes;
    }

    public static void substractRecette(float prixProduction) {
        recettes -= prixProduction;
    }

    public void action() {
        ACLMessage msg = myAgent.blockingReceive(mt);
        try {
            Message m = (Message) msg.getContentObject();
            System.out.println("Fournisseur : 'Message reçu !' : "+m.type);
            switch (m.type){
                case InitierAchat:
                    InitierAchat achat = (InitierAchat) m;
                    Message resultatInitiationAchat = traitementInitierAchat(achat);
                    ACLMessage msgResponseInitiationAchat = msg.createReply();
                    try {
                        msgResponseInitiationAchat.setContentObject(resultatInitiationAchat);
                        myAgent.send(msgResponseInitiationAchat);
                        System.out.println("Fournisseur : 'J'envois une réponse !'");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case NegocierPrix:
                    NegocierPrix nego = (NegocierPrix) m;
                    Message reponseNego = traitementNegociation(nego);
                    ACLMessage msgReponseNego = msg.createReply();
                    try {
                        msgReponseNego.setContentObject(reponseNego);
                        myAgent.send(msgReponseNego);
                        System.out.println("Fournisseur : 'J'envois une réponse !'");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case FinaliserAchat:
                    FinaliserAchat fa = (FinaliserAchat) m;
                    Message reponseFinalisationAchat = traitementFinalisationAchat(fa);
                    ACLMessage msgReponseFinalisationAchat = msg.createReply();
                    try {
                        msgReponseFinalisationAchat.setContentObject(reponseFinalisationAchat);
                        myAgent.send(msgReponseFinalisationAchat);
                        System.out.println("Fournisseur : 'J'envois une réponse !'");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case AnnulerAchat:
                    AnnulerAchat aa = (AnnulerAchat) m;
                    Message reponseAnnulerAchat = traitementAnnulationAchat(aa);
                    ACLMessage msgReponseAnnulerAchat = msg.createReply();
                    try {
                        myAgent.send(msgReponseAnnulerAchat);
                        System.out.println("Fournisseur : 'J'envois une réponse !'");
                        msgReponseAnnulerAchat.setContentObject(reponseAnnulerAchat);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case Production:
                    Production prod = (Production) m;
                    if(msg.getSender().getLocalName().equals("producteur")) {
                        this.traiterProduction(prod);
                    }
                    break;
                case DemanderSession:
                    ACLMessage reply=  msg.createReply();
                    reply.setContentObject(new AppelMethodeIncorrect());
                    myAgent.send(reply);
                    break;
                case Recherche:
                    Rechercher rechercher = (Rechercher) m;
                    RechercheBuilder rb = new RechercheBuilder(rechercher);
                    List<Produit> produits = rb.search();
                    List<fr.miage.agents.api.model.Produit> apiProduit = produits.parallelStream().map(p -> {
                        fr.miage.agents.api.model.Produit p2 = new fr.miage.agents.api.model.Produit();
                        p2.descriptionProduit = p.getDescriptionProduit();
                        Categorie c = new Categorie();
                        c.idCategorie = p.getIdCategorie().getIdCategorie();
                        c.nomCategorie = p.getIdCategorie().getNomCategorie();
                        p2.idCategorie = c;
                        p2.idProduit = p.getId();
                        p2.marque = p.getMarqueProduit();
                        p2.prixProduit = p.getPrixProduit() * 2;
                        return p2;
                    }).collect(Collectors.toList());
                    reply =  msg.createReply();
                    ResultatRecherche resultatRecherche = new ResultatRecherche();
                    resultatRecherche.produitList = apiProduit;
                    resultatRecherche.Session = rechercher.session;
                    reply.setContentObject(resultatRecherche);
                    myAgent.send(reply);
                case Aide:
                    ResultatAide ra = new ResultatAide();
                    ArrayList<TypeMessage> typeMessages = new ArrayList<>();
                    typeMessages.add(TypeMessage.Aide);
                    typeMessages.add(TypeMessage.AnnulerAchat);
                    typeMessages.add(TypeMessage.FinaliserAchat);
                    typeMessages.add(TypeMessage.Recherche);
                    typeMessages.add(TypeMessage.InitierAchat);
                    typeMessages.add(TypeMessage.NegocierPrix);
                    typeMessages.add(TypeMessage.Production);
                    ra.supportedActions = typeMessages;
                    reply =  msg.createReply();
                    reply.setContentObject(ra);
                    myAgent.send(reply);
            }
        } catch (UnreadableException | IOException e) {
            e.printStackTrace();
        }
    }

    private ResultatFinalisationAchat traitementFinalisationAchat(FinaliserAchat fa) {
        ResultatFinalisationAchat rfa = new ResultatFinalisationAchat();
        rfa.session = fa.session;
        rfa.idProduit = sessionPanier.get(fa.session).getIdProduit();
        rfa.prixFinal = sessionPanier.get(fa.session).getPrix();
        executerAchat(sessionPanier.get(fa.session));
        return rfa;
    }

    private ResultatAnnulationAchat traitementAnnulationAchat(AnnulerAchat aa){
        ResultatAnnulationAchat raa = new ResultatAnnulationAchat();
        sessionPanier.remove(aa.session);
        raa.session = aa.session;
        return raa;
    }

    private ResultatNegociation traitementNegociation(NegocierPrix nego) {
        ResultatNegociation rn = new ResultatNegociation();
        Panier p = sessionPanier.get(nego.session);
        float prixCalcule = Strategie.venteProduit(p.getIdProduit(),nego.quantiteDemande);

        if(Strategie.accepterRefuserNegociation(nego.prixDemande, nego.quantiteDemande, prixCalcule)){
            rn.estAccepte = true;
            rn.prixNegocie = nego.prixDemande;
            Panier pNew = sessionPanier.get(nego.session);
            p.setPrix(nego.prixDemande);
            p.setQuantite(nego.quantiteDemande);
            sessionPanier.put(nego.session,pNew);
        }
        else{
            rn.prixNegocie = prixCalcule;
            rn.estAccepte = false;
        }
        rn.quantiteDisponible = Strategie.getQuantiteDispoDemande(p.getIdProduit(),nego.quantiteDemande);
        return rn;
    }

    public ResultatInitiationAchat traitementInitierAchat(InitierAchat achat){
        ResultatInitiationAchat ria = new ResultatInitiationAchat();

        ria.session = achat.session;
        ria.success = true;
        ria.quantiteDisponible = Strategie.getQuantiteDispoDemande(achat.idProduit, achat.quantite);
        ria.prixFixe = Strategie.venteProduit(achat.idProduit, ria.quantiteDisponible);
        System.out.println("PRIX FIXE : "+ria.prixFixe);
        Panier p = new Panier(achat.idProduit, achat.quantite, ria.prixFixe);
        sessionPanier.put(achat.session, p);
        return ria;
    }

    public boolean done() {
        return false;
    }

    private void executerAchat(Panier panier){
        System.out.println("Panier prix : "+panier.getPrix()+" / Panier qte : "+panier.getQuantite());
        Query queryCompte= HibernateUtil.openSession().createQuery("from CompteActuel where id=:id ");
        queryCompte.setParameter("id", 1L);
        CompteActuel compte = (CompteActuel) queryCompte.uniqueResult();
        compte.addSolde( panier.getPrix()*panier.getQuantite(), 1L);
        recettes = compte.getSoldeCompte(1L);
        Produit.addQuantity(panier.getIdProduit(), -panier.getQuantite());
        System.out.println("Fournisseur : 'Parfait ! L'achat s'est correctement finalisé, j'ai maintenant "+recettes+"€ de recettes !'");
    }

    private void traiterProduction(Production prod) {
        Produit.addQuantity(prod.idProduit, prod.quantiteProduite);
        Query query = HibernateUtil.openSession().createQuery("from Produit where id=:id ");
        query.setParameter("id", prod.idProduit);
        Produit produit = (Produit) query.uniqueResult();
        float price = produit.getPrixProduit() * prod.quantiteProduite;
        Query queryCompte = HibernateUtil.openSession().createQuery("from CompteActuel where id=:id ");
        queryCompte.setParameter("id", 1L);
        CompteActuel compte = (CompteActuel) queryCompte.uniqueResult();
        compte.addSolde(-price, 1L);
        recettes = compte.getSoldeCompte(1L);
        System.out.println("Fournisseur : 'Parfait ! On s'est fait ravitaillé notre solde est de "+recettes+"€ !'");
    }
}
