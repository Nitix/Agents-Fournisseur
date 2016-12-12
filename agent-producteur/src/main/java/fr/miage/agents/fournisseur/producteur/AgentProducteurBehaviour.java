package fr.miage.agents.fournisseur.producteur;

import fr.miage.agents.api.message.production.Production;
import fr.miage.agents.fournisseur.model.Produit;
import fr.miage.agents.fournisseur.util.HibernateUtil;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.hibernate.Query;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Alexandre on 06/12/2016.
 */
public class AgentProducteurBehaviour extends Behaviour {

    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    public AgentProducteurBehaviour(AgentProducteur AgentProducteur) {
        this.myAgent = AgentProducteur;
    }

    @Override
    public void action() {
        while(true){
            Production production = produireAleatoirement();

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID("fournisseur", AID.ISLOCALNAME));

            try {
                msg.setContentObject(production);
                System.out.println("Producteur : ' Je viens de produire pour le fournisseur !' ");
                myAgent.send(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            myAgent.doWait(5000);
        }
    }

    private Production produireAleatoirement(){
        long idMax = Produit.getMaxIdProduit();
        long id = ThreadLocalRandom.current().nextInt(1, (int) idMax + 1);
        Query query= HibernateUtil.openSession().createQuery("from Produit where id=:id ");
        query.setParameter("id", idMax);
        Production production = new Production();
        Produit produit = (Produit) query.uniqueResult();
        int quantiteAdd = ThreadLocalRandom.current().nextInt(10, 20);
        production.idProduit = id;
        production.quantiteProduite = quantiteAdd;
        return production;
    }

    public boolean done() {
        return false;
    }
}
