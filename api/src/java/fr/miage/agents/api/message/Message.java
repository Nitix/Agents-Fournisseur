package fr.miage.agents.api.message;

import java.io.Serializable;

/**
 * Created by nitix on 14/11/16.
 */
public class Message implements Serializable {

    TypeMessage type;

    public Message(TypeMessage type) {
        this.type = type;
    }
}
