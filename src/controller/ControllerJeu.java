package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Jeu;
import view.VueJeu;
import view.VueJeuBalle;

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

    // Model    
    protected Jeu jeu = null;

    abstract public void initialisationJeu();

    public ControllerJeu() {
        // Views
        vueTerrain = new VueJeuBalle();
        vueTerrain.addMouseListener(this);
        vueTerrain.addKeyListener(this);

        vueFenetre = new VueJeu(this, vueTerrain);
    }
    
    public void start() {
        if (jeu == null) {
            System.out.println("Start");
            initialisationJeu();
        } else {
            System.out.println("Resume");
            jeu.resume();
        }
    }
    
    public void pause() {
        if (jeu != null) {
            System.out.println("Pause");
            jeu.pause();
        }
    }

    public void stop() {
        if (jeu != null) {
            System.out.println("Stop");
            jeu.stop();
            jeu = null;
        }
    }

    public VueJeu getVueFenetre() {
        return vueFenetre;
    }
    
    public Jeu getJeu() {
        return jeu;
    }

    public void quitter() {
        System.exit(0);
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
}