package fr.miage.agents.database;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.negociation.*;
import fr.miage.agents.strategie.Strategie;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Alexandre on 11/11/2016.
 */
public class AgentDatabaseBehaviour extends Behaviour {

    Agent myAgent;
    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    HashMap<UUID, Panier> sessionPanier = new HashMap<>();
    private float recettes = 0;

    public AgentDatabaseBehaviour(AgentDatabase receiveMessagerAgent) {
        this.myAgent = receiveMessagerAgent;
    }

    public void action() {
        ACLMessage msg = myAgent.blockingReceive(mt);
        try {
            Message m = (Message) msg.getContentObject();
            System.out.println("type du message envoyé :"+m.type);
            switch (m.type){
                case InitierAchat:
                    InitierAchat achat = (InitierAchat) msg.getContentObject();
                    Message resultatInitiationAchat = traitementInitierAchat(achat);
                    ACLMessage msgResponseInitiationAchat = new ACLMessage(ACLMessage.INFORM);
                    msgResponseInitiationAchat.addReceiver(new AID("magasinier", AID.ISLOCALNAME));
                    try {
                        msgResponseInitiationAchat.setContentObject(resultatInitiationAchat);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case NegocierPrix:
                    NegocierPrix nego = (NegocierPrix) msg.getContentObject();
                    Message reponseNego = traitementNegociation(nego);
                    ACLMessage msgReponse = new ACLMessage(ACLMessage.INFORM);
                    msgReponse.addReceiver(new AID("magasinier", AID.ISLOCALNAME));
                    try {
                        msgReponse.setContentObject(reponseNego);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case FinaliserAchat:
                    FinaliserAchat fa = (FinaliserAchat) msg.getContentObject();
                    Message reponseFinalisationAchat = traitemantFinalisationAchat(fa);
                    ACLMessage msgReponseFinalisationAchat = new ACLMessage(ACLMessage.INFORM);
                    msgReponseFinalisationAchat.addReceiver(new AID("magasinier", AID.ISLOCALNAME));
                    try {
                        msgReponseFinalisationAchat.setContentObject(reponseFinalisationAchat);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case AnnulerAchat:
                    break;
            }
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
    }

    private ResultatFinalisationAchat traitemantFinalisationAchat(FinaliserAchat fa) {
        ResultatFinalisationAchat rfa = new ResultatFinalisationAchat();
        rfa.session = fa.session;
        rfa.idProduit = sessionPanier.get(fa.session).getIdProduit();
        rfa.prixFinal = sessionPanier.get(fa.session).getPrix();
        executerAchat(sessionPanier.get(fa.session));
        sessionPanier.remove(fa.session);
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
        Panier p = new Panier(achat.idProduit, achat.quantite);
        sessionPanier.put(achat.session, p);
        ria.session = achat.session;
        ria.success = true;
        ria.quantiteDisponible = Strategie.getQuantiteDispoDemande(achat.idProduit, achat.quantite);
        return ria;
    }

    public boolean done() {
        return false;
    }

    private void executerAchat(Panier panier){
        recettes += panier.getPrix()*panier.getQuantite();
        
    }

}
