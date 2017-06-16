
package model.etat;

import model.Jeu;

/**
 * Pattern Etat : correspond à un jeu en pause
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class JeuEnPause extends Etat {
    
    /**
     * Met le jeu en pause : suspension du Timer
     *
     * @param jeu à mettre en pause
     */
    @Override
    public void jouerJeu(Jeu jeu) {
        jeu.reinitialiserTimer();
    }
}
