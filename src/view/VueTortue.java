
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;
import model.Tortue;

/**
 * Vue générique pour une tortue : triangle plein
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public abstract class VueTortue extends JComponent implements Observer
{    
    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche

    protected Tortue tortue;

    public VueTortue(Tortue tortue) {
        this.tortue = tortue;
    }
    
    public Tortue getTortue() {
        return tortue;
    }
    
    /**
     * Dessine une Tortue dans la vue du jeu
     *
     * @param graphics graphique que l'on souhaite dessiner
     */
    public void dessinerTortue(Graphics graphics) {
        if (graphics == null) return;

        //Calcule les 3 coins du triangle a partir de
        // la position de la tortue p
        Point p = new Point(tortue.getX(), tortue.getY());
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta = Tortue.ratioDegRad * (-tortue.getDirection());
        //Demi angle au sommet du triangle
        double alpha = Math.atan((float) rb / (float) rp);
        //Rayon de la fleche
        double r = Math.sqrt(rp * rp + rb * rb);
        //Sens de la fleche

        //Pointe
        Point p2=new Point((int) Math.round(p.x+r*Math.cos(theta)), 
            (int) Math.round(p.y-r*Math.sin(theta)));
        arrow.addPoint(p2.x,p2.y);
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
          (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

        //Base2
        arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
          (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

        arrow.addPoint(p2.x,p2.y);
        graphics.setColor(decoderCouleur(tortue.getCouleur()));
        graphics.fillPolygon(arrow);
    }
    
    /**
     * Associe une couleur à un entier
     *
     * @param c couleur à décoder
     * @return objet Color
     */
    public Color decoderCouleur(int c) {
        switch(c) {
            case 0: return(Color.black);
            case 1: return(Color.blue);
            case 2: return(Color.cyan);
            case 3: return(Color.darkGray);
            case 4: return(Color.red);
            case 5: return(Color.green);
            case 6: return(Color.lightGray);
            case 7: return(Color.magenta);
            case 8: return(Color.orange);
            case 9: return(Color.gray);
            case 10: return(Color.pink);
            case 11: return(Color.yellow);
            default : return(Color.black);
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {}
}
