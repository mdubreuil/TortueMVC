
package view;

import java.awt.Graphics;
import java.util.Observable;
import model.TortueJoueuse;

/**
 * Correspondant à la vue de la tortue joueuse : Triangle pourvant être de plusieurs couleurs
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class VueTortueJoueuse extends VueTortue {
    
    public VueTortueJoueuse(TortueJoueuse tortue) {
        super(tortue);        
    }
    
    /**
     * Dessine une TortueJoueuse dans la vue du jeu
     *
     * @param graphics objet graphique à dessiner
     */
    @Override
    public void dessinerTortue(Graphics graphics) {
        super.dessinerTortue(graphics);
        
        if (graphics == null) return;
        
        graphics.drawString(((TortueJoueuse)tortue).getNom(), tortue.getX() + 10, tortue.getY() + 10);
    }
    
    @Override
    public void update(Observable o, Object arg) {}
}
