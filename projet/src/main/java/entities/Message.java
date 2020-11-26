package entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Un message émit par un membre.
 * Le message peut être un message "initial", auquel cas reference est nul.
 * Ou alors ça peut être une réponse (ou forward commenté) à un autre message. Le champ reference indique alors le message de départ.
 */
@Entity
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMessage;
    @ManyToOne
    private Membre auteur;
    // géré en timestamp...
    private Date dateMessage;
    private String texteMessage;
    @OneToOne(fetch= FetchType.EAGER)
    private Message reference;

    @Override
    public String toString() {
        return "Message{" +
                "idMessage=" + idMessage +
                ", dateMessage=" + dateMessage +
                ", texteMessage='" + texteMessage + '\'' +
                '}';
    }

    public int getIdMessage() {
        return idMessage;
    }



    public Membre getAuteur() {
        return auteur;
    }

    public void setAuteur(Membre auteur) {
        this.auteur = auteur;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }

    public String getTexteMessage() {
        return texteMessage;
    }

    public void setTexteMessage(String texteMessage) {
        this.texteMessage = texteMessage;
    }

    public Message getReference() {
        return reference;
    }

    public void setReference(Message reference) {
        this.reference = reference;
    }
}
