package fr.miage.agents.api.model;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Guillaume on 07/11/2016.
 */
public class Produit implements Serializable {
    int idProduit;

    String nomProduit;

    String descriptionProduit;

    float prixProduit;

    int quantiteProduit;

    Categorie idCategorie;

    String marque;
}
