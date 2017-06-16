
package model.etat;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jeu;

/**
 * Pattern Etat : matérialise un jeu lancé
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class JeuEnCours extends Etat {
    
    /**
     * Démarre une partie : initialisation du jeu + timer
     *
     * @param jeu à démarrer
     */
    @Override
    public void jouerJeu(Jeu jeu) {
        try {
            jeu.reinitialiserTimer();
            jeu.initialisation();
        } catch (InterruptedException ex) {
            Logger.getLogger(JeuEnCours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
