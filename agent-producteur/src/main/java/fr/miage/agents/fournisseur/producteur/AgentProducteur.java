package fr.miage.agents.fournisseur.producteur; /**
 * Created by Arthur on 31/10/2016.
 */

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;


public class AgentProducteur extends Agent{

    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" et je suis un producteur !");
        addBehaviour(new AgentProducteurBehaviour(this));
    }

}
