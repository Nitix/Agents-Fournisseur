package fr.miage.agents.fournisseur.database;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Created by Alexandre on 11/11/2016.
 */
public class AgentDatabaseBehaviour extends Behaviour {

    Agent myAgent;
    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    public AgentDatabaseBehaviour(AgentDatabase receiveMessagerAgent) {
        this.myAgent = receiveMessagerAgent;
    }

    public void action() { }
    public boolean done() {
        return false;
    }

}
