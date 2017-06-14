package controller;

import factory.TortueBalleFactory;
import factory.TortueFactory;
import factory.TortueJoueuseFactory;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import model.*;

/**
 * Class Main = Controlleur
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class ControllerJeuBalle extends ControllerJeu {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                ControllerJeuBalle controller = new ControllerJeuBalle(/*new TortueJoueuseFactory()*/);
            }
        });
    }

    @Override
    public void initialisationJeu() {
        jeu = new JeuBalle();
        jeu.addObserver(vueTerrain);
        jeu.addObserver(vueFenetre);
        
        // Ajout de la balle en premier pour que toutes les tortues connaissent la balle
        this.ajouterTortue(new TortueBalleFactory());
        
        // Creation des autres tortues
        TortueFactory factoryJoueuse = new TortueJoueuseFactory(true);
        int nbJoueurs = ((JeuBalle) jeu).getNbJoueurs();
        for (int i = 0; i < nbJoueurs; i++) {
            Tortue tortue = factoryJoueuse.ajouterNouvelleTortue(this);
            this.ajouterTortue(tortue);
        }
        TortueJoueuse tortue = (TortueJoueuse) factoryJoueuse.ajouterNouvelleTortue(this);
        tortue.setEtat(new StrategieIntelligente());
        tortue.setCouleur(5);
        this.ajouterTortue(tortue);
    }
    
    public void ajouterTortue(TortueFactory factory)
    {
        Tortue tortue = factory.ajouterNouvelleTortue(this);
        getJeu().ajouterTortue(tortue);
//        setCourante(tortue);
    }

    public void ajouterTortue(Tortue tortue) {
        getJeu().ajouterTortue(tortue);
//        setCourante(tortue);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        Tortue tortue = getJeu().getTortue(x, y);
        
        if (tortue == null) {
            setCourante(null);
        } else if (tortue instanceof TortueJoueuse) {
            setCourante((TortueJoueuse) tortue);
        }
    }
    
    protected TortueJoueuse getCourante()
    {
        return getJeu().getTortueCourante();
    }
    
    protected void setCourante(TortueJoueuse tortue)
    {
        getJeu().setTortueCourante(tortue);
        vueTerrain.setFocusable(true);
        vueTerrain.requestFocus();
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
    
    @Override
    public JeuBalle getJeu() {
        return (JeuBalle) jeu;
    }
}
