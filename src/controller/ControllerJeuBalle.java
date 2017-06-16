package controller;

import factory.TortueBalleFactory;
import factory.TortueFactory;
import factory.TortueJoueuseFactory;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import model.*;

/**
 * Controller du jeu de balle : fait le lien avec les actions utilisateur de la vue du jeu de balles et les méthodes métier spécifiques au jeu de balles
 * 
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class ControllerJeuBalle extends ControllerJeu {

    /**
     * @param args arguments de la méthode main
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                ControllerJeuBalle controller = new ControllerJeuBalle();
            }
        });
    }
    
     /**
     * Retourne le jeu de balles courant
     *
     * @return JeuBalle
     */
    @Override
    public JeuBalle getJeu() {
        return (JeuBalle) jeu;
    }
    
     /**
     * Initialisation du jeu de balles : ajout des observateurs, des tortues
     */
    @Override
    public void initialisationJeu() {
        try {
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
            // La dernière tortue créée est une tortue intelligente
            TortueJoueuse tortue = (TortueJoueuse) factoryJoueuse.ajouterNouvelleTortue(this);
            tortue.setEtat(new StrategieIntelligente(tortue));
            this.ajouterTortue(tortue);
            vueFenetre.getVueStrategie().visibiliteListeTortues(true);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControllerJeuBalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Utilise la factory pour ajouter une nouvelle tortue au jeu
     *
     * @param factory factory s'occupant de l'ajout de tortue
     */
    public void ajouterTortue(TortueFactory factory)
    {
        Tortue tortue = factory.ajouterNouvelleTortue(this);
        getJeu().ajouterTortue(tortue);
    }
    
     /**
     * Ajoute une tortue au jeu de balles
     *
     * @param tortue Tortue à ajouter
     */
    public void ajouterTortue(Tortue tortue) {
        getJeu().ajouterTortue(tortue);
    }
    
     /**
     * Sélectionne la tortue courante suite à un clic utilisateur
     *
     * @param e Clic de souris de l'utilisateur
     */
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
    
     /**
     * Récupère la tortue courante du jeu
     *
     * @return tortue courante
     */
    protected TortueJoueuse getCourante()
    {
        return getJeu().getTortueCourante();
    }
    
     /**
     * Modifie la tortue courante du jeu
     *
     * @param tortue nouvelle tortue courante
     */
    protected void setCourante(TortueJoueuse tortue)
    {
        getJeu().setTortueCourante(tortue);
        vueTerrain.setFocusable(true);
        vueTerrain.requestFocus();
    }
    
     /**
     * Déplace la tortue en fonction d'une action clavier
     *
     * @param e touche du clavier appuyée par l'utilisateur
     */
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
