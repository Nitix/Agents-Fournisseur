package fr.miage.agents.fourniseur.producteur; /**
 * Created by Arthur on 31/10/2016.
 */

import fr.miage.agents.api.message.negociation.InitierAchat;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.UUID;


public class AgentProducteur extends Agent{

    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" et je suis un producteur !");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("database", AID.ISLOCALNAME));
        
    }
}
