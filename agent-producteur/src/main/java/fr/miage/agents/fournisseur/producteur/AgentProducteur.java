package fr.miage.agents.fournisseur.producteur; /**
 * Created by Arthur on 31/10/2016.
 */

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;


public class AgentProducteur extends Agent{

    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" et je suis un producteur !");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("database", AID.ISLOCALNAME));
        addBehaviour(new AgentProducteurBehaviour(this));

        
    }

}
