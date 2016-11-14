package fr.miage.agents.api.message.demande;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;
import fr.miage.agents.api.model.Categorie;

/**
 * Created by nitix on 14/11/16.
 */
public class Recherche extends Message {

    public String reference;

    public Categorie categorie;

    public String marque;

    public double prixMax;

    public double prixMin;

    public Recherche() {
        super(TypeMessage.Recherche);
    }
}
