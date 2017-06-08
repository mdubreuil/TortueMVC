package model;

import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */

public class JeuBalle extends Jeu {

    private int nbJoueurs = 10;

    public JeuBalle(Tortue courante) {
        super(courante);
    }

    public JeuBalle() {
        super();
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    @Override
    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Tortue t : tortues) {
                    if (t != tortueCourante && t instanceof TortueJoueuse) {
                        TortueJoueuse tortue = (TortueJoueuse) t;
                        tortue.seDeplacer();
                    }
                }
            }
        }, 0, 1000); // Wait 1 second between each tick
    }
}
