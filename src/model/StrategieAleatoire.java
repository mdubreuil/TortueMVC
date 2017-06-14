
package model;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class StrategieAleatoire extends Strategie {

    protected double seuilDroite = 1./3;
    protected double seuilGauche = 2./3;

    public StrategieAleatoire(TortueJoueuse tortue) {
        super(tortue);
    }

    @Override
    public void deplacer(TortueJoueuse tortue) {
        double random = rand.nextDouble();

        if (random <= seuilDroite) {
            tortue.droite(angle);
        } else if (random <= seuilGauche) {
            tortue.gauche(angle);
        }

        avancer();
    }

    @Override
    public int getCouleurStrategie() {
        return 1;
    }
}
