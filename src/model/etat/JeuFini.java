
package model.etat;

import model.Jeu;

/**
 * Pattern Etat : correspond à un jeu terminé
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class JeuFini extends Etat {
    
    /**
     * Arrête le jeu : supprime toutes les tortues + arrête le timer 
     *
     * @param jeu qui doit être arrêté
     */
    @Override
    public void jouerJeu(Jeu jeu) {
        jeu.reinitialiser();
    }
}
