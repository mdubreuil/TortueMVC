package model;

import java.util.Observable;
import java.util.Observer;

/**
 * Correspondant à une tortue balle : suit une autre tortue et est passé à une autre lorsqu'elle est suffisament proche
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
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
    
    /**
     * Paramètre la tortue suivant la balle
     *
     * @param suivie : TortueJoueuse suivant la balle
     */
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
    
    /**
     * Incrémente la durée de possession de la balle par la tortue la possédant
     */
    public void incrementerDureePossession() {
        if (getTortueSuivie() != null) {
            dureePossession++;
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    /**
     * Retourne la durée de possession de la balle
     *
     * @return int : durée de possession
     */
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
