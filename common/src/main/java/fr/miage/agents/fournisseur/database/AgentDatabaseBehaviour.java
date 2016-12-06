package fr.miage.agents.fournisseur.database;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.production.Production;
import fr.miage.agents.fournisseur.model.Produit;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

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
        ACLMessage aclMessage = myAgent.blockingReceive();
        try {
            Message msg = (Message) aclMessage.getContentObject();
            switch (msg.type) {
                case Production:
                    Production prod = (Production) msg;
                    Produit.addQuantity(prod.idProduit, prod.quantiteProduite);
                    break;
            }
        } catch (UnreadableException e) {
            e.printStackTrace();
        }


    }

    public boolean done() {
        return false;
    }

}
