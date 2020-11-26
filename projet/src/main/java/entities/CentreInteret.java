package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Permet de définir une liste de centres d'intérêt
 */
@Entity
public class CentreInteret {
    @Id
    private int idCI;
    private String intituleCI;

    public int getIdCI() {
        return idCI;
    }

    public void setIdCI(int idCI) {
        this.idCI = idCI;
    }

    public String getIntituleCI() {
        return intituleCI;
    }

    public void setIntituleCI(String intituleCI) {
        this.intituleCI = intituleCI;
    }
}
