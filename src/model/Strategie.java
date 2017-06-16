package model;

import controller.ControllerJeu;
import java.util.Random;
import view.VueJeuBalle;

/**
 * Pattern Stratégie : fait le lien avec les classes métier
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public abstract class Strategie {

    protected int xMax = VueJeuBalle.width;
    protected int yMax = VueJeuBalle.height;
    protected int xMin = 0, yMin = 0;
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
    
    /**
     * Fait avancer la tortue et ajuste sa position si elle est en dehors du terrain
     */
    public void avancer() {
        int x = tortue.getX(), y = tortue.getY();
        tortue.avancer(distance);
        while (!isPositionCorrecte()) {
            tortue.setPosition(x, y);
            tortue.setDirection(tortue.getDirection());
        }
    }
    
    /**
     * Détermine si la tortue est en dehors du terrain ou non
     * 
     * @return boolean : vrai si la position n'est pas en dehors du terrain de jeu, faux sinon
     */
    protected boolean isPositionCorrecte() {
        int x = tortue.getX();
        int y = tortue.getY();

        return (x >= xMin && x <= xMax && y >= yMin && y <= yMax);
    }
}
