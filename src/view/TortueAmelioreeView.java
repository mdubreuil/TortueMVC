
package view;

import java.awt.Graphics;
import model.TortueJoueuse;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class TortueAmelioreeView extends TortueView {
    
    public TortueAmelioreeView(TortueJoueuse tortue) {
        super(tortue);
    }

    @Override
    public void drawTurtle(Graphics graphics) {
        super.drawTurtle(graphics);
        
        if (graphics == null) return;
        
        graphics.drawString(((TortueJoueuse)tortue).getName(), tortue.getX() + 10, tortue.getY() + 10);
    }
}
