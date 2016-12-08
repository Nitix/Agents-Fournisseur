package fr.miage.agents.fournisseur.database;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.production.Production;
import fr.miage.agents.fournisseur.model.CompteActuel;
import fr.miage.agents.fournisseur.model.Produit;
import fr.miage.agents.fournisseur.util.HibernateUtil;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import org.hibernate.Query;

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
        ACLMessage aclMessage = myAgent.blockingReceive();
        try {
            Message msg = (Message) aclMessage.getContentObject();
            switch (msg.type) {
                case Production:
                    Production prod = (Production) msg;
                    Produit.addQuantity(prod.idProduit, prod.quantiteProduite);
                    Query query= HibernateUtil.openSession().createQuery("from Produit where id=:id ");
                    query.setParameter("id", prod.idProduit);
                    Produit produit = (Produit) query.uniqueResult();
                    float price = produit.getPrixProduit()*prod.quantiteProduite;
                    Query queryCompte= HibernateUtil.openSession().createQuery("from CompteActuel where id=:id ");
                    queryCompte.setParameter("id", 1);
                    CompteActuel compte = (CompteActuel) query.uniqueResult();
                    compte.addSolde(- price, (long) 1);
                    break;
            }
        } catch (UnreadableException e) {
            e.printStackTrace();
        }


    }

    public boolean done() {
        return false;
    }

}
