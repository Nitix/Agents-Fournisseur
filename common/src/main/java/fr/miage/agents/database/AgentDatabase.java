package fr.miage.agents.database;

import fr.miage.agents.fournisseur.model.Categorie;
import fr.miage.agents.fournisseur.model.Produit;
import fr.miage.agents.util.HibernateUtil;
import jade.core.Agent;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandre on 11/11/2016.
 */
public class AgentDatabase extends Agent {

    private static final HashMap<String, String> produitCategorieMap;
    static
    {
        produitCategorieMap = new HashMap<String, String>();
        produitCategorieMap.put("Carotte", "Légume");
        produitCategorieMap.put("Poireau", "Légume");
        produitCategorieMap.put("Citrouille", "Légume");
        produitCategorieMap.put("Brie", "Produit laitier");
        produitCategorieMap.put("Comté", "Produit laitier");
        produitCategorieMap.put("Lait", "Produit laitier");
        produitCategorieMap.put("Bière", "Boisson");
        produitCategorieMap.put("Eau", "Boisson");
        produitCategorieMap.put("Coca", "Boisson");
        produitCategorieMap.put("Javel", "Produit entretien");
        produitCategorieMap.put("Lave vitre", "Produit entretien");
        produitCategorieMap.put("Liquide vaisselle", "Produit entretien");
        produitCategorieMap.put("Rouge à lèvre", "Cosmétique");
        produitCategorieMap.put("Fond de teint", "Cosmétique");
        produitCategorieMap.put("Parfum", "Cosmétique");
        produitCategorieMap.put("Television HD", "High-tech");
        produitCategorieMap.put("Smartphone", "High-tech");
        produitCategorieMap.put("Rasoir éléctrique", "High-tech");

    }

    protected void setup() {
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" et je suis le taulier de la BDD !");

        addBehaviour(new AgentDatabaseBehaviour(this));
    }

    protected void init(){
        if(!isDataBaseCreated()){
            Transaction tx = null;
            try (Session session = HibernateUtil.openSession()){
                tx = session.beginTransaction();
                for (Map.Entry<String,String> e : produitCategorieMap.entrySet()) {
                    Categorie c = new Categorie();
                    if(isCategorieExist(e.getValue())==null){
                        c.setNomCategorie(e.getValue());
                        Integer categorieID = (Integer) session.save(c);
                        c.updateCategorieId(e.getValue(),categorieID);
                    }
                    Produit p = new Produit();
                    p.setNomProduit(e.getKey());
                    p.setIdCategorie(c);
                    Integer produitID = (Integer) session.save(p);
                }
                tx.commit();
            }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                e.printStackTrace();
            }
        }
    }

    private Categorie isCategorieExist(String name){
        Query query= HibernateUtil.openSession().createQuery("from Categorie where nomCategorie=:name");
        query.setParameter("name", name);
        Categorie c = (Categorie) query.uniqueResult();

        if(c.getNomCategorie()==null){
            return null;
        }
        else{
            return c;
        }

    }

    private boolean isDataBaseCreated(){
        Transaction tx = null;
        try(Session session = HibernateUtil.openSession()){
            tx = session.beginTransaction();
            int i = 0;
            List produits = session.createQuery("FROM Produit").list();
            for (Iterator iterator = produits.iterator(); iterator.hasNext(); ) {
                return true;
            }
            tx.commit();
            return false;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
