package model;

import java.util.List;
import static java.lang.Math.abs;

/**
 * Pattern stratégie : rapprochement de cette dernière si elle est près de la balle,
 * aléatoirement sinon
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */

public class StrategieIntelligente extends StrategieAleatoire {

    private final static int RAYON_X = 250;
    private final static int RAYON_Y = 250;
    private int[] coordonneesBalle;
    protected int[] positionBalle;

    public StrategieIntelligente(TortueJoueuse tortue) {
        super(tortue);
    }
    
    @Override
    public int getCouleurStrategie() {
        return 5;
    }

    public int[] getCoordonneesBalle() {
        return coordonneesBalle;
    }

    public void setCoordonneesBalle(int[] coordonneesBalle) {
        this.coordonneesBalle = coordonneesBalle;
    }
    
     /**
     * Déplace la t selon une stratégie intelligente : se rapproche de la balle si elle est dans son champ de vision,
 sinon se déplace de manière aléatoire
     *
     * @param tortue TortueJoueuse à déplacer
     */
    @Override
    public void deplacer(TortueJoueuse tortue) {
        calculerCoordonneesBalle(tortue);
        if(estPresDeLaBalle(tortue) && !tortue.estSuivie()){
            seRapprocherBalle(tortue);
        } else {
           super.deplacer(tortue); 
        }
    }
    
     /**
     * Calcule les coordonnées de la balle
     *
     * @param tortue TortueJoueuse
     */
    public void calculerCoordonneesBalle(TortueJoueuse tortue){
        int[] balle = new int[2];
        List<Tortue> lTortues = tortue.getTortuesConnues();
        for (Tortue t : lTortues){
            if(t instanceof TortueBalle){
                balle[0] = t.getX();
                balle[1] = t.getY();
            }            
        }
        this.setCoordonneesBalle(balle);
    }
    
     /**
     * Détermine si la TortueJoueuse est près d'une balle et dans quelle partie de son champ de vision elle se situe :
     * 1 = en bas à gauche
     * 2 = en haut à gauche
     * 3 = en bas à droite
     * 4 = en bas à gauche
     *
     * @param tortue TortueJoueuse
     * @return vrai si la tortue est près de la balle, faux sinon
     */
    public boolean estPresDeLaBalle(TortueJoueuse tortue){
        positionBalle = new int[1];        
        if(coordonneesBalle[0] <= tortue.getX() && coordonneesBalle[0] >= tortue.getX() - RAYON_X && coordonneesBalle[1] >= tortue.getY() && coordonneesBalle[1] <= tortue.getY() + RAYON_Y) {
            // La balle est en bas à gauche par rapport à la tortue
            positionBalle[0] = 1;
            return true;
        } else if(coordonneesBalle[0] <= tortue.getX() && coordonneesBalle[0] >= tortue.getX() + RAYON_X && coordonneesBalle[1] >= tortue.getY() && coordonneesBalle[1] <= tortue.getY() + RAYON_Y){
            // La balle est en haut à gauche par rapport à la tortue
            positionBalle[0] = 2;
            return true;
        } else if(coordonneesBalle[0] >= tortue.getX() && coordonneesBalle[0] <= tortue.getX() + RAYON_X && coordonneesBalle[1] >= tortue.getY() && coordonneesBalle[1] <= tortue.getY() + RAYON_Y) {
            // La balle est en bas à droite par rapport à la tortue
            positionBalle[0] = 3;
            return true;
        } else if (coordonneesBalle[0] >= tortue.getX() && coordonneesBalle[0] <= tortue.getX() + RAYON_X && coordonneesBalle[1] >= tortue.getY() - RAYON_Y && coordonneesBalle[1] <= tortue.getY()){
            // La balle est en haut à droite par rapport à la tortue
            positionBalle[0] = 4;
            return true;
        }
        return false;
    }
    
     /**
     * Rapproche la tortue de la position de la balle
     *
     * @param tortue TortueJoueuse à déplacer
     */
    public void seRapprocherBalle(TortueJoueuse tortue){
        // On réinitialise la direction de la tortue pour faciliter les calculs
        tortue.setDirection(-90);
        
        // Calcul de l'angle par rapport aux coordonées de la tortue et de la balle
        double coordonneeX = coordonneesBalle[0]-tortue.getX();
        double coordonneeY = coordonneesBalle[1]-tortue.getY();
        int degree = (int) Math.toDegrees(Math.atan(coordonneeX/coordonneeY));
        
        switch(positionBalle[0]){
            case 1:
                tortue.droite(abs(degree) + 180);
                break;
            case 3 :
                tortue.droite(abs(degree) + 90);
                break;
            case 2 :
                tortue.gauche(abs(degree));
                break;
            case 4 :
                tortue.droite(abs(degree));
                break;
            default :
                break;
        }

        avancer();
    }
}
