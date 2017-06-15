
package model.etat;

import model.Jeu;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */
public class JeuEnPause extends Etat {

    @Override
    public void jouerJeu(Jeu jeu) {
        jeu.reinitialiserTimer();
    }
}
