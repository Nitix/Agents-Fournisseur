package fr.miage.agents.fournisseur.model;

import fr.miage.agents.fournisseur.util.HibernateUtil;
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
    private long id;
    @Column(name = "sommeActuelle")
    private float sommeActuelle;

    public CompteActuel(float somme) {
        this.sommeActuelle = somme;
    }

    public CompteActuel() {
    }

    public Long generateCompte(float sommeDepart) {

        Transaction tx = null;
        Long compteID = null;
        Session session = HibernateUtil.openSession();
        try {
            tx = session.beginTransaction();
            CompteActuel categorie = new CompteActuel(sommeDepart);
            compteID = (Long) session.save(categorie);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return compteID;
    }

    public void updateCompte(float nombre, Long idCompteActuelle) {
        Transaction tx = null;
        Session session = HibernateUtil.openSession();
        try {
            tx = session.beginTransaction();
            CompteActuel compteActuel = (CompteActuel) session.get(CompteActuel.class, idCompteActuelle);
            compteActuel.sommeActuelle = compteActuel.sommeActuelle + nombre;
            session.update(compteActuel);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public float getNombreCompte(Long idCompteActuelle) {
        Transaction tx = null;
        Session session = HibernateUtil.openSession();
        try {
            tx = session.beginTransaction();
            CompteActuel compteActuel = (CompteActuel) session.get(CompteActuel.class, idCompteActuelle);
            return compteActuel.sommeActuelle;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return 0;
    }


}
