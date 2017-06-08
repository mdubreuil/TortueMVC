package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class StrategieIntelligente extends StrategieAleatoire {
    private final static int RAYON_X = 300;
    private final static int RAYON_Y = 300;
    private int[] coordonneesBalle;

    public int[] getCoordonneesBalle() {
        return coordonneesBalle;
    }

    public void setCoordonneesBalle(int[] coordonneesBalle) {
        this.coordonneesBalle = coordonneesBalle;
    }
        
    @Override
    public void deplacer(TortueJoueuse tortue) {
        calculerCoordonneesBalle(tortue);
        if(estPresDeLaBalle(tortue)){
            if(!tortue.estSuivie()){
                seRapprocherBalle(tortue);
            }
        } else {
           super.deplacer(tortue); 
        }
    }
    
    public void calculerCoordonneesBalle(TortueJoueuse t){
        int[] coordonneesBalle = new int[2];
        List<Tortue> lTortues = new ArrayList<>();
        lTortues = t.getTortuesConnues();
        for (Tortue tortue : lTortues){
            if(tortue instanceof TortueBalle){
                coordonneesBalle[0] = tortue.getX();
                coordonneesBalle[1] = tortue.getY();
            }            
        }
        this.setCoordonneesBalle(coordonneesBalle);
    }
    
    public boolean estPresDeLaBalle(TortueJoueuse t){
        System.out.println(t.getX());
        if((coordonneesBalle[0] <= t.getX() && coordonneesBalle[0] >= t.getX() - RAYON_X && coordonneesBalle[1] <= t.getY() && coordonneesBalle[1] >= t.getY() - RAYON_Y) ||
            (coordonneesBalle[0] <= t.getX() && coordonneesBalle[0] >= t.getX() - RAYON_X && coordonneesBalle[1] >= t.getY() && coordonneesBalle[1] <= t.getY() + RAYON_Y) ||
            (coordonneesBalle[0] >= t.getX() && coordonneesBalle[0] <= t.getX() + RAYON_X && coordonneesBalle[1] <= t.getY() && coordonneesBalle[1] >= t.getY() - RAYON_Y) ||
            (coordonneesBalle[0] >= t.getX() && coordonneesBalle[0] <= t.getX() + RAYON_X && coordonneesBalle[1] >= t.getY() && coordonneesBalle[1] <= t.getY() + RAYON_Y)){
            return true;
        }
        return false;
    }
    
    public void seRapprocherBalle(TortueJoueuse t){
        // La balle est en bas à gauche par rapport à la tortue
        if(coordonneesBalle[0] <= t.getX() && coordonneesBalle[0] >= t.getX() - RAYON_X && coordonneesBalle[1] <= t.getY() && coordonneesBalle[1] >= t.getY() - RAYON_Y){
            t.gauche(3*angle);
        }
        // La balle est en haut à gauche par rapport à la tortue
        if(coordonneesBalle[0] <= t.getX() && coordonneesBalle[0] >= t.getX() - RAYON_X && coordonneesBalle[1] >= t.getY() && coordonneesBalle[1] <= t.getY() + RAYON_Y){
            t.gauche(angle);
        }
        // La balle est en bas à droite par rapport à la tortue
        if(coordonneesBalle[0] >= t.getX() && coordonneesBalle[0] <= t.getX() + RAYON_X && coordonneesBalle[1] <= t.getY() && coordonneesBalle[1] >= t.getY() - RAYON_Y){
            t.droite(3*angle);
        }
        // La balle est en haut à droite par rapport à la tortue
        if(coordonneesBalle[0] >= t.getX() && coordonneesBalle[0] <= t.getX() + RAYON_X && coordonneesBalle[1] >= t.getY() && coordonneesBalle[1] <= t.getY() + RAYON_Y){
            t.droite(angle);
        }
        avancer(t);
    }
    
}
