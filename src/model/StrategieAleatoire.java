
package model;

/**
 * Pattern Stratégie : génère un déplacement de tortues aléatoire
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class StrategieAleatoire extends Strategie {

    protected double seuilDroite = 1./3;
    protected double seuilGauche = 2./3;

    public StrategieAleatoire(TortueJoueuse tortue) {
        super(tortue);
    }
    
    /**
     * Déplace une tortue de manière aléatoire dans l'espace de jeu
     *
     * @param tortue TortueJoueuse
     */
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
    
    /**
     * Retourne la couleure bleue pour les tortues se déplacant de manière aléatoire
     *
     * @return int couleurBleue
     */
    @Override
    public int getCouleurStrategie() {
        return 1;
    }
}
