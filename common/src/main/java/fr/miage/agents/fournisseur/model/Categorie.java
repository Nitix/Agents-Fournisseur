package fr.miage.agents.fournisseur.model;

import fr.miage.agents.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Alexandre on 06/11/2016.
 */
@Entity
@Table(name="Categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCategorie")
    private int idCategorie;
    @Column(name="nomCategorie")
    private String nomCategorie;

    @OneToMany(mappedBy="idCategorie")
    private Set<Produit> produits;


    public Categorie(int idCategorie, String nomCategorie) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
    }

    public Categorie() {
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    /* Method to CREATE a category in the database */
    public static Integer addCategorie(String nomCategorie){

        Transaction tx = null;
        Integer categorieID = null;
        Session session = HibernateUtil.openSession();
        try {
            tx = session.beginTransaction();
            Categorie categorie = new Categorie();
            categorie.setNomCategorie(nomCategorie);
            categorieID = (Integer) session.save(categorie);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return categorieID;
    }

    public void updateCategorieId(String nomCategorie, Integer categorieID ){
        Transaction tx = null;
        Session session = HibernateUtil.openSession();
        try{
            tx = session.beginTransaction();
            Categorie categorie = (Categorie) session.get(Categorie.class, nomCategorie);
            categorie.setIdCategorie(categorieID);
            session.update(categorie);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
