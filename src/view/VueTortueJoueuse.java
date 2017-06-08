
package view;

import java.awt.Graphics;
import java.util.Observable;
import model.TortueJoueuse;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class VueTortueJoueuse extends VueTortue {
    
    public VueTortueJoueuse(TortueJoueuse tortue) {
        super(tortue);        
    }

    @Override
    public void dessinerTortue(Graphics graphics) {
        super.dessinerTortue(graphics);
        
        if (graphics == null) return;
        
        graphics.drawString(((TortueJoueuse)tortue).getNom(), tortue.getX() + 10, tortue.getY() + 10);
    }
    
    @Override
    public void update(Observable o, Object arg) {}
}
