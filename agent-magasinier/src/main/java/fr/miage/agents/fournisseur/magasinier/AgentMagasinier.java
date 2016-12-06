package fr.miage.agents.fournisseur.magasinier;

/**
 * Created by Arthur on 31/10/2016.
 */
import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.negociation.InitierAchat;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.UUID;

public class AgentMagasinier extends Agent{

    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" est je suis un magasinier !");
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("michel", AID.ISLOCALNAME));
        try {
            InitierAchat achat = new InitierAchat();
            achat.session = UUID.randomUUID();
            achat.idProduit = 1;
            achat.quantite = 3;
            msg.setContentObject(achat);
            System.out.println("Magasinier : 'j'envois un message !' ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        send(msg);

        ACLMessage messageReception = blockingReceive(mt);
        try {

            //Switch le type de message reçu

            Message m = (Message) messageReception.getContentObject();
            System.out.println("Magasinier : 'Message reçu' : "+m.type);
        } catch (UnreadableException e) {
            e.printStackTrace();
        }

    }




}
