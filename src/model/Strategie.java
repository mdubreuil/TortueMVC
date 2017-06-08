package model;

import controller.ControllerJeu;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.VueJeuBalle;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public abstract class Strategie {

    public int xMax = VueJeuBalle.width;
    public int yMax = VueJeuBalle.height;
    public int xMin = 0, yMin = 0;
    public int distance = ControllerJeu.distance;
    public int angle = ControllerJeu.angle;
    
    public abstract void deplacer(TortueJoueuse tortue);
    
    public void avancer(Tortue tortue) {
//        try {
//            Tortue t;
//            do {
//                t = tortue.clone();
//                t.avancer(distance);
//            } while (isPositionCorrecte(t));
//            tortue.setPosition(t.getX(), t.getY());
//        } catch (CloneNotSupportedException ex) {
//            Logger.getLogger(Strategie.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        tortue.avancer(distance);
        if (isPositionCorrecte(tortue)) {
            return;
        }
        
        ajusterPosition(tortue);
    }

    private boolean isPositionCorrecte(Tortue tortue) {
        int x = tortue.getX();
        int y = tortue.getY();

        return (x >= xMin && x <= xMax && y >= yMin && y <= yMax);
    }

    private void ajusterPosition(Tortue tortue) {
        int x = tortue.getX();
        int y = tortue.getY();

        if (x > xMax) {
            x = xMax;
        } else if (x < xMin) {
            x = xMin;
        }

        if (y > yMax) {
            y = yMax;
        } else if (y < yMin) {
            y = yMin;
        }

        tortue.setPosition(x, y);
    }

    
}
