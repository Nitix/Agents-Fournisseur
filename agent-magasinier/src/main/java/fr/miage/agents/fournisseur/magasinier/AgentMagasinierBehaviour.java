package fr.miage.agents.fournisseur.magasinier;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.negociation.*;
import fr.miage.agents.database.Panier;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.HashMap;
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
                achat.idProduit = (int) (Math.random()*149);
                achat.quantite = (int) (Math.random()*200);
                msg.setContentObject(achat);
                System.out.println("Magasinier : 'j'envois un message !' ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            myAgent.send(msg);

            ACLMessage messageReception = myAgent.blockingReceive(mt);
            try {
                Message m = (Message) messageReception.getContentObject();
                System.out.println("Magasinier : 'Message reçu' : " + m.type);
                switch (m.type){
                    case ResultatInitiationAchat:
                        ResultatInitiationAchat achat = (ResultatInitiationAchat) msg.getContentObject();
                        Message negociation = traitementResultatInitierAchat(achat);
                        ACLMessage msgNegociation = new ACLMessage(ACLMessage.INFORM);
                        msgNegociation.addReceiver(new AID("michel", AID.ISLOCALNAME));
                        try {
                            msgNegociation.setContentObject(negociation);
                            myAgent.send(msgNegociation);
                            System.out.println("Magasinier : 'J'envois une réponse !'");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case ResultatNegociation:
                        ResultatNegociation resNego = (ResultatNegociation) msg.getContentObject();
                        Message reponseNego = traitementResultatNegociation(resNego);
                        ACLMessage msgReponseNego = new ACLMessage(ACLMessage.INFORM);
                        msgReponseNego.addReceiver(new AID("michel", AID.ISLOCALNAME));
                        try {
                            msgReponseNego.setContentObject(reponseNego);
                            myAgent.send(msgReponseNego);
                            System.out.println("Magasinier : 'J'envois une réponse !'");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case ResultatAnnulationAchat:
                        System.out.println("Magasinier : 'Bien ! Le fournisseur m'informe que l'opération est annulée.'");
                        break;
                    case ResultatFinalisationAchat:
                        System.out.println("Magasinier : 'Parfait ! Le fournisseur m'informe que l'achat s'est bien effectué.'");
                        break;
                }

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

    private Message traitementResultatInitierAchat(ResultatInitiationAchat ria){
        if(ria.success){
            FinaliserAchat fa = new FinaliserAchat();
            fa.session = ria.session;
            return fa;
        }
        else{
            NegocierPrix nego = new NegocierPrix();
            nego.session = ria.session;
            nego.prixDemande = (float) (ria.prixFixe+(ria.prixFixe*0.2));
            nego.quantiteDemande = ria.quantiteDisponible;
            return nego;
        }
    }

    private Message traitementResultatNegociation(ResultatNegociation ria){
        int random = (int) (Math.random()*100);

        if(random <= 15 ){
            AnnulerAchat annule = new AnnulerAchat();
            annule.session = ria.session;
            return annule;
        }
        else if((random > 15)||(random <= 55)){
            FinaliserAchat fa = new FinaliserAchat();
            fa.session = ria.session;
            return fa;
        }
        else{
            NegocierPrix renegociation = new NegocierPrix();
            renegociation.session = ria.session;
            renegociation.prixDemande = (float) (ria.prixNegocie+(ria.prixNegocie*0.2));
            renegociation.quantiteDemande = ria.quantiteDisponible;
            return renegociation;
        }
    }
}
