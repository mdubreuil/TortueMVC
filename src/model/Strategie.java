package model;

import controller.ControllerJeu;
import java.util.Random;
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
    protected Random rand = new Random();
    
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
        int x = tortue.getX(), y = tortue.getY();
        tortue.avancer(distance);
        while (!isPositionCorrecte(tortue)) {
            tortue.setPosition(x, y);
            tortue.setDirection(tortue.getDirection());
        }
    }

    private boolean isPositionCorrecte(Tortue tortue) {
        int x = tortue.getX();
        int y = tortue.getY();

        return (x >= xMin && x <= xMax && y >= yMin && y <= yMax);
    }

    private void ajusterPosition(Tortue tortue) {
        int x = tortue.getX();
        int y = tortue.getY();

        int inverseX = rand.nextInt() > 0 ? x : -x;
        int inverseY = rand.nextInt() > 0 ? y : -y;

        tortue.setPosition(inverseX, inverseY);
    }
}
