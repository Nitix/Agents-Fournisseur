package fr.miage.agents.fournisseur.magasinier;

/**
 * Created by Arthur on 31/10/2016.
 */

import jade.core.Agent;

public class AgentMagasinier extends Agent{

    protected void setup() {

        addBehaviour(new AgentMagasinierBehaviour(this));

    }
}
