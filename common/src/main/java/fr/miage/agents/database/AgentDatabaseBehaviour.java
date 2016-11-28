package fr.miage.agents.database;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;
import fr.miage.agents.api.message.demande.*;
import fr.miage.agents.api.message.reponse.AppelMethodeIncorrect;
import fr.miage.agents.api.message.reponse.ResultatRecherche;
import fr.miage.agents.strategie.Strategie;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.IOException;

/**
 * Created by Alexandre on 11/11/2016.
 */
public class AgentDatabaseBehaviour extends Behaviour {

    Agent myAgent;
    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    public AgentDatabaseBehaviour(AgentDatabase receiveMessagerAgent) {
        this.myAgent = receiveMessagerAgent;

    }

    public void action() {
        ACLMessage msg = myAgent.blockingReceive(mt);
        try {
            Message m = (Message) msg.getContentObject();
            System.out.println("type du message envoyé :"+m.type);
            switch (m.type){
                case Achat:
                    Acheter achat = (Acheter) m;
                    Message reponse = traitementAchat(achat);
                    ACLMessage msgReponse = new ACLMessage(ACLMessage.INFORM);
                    msgReponse.addReceiver(new AID("magasinier", AID.ISLOCALNAME));
                    try {
                        msgReponse.setContentObject(reponse);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case Aide:
                case DemandeDistance:
                    msgReponse = new ACLMessage(ACLMessage.FAILURE);
                    msgReponse.addReceiver(new AID("magasinier", AID.ISLOCALNAME));
                    try {
                        msgReponse.setContentObject(new AppelMethodeIncorrect());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case NegocierPrix:
                    NegocierPrix negociation = (NegocierPrix) m;
                    break;
                case PrevenirSolde:
                    PrevenirSolde prevenirSolde = (PrevenirSolde) m;
                    break;
                case Recherche:
                    Recherche recherche = (Recherche) m;
                    break;
                case ResultatRecherche:
                    ResultatRecherche resultatRecherche = (ResultatRecherche) m;
                    break;
            }
        } catch (UnreadableException e) {
            e.printStackTrace();
        }
    }

    public boolean done() {
        return false;
    }

    public Message traitementAchat(Acheter achat){
        ReponseAchat ra = new ReponseAchat();
        ra.idSession = achat.idSession;
        ra.nomProduit = achat.nomProduit;
        ra.marqueProduit = achat.marqueProduit;
        ra.prixCalcule = Strategie.venteProduit(achat.nomProduit,achat.marqueProduit,achat.quantiteProduit);
        ra.quantiteProduit = Strategie.getQuantiteDispoDemande(achat.nomProduit,achat.marqueProduit,achat.quantiteProduit);
        return new Message(TypeMessage.ReponseAchat);
    }

}
