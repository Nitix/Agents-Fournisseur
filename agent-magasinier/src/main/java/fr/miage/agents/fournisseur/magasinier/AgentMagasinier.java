package fr.miage.agents.fournisseur.magasinier; /**
 * Created by Arthur on 31/10/2016.
 */
import fr.miage.agents.api.message.demande.Acheter;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.UUID;

public class AgentMagasinier extends Agent{
    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" est je suis un magasinier !");
    }




}
