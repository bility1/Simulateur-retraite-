package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Groupe {
    @Id
    private int idGroupe;
    private String nomGroupe;
    @OneToMany(fetch= FetchType.EAGER)
    private Set<Message> publisGroupe;
    @ManyToMany
    private Set<Membre> membresGroupe;


    public Groupe() {
        publisGroupe = new HashSet<>();
        membresGroupe = new HashSet<>();
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Set<Message> getPublisGroupe() {
        return publisGroupe;
    }

    public void setPublisGroupe(Set<Message> publisGroupe) {
        this.publisGroupe = publisGroupe;
    }

    public Set<Membre> getMembresGroupe() {
        return membresGroupe;
    }

    public void setMembresGroupe(Set<Membre> membresGroupe) {
        this.membresGroupe = membresGroupe;
    }
}
