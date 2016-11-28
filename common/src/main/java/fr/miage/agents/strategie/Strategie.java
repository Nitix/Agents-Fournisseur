package fr.miage.agents.strategie;

import fr.miage.agents.fournisseur.model.Produit;
import fr.miage.agents.util.HibernateUtil;
import org.hibernate.Query;


/**
 * Created by Arthur on 15/11/2016.
 */
public class Strategie {

    public static float venteProduit(long id, int quantiteDemandee){
        Query query= HibernateUtil.openSession().createQuery("from Produit p where p.id=:id");
        query.setParameter("id", id);
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
        return p.getQuantiteProduit()>=quantite;
    }

    public static boolean accepterRefuserNegociation(float prixPropose, int quantiteDemandee, float prixCalcule){

        if(quantiteDemandee >= 1000){
            return calculParRapportMarge(new Float(0.15), prixPropose, prixCalcule);
        }
        else if(quantiteDemandee < 1000 && quantiteDemandee >= 500){
            return calculParRapportMarge(new Float(0.30), prixPropose, prixCalcule);
        }
        else if(quantiteDemandee < 500 && quantiteDemandee >= 200){
            return calculParRapportMarge(new Float(0.40), prixPropose, prixCalcule);
        }
        else if(quantiteDemandee < 200 && quantiteDemandee >= 1){
            return calculParRapportMarge(new Float(0.50), prixPropose, prixCalcule);
        }
        else{
            return false;
        }
    }

    // La marge doit etre un nombre entre 0 et 1 alors:
    public static boolean calculParRapportMarge(float marge, float prixPropose, float prixCalcule){
        return (prixCalcule + marge *prixCalcule) < prixPropose;
    }


    public static int getQuantiteDispoDemande(long idProduit, int quantiteDemandee) {
        Query query= HibernateUtil.openSession().createQuery("from Produit p where p.id=:id");
        query.setParameter("id", idProduit);
        Produit p = (Produit) query.uniqueResult();

        if(p.getQuantiteProduit()>=quantiteDemandee){
            return p.getQuantiteProduit();
        }
        else{
            return quantiteDemandee;
        }
    }
}
