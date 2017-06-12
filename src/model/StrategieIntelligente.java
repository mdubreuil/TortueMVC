package model;

import static java.lang.Math.abs;
import java.util.List;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class StrategieIntelligente extends StrategieAleatoire {
    private final static int RAYON_X = 250;
    private final static int RAYON_Y = 250;
    private int[] coordonneesBalle;
    private int[] positionBalle;

    public int[] getCoordonneesBalle() {
        return coordonneesBalle;
    }

    public void setCoordonneesBalle(int[] coordonneesBalle) {
        this.coordonneesBalle = coordonneesBalle;
    }
        
    @Override
    public void deplacer(TortueJoueuse tortue) {
        calculerCoordonneesBalle(tortue);
        if(estPresDeLaBalle(tortue) && !tortue.estSuivie()){
            seRapprocherBalle(tortue);
        } else {
           super.deplacer(tortue); 
        }
    }
    
    public void calculerCoordonneesBalle(TortueJoueuse t){
        int[] balle = new int[2];
        List<Tortue> lTortues = t.getTortuesConnues();
        for (Tortue tortue : lTortues){
            if(tortue instanceof TortueBalle){
                balle[0] = tortue.getX();
                balle[1] = tortue.getY();
            }            
        }
        this.setCoordonneesBalle(balle);
    }
    
    public boolean estPresDeLaBalle(TortueJoueuse t){
        positionBalle = new int[1];        
        if(coordonneesBalle[0] <= t.getX() && coordonneesBalle[0] >= t.getX() - RAYON_X && coordonneesBalle[1] >= t.getY() && coordonneesBalle[1] <= t.getY() + RAYON_Y) {
            // La balle est en bas à gauche par rapport à la tortue
            positionBalle[0] = 1;
            return true;
        } else if(coordonneesBalle[0] <= t.getX() && coordonneesBalle[0] >= t.getX() + RAYON_X && coordonneesBalle[1] >= t.getY() && coordonneesBalle[1] <= t.getY() + RAYON_Y){
            // La balle est en haut à gauche par rapport à la tortue
            positionBalle[0] = 2;
            return true;
        } else if(coordonneesBalle[0] >= t.getX() && coordonneesBalle[0] <= t.getX() + RAYON_X && coordonneesBalle[1] >= t.getY() && coordonneesBalle[1] <= t.getY() + RAYON_Y) {
            // La balle est en bas à droite par rapport à la tortue
            positionBalle[0] = 3;
            return true;
        } else if (coordonneesBalle[0] >= t.getX() && coordonneesBalle[0] <= t.getX() + RAYON_X && coordonneesBalle[1] >= t.getY() - RAYON_Y && coordonneesBalle[1] <= t.getY()){
            // La balle est en haut à droite par rapport à la tortue
            positionBalle[0] = 4;
            return true;
        }
        return false;
    }
    
    public void seRapprocherBalle(TortueJoueuse t){
        t.setDirection(-90);
        double coordonneeX = coordonneesBalle[0]-t.getX();
        double coordonneeY = coordonneesBalle[1]-t.getY();
        int degree = (int) Math.toDegrees(Math.atan(coordonneeX/coordonneeY));
        
        switch(positionBalle[0]){
            case 1:
                t.droite(abs(degree) + 180);
                break;
            case 3 :
                t.droite(abs(degree)+90);
                break;
            case 2 :
                t.gauche(abs(degree));
                break;
            case 4 :
                t.droite(abs(degree));
                break;
            default :
                break;
        }
        avancer(t);
        
    }
    
}
