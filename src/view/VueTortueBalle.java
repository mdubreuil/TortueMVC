
package view;

import java.awt.Graphics;
import model.TortueBalle;

/**
 * Correspondant à la vue de la tortue joueuse : Cercle plein
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class VueTortueBalle extends VueTortue {
    
    public VueTortueBalle(TortueBalle tortue) {
        super(tortue);
        setFocusable(false);
    }
    
    /**
     * Dessine une TortueBalle dans la vue du jeu
     *
     * @param graphics composant graphique que l'on souhaite dessiner
     */
    @Override
    public void dessinerTortue(Graphics graphics) {
        if (graphics == null) return;

        int radius = 5;
        TortueBalle t = (TortueBalle) tortue;
        graphics.fillOval(t.getX() - radius, t.getY() - radius, 2 * radius, 2 * radius);
    }
}
