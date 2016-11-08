package fr.miage.agents.fournisseur.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Guillaume on 07/11/2016.
 */
@Entity
public interface Categorie {

    int getIdCategorie();

    void setIdCategorie(int idCategorie);

    String getNomCategorie();

    void setNomCategorie(String nomCategorie);
}
