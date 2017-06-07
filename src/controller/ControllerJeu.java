package controller;

import factory.TortueFactory;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Jeu;
import model.JeuBalle;
import model.Tortue;
import view.VueJeu;
import view.VueJeuBalle;
import view.VueStrategie;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public abstract class ControllerJeu implements MouseListener, KeyListener {

    public static int angle = 45;
    public static int distance = 45;
    
    // View
    protected static VueJeu vueFenetre = null;
    protected VueJeuBalle vueTerrain = null;
    protected VueStrategie vueStrategie = null;

    // Model    
    protected Jeu jeu = null;
    
    abstract public void initialisation();
    
    public ControllerJeu() {
        vueStrategie = new VueStrategie();
        jeu = new JeuBalle();
        
        // Views
        vueTerrain = new VueJeuBalle();
        vueTerrain.addMouseListener(this);
        vueTerrain.addKeyListener(this);

        // Add listeners
        jeu.addObserver(vueTerrain);

        vueFenetre = new VueJeu(this, vueTerrain, vueStrategie);
    }

    public VueJeu getVueFenetre() {
        return vueFenetre;
    }
    
    public Jeu getJeu() {
        return jeu;
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
        jeu.reinitialiser();
    }

    protected Tortue getCourante()
    {
        return jeu.getTortueCourante();
    }
    
    protected void setCourante(Tortue tortue)
    {
        jeu.setTortueCourante(tortue);
    }

    public void reinitialiserTortueCourante()
    {
        getCourante().reinitialiser();
    }
    
    public void ajouterTortue(TortueFactory factory)
    {
        Tortue tortue = factory.ajouterNouvelleTortue(this);
        jeu.ajouterTortue(tortue);
//        setCourante(tortue);
    }

    public void ajouterTortue(Tortue tortue) {
        jeu.ajouterTortue(tortue);
//        setCourante(tortue);
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
        if (getCourante() == null) return;
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_KP_RIGHT:
                getCourante().droite(angle);
                break;
            case KeyEvent.VK_KP_LEFT:
                getCourante().gauche(angle);
                break;
            case KeyEvent.VK_KP_UP:
                getCourante().avancer(distance);
                break;
            default:
                System.out.println("released key");
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        Tortue tortue = jeu.getTortue(x, y);
        setCourante(tortue);
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
