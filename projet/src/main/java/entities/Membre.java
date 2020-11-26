package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Un membre peut appartenir à 0, 1 ou plusieurs groupes.
 * Quand il publie un message il peut l'associer ou non à un groupe.
 * Si le message est associé à un groupe il aparait dans la liste des message du membre et dans la liste des messages du groupe.
 */
@Entity
public class Membre {
    @Id
    private String login;
    private String passwd;
    @ManyToMany(fetch= FetchType.EAGER)
    private Set<Membre> amis;
    @OneToMany(fetch= FetchType.EAGER,mappedBy = "auteur")
    private Set<Message> publications ;
    @ManyToMany(fetch= FetchType.EAGER,mappedBy = "membresGroupe")
    private Set<Groupe> groupes;
    @OneToMany
    private Set<CentreInteret> interets ;


    public Membre() {
        this.amis = new HashSet<>();
        this.publications = new HashSet<>();
        this.groupes = new HashSet<>();
        this.interets = new HashSet<>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Set<Membre> getAmis() {
        return amis;
    }

    public void setAmis(Set<Membre> amis) {
        this.amis = amis;
    }

    public Set<Message> getPublications() {
        return publications;
    }

    public void setPublications(Set<Message> publications) {
        this.publications = publications;
    }

    public Set<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(Set<Groupe> groupes) {
        this.groupes = groupes;
    }

    public Set<CentreInteret> getInterets() {
        return interets;
    }

    public void setInterets(Set<CentreInteret> interets) {
        this.interets = interets;
    }
}
