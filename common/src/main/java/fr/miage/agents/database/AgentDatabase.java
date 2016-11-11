package fr.miage.agents.database;

import jade.core.Agent;

/**
 * Created by Alexandre on 11/11/2016.
 */
public class AgentDatabase extends Agent {

    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" et je suis le taulier de la BDD !");

        addBehaviour(new AgentDatabaseBehaviour(this));
    }
}
