
package view;

import controller.ControllerJeu;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import model.Tortue;
import model.TortueJoueuse;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class VueTortueJoueuse extends VueTortue implements KeyListener {
    
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
    public void keyPressed(KeyEvent e) {
    System.out.println("keyboard pressed");}
    
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyboard types");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyboard");
        if (tortue == null) return;
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_KP_RIGHT:
                tortue.droite(ControllerJeu.angle);
                break;
            case KeyEvent.VK_KP_LEFT:
                tortue.gauche(ControllerJeu.angle);
                break;
            case KeyEvent.VK_KP_UP:
                tortue.avancer(ControllerJeu.distance);
                break;
            default:
                System.out.println("released key");
                break;
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Tortue) {
            Tortue joueuse = (Tortue) o;
            if (joueuse.isCourante()) {
                this.setFocusable(true);
                this.requestFocus();
            } else {
                this.setFocusable(false);
            }
        }
    }
}
