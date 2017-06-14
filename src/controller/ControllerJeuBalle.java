package controller;

import factory.TortueBalleFactory;
import factory.TortueFactory;
import factory.TortueJoueuseFactory;
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
        tortue.setEtat(new StrategieIntelligente());
        tortue.setCouleur(5);
        this.ajouterTortue(tortue);
    }
}
