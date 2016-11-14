package fr.miage.agents.api.message.demande;

import fr.miage.agents.api.message.Message;
import fr.miage.agents.api.message.TypeMessage;

/**
 * Created by nitix on 14/11/16.
 */
public class NegocierPrix extends Message {

    public NegocierPrix() {
        super(TypeMessage.NegocierPrix);
    }
}
