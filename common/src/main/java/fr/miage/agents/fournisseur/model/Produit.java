package fr.miage.agents.fournisseur.model;
/**
 * Created by Alexandre on 06/11/2016.
 */

import fr.miage.agents.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;
@Entity
@Table(name="Produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduit")
    private int idProduit;
    @Column(name = "nomProduit")
    private String nomProduit;
    @Column(name = "descriptionProduit")
    private String descriptionProduit;
    @Column(name = "prixProduit")
    private float prixProduit;
    @Column(name = "quantiteProduit")
    private int quantiteProduit;

    @ManyToOne
    @JoinColumn(name="idCategorie")
    private Categorie idCategorie;


    public static SessionFactory factory;

    public Produit(int idProduit, String nomProduit, String descriptionProduit, float prixProduit, int quantiteProduit, Categorie idCategorie) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
        this.idCategorie = idCategorie;
    }

    public Produit() {
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public float getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
    }

    /* Method to CREATE a product in the database */
    public static Integer addProduit(String nomProduit, String descriptionProduit, float prixProduit, int quantite, int categorieID){
        Transaction tx = null;
        Integer produitID = null;
        try(Session session = HibernateUtil.openSession();){
            tx = session.beginTransaction();
            Produit produit = new Produit();
            produit.setNomProduit(nomProduit);
            produit.setDescriptionProduit(descriptionProduit);
            produit.setPrixProduit(prixProduit);
            produit.setQuantiteProduit(quantite);
            Categorie categorie = (Categorie) session.get(Categorie.class, categorieID);
            produit.setIdCategorie(categorie);
            produitID = (Integer) session.save(produit);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return produitID;
    }

    /* Method to UPDATE price for an product */
    public static void updateProduit(Integer ProductID, float prixProduit ){
        Transaction tx = null;
        try(Session session = HibernateUtil.openSession()){
            tx = session.beginTransaction();
            Produit produit =
                    (Produit) session.get(Produit.class, ProductID);
            produit.setPrixProduit( prixProduit );
            session.update(produit);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }


    /* Method to  READ all the products */
    public static void listProduits( ) {
        Transaction tx = null;
        try(Session session = HibernateUtil.openSession()){
            tx = session.beginTransaction();
            List produits = session.createQuery("FROM Produit").list();
            for (Iterator iterator =
                 produits.iterator(); iterator.hasNext(); ) {
                Produit produit = (Produit) iterator.next();
                System.out.print("Nom du Produit : " + produit.getNomProduit());
                System.out.print("  Description du Produit : " + produit.getDescriptionProduit());
                System.out.println("  Prix du Produit: " + produit.getPrixProduit());
                System.out.println("  Quantité restante du Produit: " + produit.getQuantiteProduit());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public static void addQuantity(String nom, int quantite)
    {
        Query query= HibernateUtil.openSession().
                createQuery("from Produit where nomProduit=:name");
        query.setParameter("name", nom);
        Produit produit = (Produit) query.uniqueResult();
        produit.setQuantiteProduit(produit.getQuantiteProduit() + quantite);
        try(Session session = HibernateUtil.openSession();){
            session.persist(produit);
        }catch (Exception e){

        }
    }



}
