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
    protected int dureePossession = 0;

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
            if (getDureePossession() < TortueJoueuse.DUREE_POSSESSION_MIN) {
                System.out.println(tortueSuivie + " possède la balle depuis moins de " + TortueJoueuse.DUREE_POSSESSION_MIN + " secondes");
                return;
            }
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
        dureePossession = 0;

        this.setChanged();
        this.notifyObservers();
    }

    public void incrementerDureePossession() {
        if (getTortueSuivie() != null) {
            dureePossession++;
            this.setChanged();
            this.notifyObservers();
        }
    }

    public int getDureePossession() {
        return dureePossession;
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
