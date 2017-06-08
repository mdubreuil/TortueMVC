package model;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TortueBalle extends Tortue implements Observer {

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
        if (tortueSuivie != null) {
            tortueSuivie.deleteObserver(this);
        }

        if (suivie != null) {
            suivie.addObserver(this);
            x = suivie.getX();
            y = suivie.getY();
        }

        tortueSuivie = suivie;

        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Tortue) {
            Tortue tortue = (Tortue) o;
            setPosition(tortue.getX(), tortue.getY());
        }
    }
}
