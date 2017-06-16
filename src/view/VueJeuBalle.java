package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Jeu;

/**
 * Vue matérialisant le jeu de balles
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class VueJeuBalle extends JPanel implements Observer
{
    protected List<VueTortue> tortues;
    public static int width = 600;
    public static int height = 400;
	
    public VueJeuBalle() {
        setBackground(Color.white);
        setSize(new Dimension(VueJeuBalle.width, VueJeuBalle.height));
        setPreferredSize(new Dimension(VueJeuBalle.width, VueJeuBalle.height));

        tortues = new ArrayList();
    }
    
    /**
     * Dessine les tortues dans le jeu de balles
     *
     * @param g graphique que l'on souhaite dessiner
     */
    public void montrerTortues(Graphics g) {
        for (VueTortue t : tortues) {
            t.dessinerTortue(g);
        }
    }
    
    /**
     * Ajoute une tortue dans la vue du jeu de balles
     *
     * @param tortue à ajouter
     */
    public void ajouterTortues(VueTortue tortue) {
        tortues.add(tortue);
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
        // La vue du jeu doit être réinitialisée
        if (o instanceof Jeu && ((Jeu)o).getTortues().isEmpty()) {
            tortues.clear();
        }
        this.repaint();
    }
}
