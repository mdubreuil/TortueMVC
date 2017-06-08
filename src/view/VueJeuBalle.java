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
public class VueJeuBalle extends JPanel implements Observer
{
    protected List<VueTortue> tortues; // la liste des sous-vues
    public static int width = 600;
    public static int height = 400;
	
    public VueJeuBalle() {
        setBackground(Color.white);
        setSize(new Dimension(VueJeuBalle.width, VueJeuBalle.height));
        setPreferredSize(new Dimension(VueJeuBalle.width, VueJeuBalle.height));

        tortues = new ArrayList();
    }

    public void montrerTortues(Graphics g) {
        for (VueTortue t : tortues) {
            t.dessinerTortue(g);
        }
    }

    public void ajouterTortues(VueTortue o) {
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

        montrerTortues(g);
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
