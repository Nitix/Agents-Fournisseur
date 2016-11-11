package fr.miage.agents.database;

import fr.miage.agents.fournisseur.model.Categorie;
import fr.miage.agents.fournisseur.model.HibernateUtil;
import fr.miage.agents.fournisseur.model.Produit;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
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
        JSONObject obj = new JSONObject(msg.getContent());
        String action = obj.getString("action");
        Integer idCat = Categorie.addCategorie("Légume");
        Produit.addProduit("Tomate", "J'aime les tomates",1.0f,1 ,idCat );
        Produit.addProduit("Pomme", "J'aime les pommes",1.0f,1 ,idCat );
        switch (action){
            case "Produire":
                JSONArray arr = obj.getJSONArray("elements");
                for (int i = 0; i < arr.length(); i++) {
                   JSONObject ligne = arr.getJSONObject(i);
                    String nom = ligne.getString("nom");
                    int quantite = ligne.getInt("quantite");
                    Produit.addQuantity(nom, quantite);
                }
        }



        System.out.println("Le message reçu : " + msg.getContent());
        HibernateUtil.currentSession().close();
    }

    public boolean done() {
        return false;
    }
}