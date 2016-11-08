package fr.miage.agents.fournisseur.api.model;

import javax.persistence.Entity;

/**
 * Created by Guillaume on 07/11/2016.
 */
@Entity
public interface Produit {
    int getIdProduit();

    void setIdProduit(int idProduit);

    String getNomProduit();

    void setNomProduit(String nomProduit);

    String getDescriptionProduit();

    void setDescriptionProduit(String descriptionProduit);

    float getPrixProduit();

    void setPrixProduit(float prixProduit);

    int getQuantiteProduit();

    void setQuantiteProduit(int quantiteProduit);

    Categorie getIdCategorie();

    void setIdCategorie(Categorie idCategorie);
}
