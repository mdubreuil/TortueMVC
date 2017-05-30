package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import model.*;
import view.*;

/**
 * Class Main = Controlleur
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class ControllerJeuBalle extends ControllerJeu implements MouseListener {

    private static TurtleSoccer fenetre = null;
    private JeuBalle jeuBalle = null;
    private VueStrategie strategie = null;
    private VueJeuBalle vueJeuBalle = null;
    private VueTortue tortueCourante = null;

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                ControllerJeuBalle controller = new ControllerJeuBalle();
            }
        });
    }
    
    public ControllerJeuBalle() {
        // Modèle
        Tortue courante = new Tortue();
        jeuBalle = new JeuBalle(courante);

        // Views
        strategie = new VueStrategie();
        vueJeuBalle = new VueJeuBalle();
        vueJeuBalle.addMouseListener(this);
        tortueCourante = new VueTortue(courante);
        vueJeuBalle.ajouterTortues(tortueCourante);

        // Add listeners
        jeuBalle.addObserver(vueJeuBalle);
        courante.addObserver(vueJeuBalle);
        
        fenetre = new TurtleSoccer(this, vueJeuBalle, strategie);
    }

    public void reinitialiserTortueCourante()
    {
        getCourante().reinitialiser();
    }

    public void changerCouleur(int n) {
        getCourante().setCouleur(n);
    }
    
    public void changerPosition(int x, int y) {
        getCourante().setPosition(x, y);
    }
    
    public void quitter() {
        System.exit(0);
    }
    
    public void avancer(int v) {
        getCourante().avancer(v);
    }
    
    public void droite(int v) {
        getCourante().droite(v);
    }
    
    public void gauche(int v) {
        getCourante().gauche(v);
    }
    
    public void reinitialiserJeu() {
        jeuBalle.reinitialiser();
    }
    
    // Temporary while no tortueController
    protected Tortue getCourante()
    {
        return jeuBalle.getTortueCourante();
    }
    
    protected void setCourante(Tortue tortue)
    {
        jeuBalle.ajouterTortue(tortue);
        jeuBalle.setTortueCourante(tortue);
    }
    
    public void ajouterTortue()
    {
        Tortue t = new Tortue();
        t.addObserver(vueJeuBalle);        
        VueTortue tView = new VueTortue(t);
        vueJeuBalle.ajouterTortues(tView);
        setCourante(t);
    }
    
    public void ajouterTortueJoueuse(String name)
    {
        TortueJoueuse t = new TortueJoueuse(name);

        for (Tortue tortue : jeuBalle.getTortues()) {
            t.ajouterTortue(tortue);
            
            if (tortue instanceof TortueJoueuse) {
                ((TortueJoueuse)tortue).ajouterTortue(t);
            }
        }
        
        t.addObserver(vueJeuBalle);
        VueTortue tView = new VueTortueJoueuse(t);
        vueJeuBalle.ajouterTortues(tView);
        setCourante(t);
    }
    
    public void ajouterTortueBalle()
    {
        TortueBalle t = new TortueBalle();
        t.addObserver(vueJeuBalle);        
        VueTortueBalle tView = new VueTortueBalle(t);
        vueJeuBalle.ajouterTortues(tView);
        setCourante(t);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        Tortue tortue = jeuBalle.getTortue(x, y);
        jeuBalle.setTortueCourante(tortue);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
