package fr.miage.agents.strategie;

import fr.miage.agents.fournisseur.model.Categorie;
import fr.miage.agents.fournisseur.model.Produit;
import fr.miage.agents.util.HibernateUtil;
import jdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;


/**
 * Created by Arthur on 15/11/2016.
 */
public class Strategie {

    public static float venteProduit(String nomProduit, String marqueProduit, int quantiteDemandee){
        Query query= HibernateUtil.openSession().createQuery("from Produit where nomProduit=:name and marqueProduit=:mrq");
        query.setParameter("name", nomProduit);
        query.setParameter("mrq", marqueProduit);
        Produit p = (Produit) query.uniqueResult();

        float prixCalculeProduitStocke = getPrixStrategiqueByQuantiteStockee(p);
        float prixCalculePourDemande = 0;
        if(assezQuantite(p, quantiteDemandee)){
            prixCalculePourDemande = getPrixStrategiqueByQuantiteDemandee(p, quantiteDemandee);
        }
        else{
            quantiteDemandee = p.getQuantiteProduit();
            prixCalculePourDemande = getPrixStrategiqueByQuantiteDemandee(p, quantiteDemandee);
        }

        float prixVente = (prixCalculeProduitStocke + prixCalculePourDemande)/2;
        return prixVente*quantiteDemandee;
    }

    public static float getPrixStrategiqueByQuantiteStockee(Produit p){

        int qteStockee = p.getQuantiteProduit();

        float prix = -1;
        if(qteStockee > 0){
            prix = p.getPrixProduit()+((100*20/p.getPrixProduit())/qteStockee);
        }
        return prix;
    }

    public static float getPrixStrategiqueByQuantiteDemandee(Produit p, int quantiteDemandee){
        float pourcentageQuantite = ((quantiteDemandee*100)/p.getQuantiteProduit());
        return p.getPrixProduit()+((100*20/p.getPrixProduit())/(2*pourcentageQuantite));
    }

    public static boolean assezQuantite(Produit p, int quantite){
        if(p.getQuantiteProduit()>=quantite){
            return true;
        }
        return false;
    }

    public static boolean accepterRefuserNegociation(){
        int reussite = (int)Math.random()*100;
        if(reussite>=20){
            return false;
        }
        else{
            return true;
        }
    }

    public static float calculPrixApresNegociation(float prixCalcule){
        if(accepterRefuserNegociation()){
            return prixCalcule - ((prixCalcule*20)/100);
        }
        else{
            return prixCalcule;
        }
    }
}
