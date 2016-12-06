package fr.miage.agents.fournisseur.fournisseur;

import jade.core.Agent;

/**
 * Created by Arthur on 06/12/2016.
 */
public class AgentFournisseur extends Agent {

    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" et je suis un fournisseur !");

        addBehaviour(new AgentFournisseurBehaviour(this));

    }
}
