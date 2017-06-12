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
    
    abstract public void initialisationJeu();

    public ControllerJeu() {
        vueStrategie = new VueStrategie();

        // Views
        vueTerrain = new VueJeuBalle();
        vueTerrain.addMouseListener(this);
        vueTerrain.addKeyListener(this);

        vueFenetre = new VueJeu(this, vueTerrain, vueStrategie);
    }
    
    public void start() {
        if (jeu == null) {
            initialisationJeu();
            jeu.run();
        } else {
            System.out.println("Resume");
            jeu.resume();
        }
    }
    
    public void pause() {
        System.out.println("Pause");
        jeu.pause();
    }

    public void stop() {
        System.out.println("Stop");
        jeu.stop();
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
        vueTerrain.setFocusable(true);
        vueTerrain.requestFocus();
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
    
    @Override
    public void keyPressed(KeyEvent e) {}
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        Tortue tortue = getCourante();
        if (tortue == null) return;
        switch (e.getKeyCode()) {            
            case KeyEvent.VK_RIGHT:
                tortue.droite(ControllerJeu.angle);
                break;
            case KeyEvent.VK_LEFT:
                tortue.gauche(ControllerJeu.angle);
                break;
            case KeyEvent.VK_UP:
                tortue.avancer(ControllerJeu.distance);
                break;
            default:
                break;
        }
    }
}
