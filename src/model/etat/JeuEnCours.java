
package model.etat;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Jeu;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */
public class JeuEnCours extends Etat {

    @Override
    public void jouerJeu(Jeu jeu) {
        try {
            jeu.initialisation();
        } catch (InterruptedException ex) {
            Logger.getLogger(JeuEnCours.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
