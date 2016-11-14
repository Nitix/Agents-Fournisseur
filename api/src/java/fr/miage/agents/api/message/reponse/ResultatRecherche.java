package fr.miage.agents.api.message.reponse;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;
import fr.miage.agents.api.model.Produit;

import java.util.List;

/**
 * Created by nitix on 14/11/16.
 */
public class ResultatRecherche extends Message{

    public List<Produit> produitList;

    public ResultatRecherche() {
        super(TypeMessage.ResultatRecherche);
    }
}
