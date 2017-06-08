
package model;

import java.util.Random;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class StrategieAleatoire extends Strategie {

    protected Random rand = new Random();
    protected double seuilGauche = 33.33;
    protected double seuilDroite = 66.66;

    @Override
    public void deplacer(TortueJoueuse tortue) {
        double d = rand.nextDouble();
        if (d < seuilGauche) {
            tortue.gauche(angle);
        } else if (d < seuilDroite) {
            tortue.droite(angle);
        }
        avancer(tortue);
    }
}
