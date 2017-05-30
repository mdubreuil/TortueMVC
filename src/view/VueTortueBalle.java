
package view;

import java.awt.Graphics;
import model.TortueBalle;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class VueTortueBalle extends VueTortue {
    
    public VueTortueBalle(TortueBalle tortue) {
        super(tortue);
    }

    @Override
    public void dessinerTortue(Graphics graphics) {
        if (graphics == null) return;

        int radius = 5;
        TortueBalle t = (TortueBalle) tortue;
        graphics.fillOval(t.getX() - radius, t.getY() - radius, 2 * radius, 2 * radius);
    }
}
