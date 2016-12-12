package fr.miage.agents.fournisseur.fournisseur;

import fr.miage.agents.api.message.recherche.Rechercher;
import fr.miage.agents.fournisseur.model.Produit;
import fr.miage.agents.fournisseur.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nitix on 10/12/16.
 */
public class RechercheBuilder {

    private Rechercher rechercher;

    private String query;

    public RechercheBuilder(Rechercher rechercher) {
        this.rechercher = rechercher;
    }

    public List<Produit> search() {
        Transaction tx = null;
        Integer produitID = null;
        Session session = HibernateUtil.openSession();
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Produit.class)
                    .addOrder(Order.desc("id"));
            if( rechercher.idProduit != 0 ){
                criteria.add(Restrictions.eq("id", rechercher.idProduit));
            }
            if(rechercher.prixMax != 0) {
                criteria.add(Restrictions.le("prixproduit", rechercher.prixMax/1.75));
            }

            if(rechercher.prixMin != 0) {
                criteria.add(Restrictions.ge("prixproduit", rechercher.prixMax * 1.25));
            }
            if(rechercher.nomCategorie != null && !rechercher.nomCategorie.equals("")) {
                criteria.createCriteria("categorie")
                        .add(Restrictions.eq("nomcategorie", rechercher.nomCategorie));
            }
            if(rechercher.marque != null && !rechercher.marque.equals("")){
                criteria.add(Restrictions.eq("marqueproduit", rechercher.marque));
            }
            return (List<Produit>) criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new ArrayList<>();
    }
}
