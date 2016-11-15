package fr.miage.agents.database;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;
import fr.miage.agents.api.message.demande.*;
import fr.miage.agents.api.message.reponse.ResultatRecherche;
import fr.miage.agents.fournisseur.model.Categorie;
import fr.miage.agents.fournisseur.model.Produit;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.event.MessageAdapter;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Alexandre on 11/11/2016.
 */
public class AgentDatabaseBehaviour extends Behaviour {

    Agent myAgent;
    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    public AgentDatabaseBehaviour(AgentDatabase receiveMessagerAgent) {
        this.myAgent = receiveMessagerAgent;

    }

    public void action() {
        ACLMessage msg = myAgent.blockingReceive(mt);
        try {
            Message m = (Message) msg.getContentObject();
            switch (m.type){
                case Achat:
                    Acheter achat = (Acheter) m;
                    break;
                case Aide:
                    Aide aide = (Aide) m;
                    break;
                case DemandeDistance:
                    DemandeDistane demandeDistane = (DemandeDistane) m;
                    break;
                case NegocierPrix:
                    NegocierPrix negociation = (NegocierPrix) m;
                    break;
                case PrevenirSolde:
                    PrevenirSolde prevenirSolde = (PrevenirSolde) m;
                    break;
                case Recherche:
                    Recherche recherche = (Recherche) m;
                    break;
                case ResultatRecherche:
                    ResultatRecherche resultatRecherche = (ResultatRecherche) m;
                    break;
            }
        } catch (UnreadableException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject(msg.getContent());
        String action = obj.getString("action");
        Integer idCat = Categorie.addCategorie("Légume");
        Produit.addProduit("Tomate", "J'aime les tomates",1.0f,1 ,idCat, "Bonduel");
        Produit.addProduit("Pomme", "J'aime les pommes",1.0f,1 ,idCat, "Granny");
        Produit.listProduits();
        switch (action){
            case "Produire":
                JSONArray arr = obj.getJSONArray("elements");
                for (int i = 0; i < arr.length(); i++) {
                   JSONObject ligne = arr.getJSONObject(i);
                    String nom = ligne.getString("nom");
                    String marque = ligne.getString("marque");
                    int quantite = ligne.getInt("quantite");
                    Produit.addQuantity(nom, marque, quantite);
                }
        }



        System.out.println("Le message reçu : " + msg.getContent());
    }

    public boolean done() {
        return false;
    }
}
