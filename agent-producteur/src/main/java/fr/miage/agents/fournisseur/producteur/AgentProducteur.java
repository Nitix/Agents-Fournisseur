package fr.miage.agents.fournisseur.producteur; /**
 * Created by Arthur on 31/10/2016.
 */

import fr.miage.agents.api.message.demande.Acheter;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.UUID;


public class AgentProducteur extends Agent{

    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" et je suis un producteur !");
        /*
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("database", AID.ISLOCALNAME));
        msg.setContent("{'action':'Produire', 'elements': [{'nom':'Tomate', 'marque':'Bonduel', 'quantite':4},{'nom':'Pomme', 'marque':'Granny', 'quantite':1}]}");
        send(msg);*/


        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("database", AID.ISLOCALNAME));
        try {
            Acheter achat = new Acheter();
            achat.session = UUID.randomUUID();
            achat.idProduit = 1;
            achat.quantiteProduit = 3;
            msg.setContentObject(achat);
            System.out.println("Envois d'une demande d'achat");
        } catch (IOException e) {
            e.printStackTrace();
        }
        send(msg);
    }



}
