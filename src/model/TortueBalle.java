package model;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TortueBalle extends Tortue {
    protected Tortue tortueSuivie;

    public Tortue getTortueSuivie() {
        return tortueSuivie;
    }

    public void setTortueSuivie(Tortue suivie) {
        this.tortueSuivie = suivie;
    }
}
