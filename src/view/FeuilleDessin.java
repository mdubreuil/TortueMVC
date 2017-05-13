
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.JeuBalle;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class FeuilleDessin extends JPanel implements Observer
{
    protected List<TortueView> tortues; // la liste des sous-vues
	
    public FeuilleDessin() {
        setBackground(Color.white);
        setSize(new Dimension(600,400));
        setPreferredSize(new Dimension(600,400));
        
        tortues = new ArrayList();
    }

    public void showTurtles(Graphics g) {
        for (TortueView t : tortues) {
            t.drawTurtle(g);
        }
    }

    public void addTortue(TortueView o) {
        tortues.add(o);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Color c = g.getColor();
        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0,0,dim.width, dim.height);
        g.setColor(c);

        showTurtles(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        // Test reset
        if (o instanceof JeuBalle && ((JeuBalle)o).getTortues().isEmpty()) {
            tortues.clear();
        }

        this.repaint();
    }
}
