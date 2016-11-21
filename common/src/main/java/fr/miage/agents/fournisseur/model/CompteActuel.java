package fr.miage.agents.fournisseur.model;

import fr.miage.agents.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;

/**
 * Created by Alexandre on 21/11/2016.
 */
@Entity
@Table(name="CompteActuel")
public class CompteActuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompte")
    private int id;
    @Column(name = "sommeActuelle")
    private float sommeActuelle;

    public CompteActuel(float somme) {
        this.sommeActuelle = somme;
    }

    public CompteActuel() {
    }

    public Integer generateCompte(float sommeDepart) {

        Transaction tx = null;
        Integer compteID = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            CompteActuel categorie = new CompteActuel(sommeDepart);
            compteID = (Integer) session.save(categorie);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return compteID;
    }

    public void updateCompte(float nombre, Integer idCompteActuelle) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            CompteActuel compteActuel = (CompteActuel) session.get(CompteActuel.class, idCompteActuelle);
            compteActuel.sommeActuelle = compteActuel.sommeActuelle + nombre;
            session.update(compteActuel);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public float getNombreCompte(Integer idCompteActuelle) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            CompteActuel compteActuel = (CompteActuel) session.get(CompteActuel.class, idCompteActuelle);
            return compteActuel.sommeActuelle;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return 0;
    }


}
