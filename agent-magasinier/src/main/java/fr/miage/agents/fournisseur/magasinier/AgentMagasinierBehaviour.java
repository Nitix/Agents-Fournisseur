package fr.miage.agents.fournisseur.magasinier;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.negociation.InitierAchat;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.UUID;

import static java.lang.Thread.sleep;

/**
 * Created by Arthur on 06/12/2016.
 */
public class AgentMagasinierBehaviour extends Behaviour {

    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    public AgentMagasinierBehaviour(AgentMagasinier agentMagasinier) {
        this.myAgent = agentMagasinier;
    }

    @Override
    public void action() {
        while(true) {
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
            myAgent.send(msg);

            ACLMessage messageReception = myAgent.blockingReceive(mt);
            try {

                //Switch le type de message reçu

                Message m = (Message) messageReception.getContentObject();
                System.out.println("Magasinier : 'Message reçu' : " + m.type);
            } catch (UnreadableException e) {
                e.printStackTrace();
            }

            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
