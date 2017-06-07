package model;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TortueBalle extends Tortue {

    protected Tortue tortueSuivie = null;

    public TortueBalle() {
        super();
    }

    public TortueBalle(Tortue tortueSuivie) {
        this();
        setTortueSuivie(tortueSuivie);
    }

    public Tortue getTortueSuivie() {
        return tortueSuivie;
    }

    public void setTortueSuivie(Tortue suivie) {
        this.tortueSuivie = suivie;
        this.x = suivie.getX();
        this.y = suivie.getY();
        this.setChanged();
        this.notifyObservers();
    }
}
