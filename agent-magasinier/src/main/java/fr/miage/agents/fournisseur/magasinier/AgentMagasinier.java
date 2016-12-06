package fr.miage.agents.fournisseur.magasinier; /**
 * Created by Arthur on 31/10/2016.
 */
import fr.miage.agents.api.message.negociation.InitierAchat;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.UUID;

public class AgentMagasinier extends Agent{
    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" est je suis un magasinier !");
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("database", AID.ISLOCALNAME));
        try {
            InitierAchat achat = new InitierAchat();
            achat.session = UUID.randomUUID();
            achat.idProduit = 1;
            achat.quantite = 3;
            msg.setContentObject(achat);
            System.out.println("Demande d'achat pour le magasinier.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        send(msg);
    }




}
