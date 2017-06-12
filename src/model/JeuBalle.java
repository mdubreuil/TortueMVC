package model;

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
    public void run() {
        // Start game
        setEtat(Etat.EN_COURS);
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (etat == Etat.EN_COURS) {
                    for (Tortue t : tortues) {
                        if (t != tortueCourante && t instanceof TortueJoueuse) {
                            TortueJoueuse tortue = (TortueJoueuse) t;
                            tortue.seDeplacer();
                        }
                    }
                    incrementerDuree();
                } else if (etat == Etat.ARRETE) {
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 0, 1000); // Wait 1 second between each tick
    }
}
