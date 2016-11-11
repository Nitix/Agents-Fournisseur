package fr.miage.agents.fournisseur.model;

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
        Session session = Produit.factory.openSession();
        Transaction tx = null;
        Integer categorieID = null;
        try{
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
}
