package fr.miage.agents.fournisseur.producteur; /**
 * Created by Arthur on 31/10/2016.
 */

import fr.miage.agents.fournisseur.model.Categorie;
import fr.miage.agents.fournisseur.model.Produit;
import jade.core.Agent;
import org.hibernate.cfg.AnnotationConfiguration;


public class AgentProducteur extends Agent{

    protected void setup() {

        try{
            Produit.factory = new AnnotationConfiguration().
                    configure().
                    //addPackage("com.xyz") //add package if used.
                            addAnnotatedClass(Produit.class).
                            addAnnotatedClass(Categorie.class).
                    buildSessionFactory();

        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        System.out.println("Bonjour ! Mon nom est "+getLocalName()+" et je suis un producteur !");
        Integer idCat = Categorie.addCategorie("Légume");
        Produit.addProduit("Tomate", "J'aime les tomates",1.0f,1 ,idCat );
        Produit.listProduits();
    }



}
