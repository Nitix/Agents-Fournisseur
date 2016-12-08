package fr.miage.agents.fournisseur.strategie;

import fr.miage.agents.fournisseur.model.Produit;
import fr.miage.agents.fournisseur.util.HibernateUtil;
import org.hibernate.Session;


/**
 * Created by Arthur on 15/11/2016.
 */
public class Strategie {

    public static float venteProduit(long id, int quantiteDemandee){
        Session session = HibernateUtil.openSession();
        Produit p =  (Produit) session.get(Produit.class, id);

        float prixCalculeProduitStocke = getPrixStrategiqueByQuantiteStockee(p);
        float prixCalculePourDemande = getPrixStrategiqueByQuantiteDemandee(p, quantiteDemandee);
        
        float prixVente = (prixCalculeProduitStocke + prixCalculePourDemande)/2;
        return prixVente*quantiteDemandee;
    }

    public static float getPrixStrategiqueByQuantiteStockee(Produit p){
        return (float) (p.getPrixProduit()*(0.5*Math.exp(-0.0025*p.getQuantiteProduit())+0.25))*p.getPrixProduit();
    }

    public static float getPrixStrategiqueByQuantiteDemandee(Produit p, int quantiteDemandee) {
        return (float) (p.getPrixProduit()* (0.5*Math.exp(-0.0025*quantiteDemandee)+0.25))*p.getPrixProduit();
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
        Session session = HibernateUtil.openSession();
        Produit p =  (Produit) session.get(Produit.class, idProduit);

        if(p.getQuantiteProduit()>=quantiteDemandee){
            return p.getQuantiteProduit();
        }
        else{
            return quantiteDemandee;
        }
    }
}
