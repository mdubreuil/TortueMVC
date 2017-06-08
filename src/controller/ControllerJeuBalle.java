package controller;

import factory.TortueBalleFactory;
import factory.TortueFactory;
import factory.TortueJoueuseFactory;
import java.util.List;
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
                controller.initialisation();
            }
        });
    }

    @Override
    public void initialisation() {
        TortueFactory factoryJoueuse = new TortueJoueuseFactory(true);
        int nbJoueurs = ((JeuBalle) jeu).getNbJoueurs();
        for (int i = 0; i < nbJoueurs; i++) {
            Tortue tortue = factoryJoueuse.ajouterNouvelleTortue(this);
            this.ajouterTortue(tortue);
        }

        this.ajouterTortue(new TortueBalleFactory());
    }
}
