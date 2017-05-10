
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Iterator;
import model.Tortue;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class TortueView
{    
    protected ArrayList<Segment> listSegments; // Trace de la tortue
    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche

    protected Tortue tortue;

    public TortueView(Tortue tortue) {
        this.listSegments = new ArrayList();
        this.tortue = tortue;
    }
    
    public Tortue getTortue() {
        return tortue;
    }

    public void reset() {
        listSegments.clear();
    }

    public void drawTurtle(Graphics graphics) {
        if (graphics == null) return;

        // Dessine les segments<
        for (Iterator it = listSegments.iterator(); it.hasNext();) {
            Segment seg = (Segment) it.next();
            seg.drawSegment(graphics);
        }

        //Calcule les 3 coins du triangle a partir de
        // la position de la tortue p
        Point p = new Point(tortue.getX(), tortue.getY());
        Polygon arrow = new Polygon();

        //Calcule des deux bases
        //Angle de la droite
        double theta = Tortue.getRatioDegRad() * (-tortue.getDirection());
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
        graphics.setColor(Color.green);
        graphics.fillPolygon(arrow);
    }
}
