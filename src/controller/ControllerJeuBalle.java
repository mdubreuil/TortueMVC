package controller;

import javax.swing.SwingUtilities;
import model.*;
import view.*;

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

    public ControllerJeuBalle(/*TortueFactory factory*/) {
        super();
        
        Tortue courante = new TortueJoueuse();
        jeu = new JeuBalle(courante);
        
        // Views
        vueTerrain = new VueJeuBalle();
        vueTerrain.addMouseListener(this);
        VueTortue vueTortueCourante = new VueTortueJoueuse((TortueJoueuse)courante);
        vueTerrain.ajouterTortues(vueTortueCourante);

        // Add listeners
        jeu.addObserver(vueTerrain);
        courante.addObserver(vueTerrain);
        
        vueFenetre = new VueJeu(this, vueTerrain, vueStrategie);
    }
}
