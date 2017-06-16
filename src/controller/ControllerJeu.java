package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Jeu;
import view.VueJeu;
import view.VueJeuBalle;

/**
 * Controller regroupant toutes les méthodes communes à tous les jeux : lien entre actions utilisateur et méthodes métier
 * 
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
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
    
     /**
     * Démarre une partie si le jeu n'a pas été lancé ou relance la partie si elle a été mise en pause
     */
    public void start() {
        if (jeu == null) {
            System.out.println("Start");
            initialisationJeu();
        } else {
            System.out.println("Resume");
            jeu.resume();
        }
    }
    
     /**
     * Met une partie en pause
     */
    public void pause() {
        if (jeu != null) {
            System.out.println("Pause");
            jeu.pause();
        }
    }
    
     /**
     * Arrête une partie
     */
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
    
     /**
     * Quitte le jeu si l'utilisateur clique sur le bouton quitter du menu
     */
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