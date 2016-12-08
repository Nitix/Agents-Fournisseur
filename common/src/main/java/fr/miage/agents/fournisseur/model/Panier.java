package fr.miage.agents.fournisseur.model;

/**
 * Created by Arthur on 05/12/2016.
 */
public class Panier {

    private long idProduit;

    private int quantite;

    private float prix;

    public Panier(long idProduit, int quantite, float prix) {
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Panier(long idProduit, int quantite) {
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    public long getIdProduit() {
        return idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setIdProduit(long idProduit) {
        this.idProduit = idProduit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
