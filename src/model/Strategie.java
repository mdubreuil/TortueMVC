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
    private TortueJoueuse tortue;
    
    public abstract void deplacer(TortueJoueuse tortue);
    public abstract int getCouleurStrategie();
    
    public Strategie(TortueJoueuse tortue) {
        this.tortue = tortue;
        this.tortue.setCouleur(getCouleurStrategie());
    }

    public void avancer() {
//        if (tortue.getCouleur() != getCouleurStrategie()) {
//            tortue.setCouleur(getCouleurStrategie());
//        }

        int x = tortue.getX(), y = tortue.getY();
        tortue.avancer(distance);
        while (!isPositionCorrecte()) {
            tortue.setPosition(x, y);
            tortue.setDirection(tortue.getDirection());
        }
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
    }

    private boolean isPositionCorrecte() {
        int x = tortue.getX();
        int y = tortue.getY();

        return (x >= xMin && x <= xMax && y >= yMin && y <= yMax);
    }

    private void ajusterPosition() {
        int x = tortue.getX();
        int y = tortue.getY();

        int inverseX = rand.nextInt() > 0 ? x : -x;
        int inverseY = rand.nextInt() > 0 ? y : -y;

        tortue.setPosition(inverseX, inverseY);
    }
}
