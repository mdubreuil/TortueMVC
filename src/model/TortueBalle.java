package model;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TortueBalle extends Tortue implements Observer {

    protected TortueJoueuse tortueSuivie = null;

    public TortueBalle() {
        super();
    }

    public TortueBalle(TortueJoueuse tortueSuivie) {
        this();
        setTortueSuivie(tortueSuivie);
    }

    public TortueJoueuse getTortueSuivie() {
        return tortueSuivie;
    }

    public void setTortueSuivie(TortueJoueuse suivie) {
        if (suivie == tortueSuivie) {
            return;
        }

        if (tortueSuivie != null) {
            tortueSuivie.deleteObserver(this);
            tortueSuivie.supprimerSuiveur(this);
        }

        if (suivie != null) {
            System.out.println(suivie + " a la balle!");
            suivie.addObserver(this);
            suivie.ajouterSuiveur(this);
            x = suivie.getX();
            y = suivie.getY();
            direction = suivie.getDirection();
        }

        tortueSuivie = suivie;

        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof TortueJoueuse) {
            TortueJoueuse tortue = (TortueJoueuse) o;
            setPosition(tortue.getX(), tortue.getY());
            setDirection(tortue.getDirection());
        }
    }

    @Override
    public String toString() {
        return "TortueBalle";
    }
}
