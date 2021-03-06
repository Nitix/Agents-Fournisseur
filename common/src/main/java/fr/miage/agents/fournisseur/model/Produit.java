package fr.miage.agents.fournisseur.model;
/**
 * Created by Alexandre on 06/11/2016.
 */

import fr.miage.agents.fournisseur.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name="produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nomProduit")
    private String nomProduit;
    @Column(name = "descriptionProduit")
    private String descriptionProduit;
    @Column(name = "prixProduit")
    private float prixProduit;
    @Column(name = "quantiteProduit", columnDefinition = "long default 0")
    private int quantiteProduit;
    @Column(name = "marqueProduit")
    private String marqueProduit;

    @ManyToOne
    @JoinColumn(name="idCategorie")
    private Categorie idCategorie;

    public static SessionFactory factory;

    public Produit(String nomProduit, String descriptionProduit, float prixProduit, int quantiteProduit, Categorie idCategorie, String marqueProduit) {
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
        this.idCategorie = idCategorie;
        this.marqueProduit = marqueProduit;
    }

    public Produit() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getMarqueProduit() {
        return marqueProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public void setMarqueProduit(String marqueProduit) {
        this.marqueProduit = marqueProduit;
    }

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
    }

    /* Method to CREATE a product in the database */
    public static Integer addProduit(String nomProduit, String descriptionProduit, float prixProduit, int quantite, long categorieID, String marqueProduit){
        Transaction tx = null;
        Integer produitID = null;
        Session session = HibernateUtil.openSession();
        try{
            tx = session.beginTransaction();
            Produit produit = new Produit();
            produit.setNomProduit(nomProduit);
            produit.setDescriptionProduit(descriptionProduit);
            produit.setPrixProduit(prixProduit);
            produit.setQuantiteProduit(quantite);
            produit.setMarqueProduit(marqueProduit);
            Categorie categorie = (Categorie) session.get(Categorie.class, categorieID);
            produit.setIdCategorie(categorie);
            produitID = (Integer) session.save(produit);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return produitID;
    }

    /* Method to UPDATE price for an product */
    public static void updateProduit(Integer ProductID, float prixProduit ){
        Transaction tx = null;
        Session session = HibernateUtil.openSession();
        try{
            tx = session.beginTransaction();
            Produit produit =
                    (Produit) session.get(Produit.class, ProductID);
            produit.setPrixProduit( prixProduit );
            session.update(produit);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    /* Method to  READ all the products */
    public static void listProduits( ) {
        Transaction tx = null;
        Session session = HibernateUtil.openSession();
        try{
            tx = session.beginTransaction();
            List produits = session.createQuery("FROM Produit").list();
            for (Iterator iterator =
                 produits.iterator(); iterator.hasNext(); ) {
                Produit produit = (Produit) iterator.next();
                System.out.print("Nom du Produit : " + produit.getNomProduit());
                System.out.print("  Description du Produit : " + produit.getDescriptionProduit());
                System.out.println("  Prix du Produit: " + produit.getPrixProduit());
                System.out.println("  Quantité restante du Produit: " + produit.getQuantiteProduit());
                System.out.println(" Marque du produit :"+ produit.getMarqueProduit());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static void addQuantity(long id, int quantite)
    {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        Produit produit = (Produit) session.get(Produit.class, id);
        produit.setQuantiteProduit(produit.getQuantiteProduit() + quantite);
        try{
            session.saveOrUpdate(produit);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public static long getMaxIdProduit(){
        Transaction tx = null;
        Integer produitID = null;
        Session session = HibernateUtil.openSession();
        try{
            tx = session.beginTransaction();
            Produit produit =
                    (Produit) session.createCriteria(Produit.class)
                            .addOrder(Order.desc("id"))
                            .setMaxResults(1)
                            .uniqueResult();
            return produit.id;
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return 0;
    }



}
