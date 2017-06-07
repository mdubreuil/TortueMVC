
package factory;

import controller.ControllerJeu;
import java.util.Random;
import model.Jeu;
import model.Tortue;
import model.TortueJoueuse;
import view.VueJeu;
import view.VueJeuBalle;
import view.VueTortue;
import view.VueTortueJoueuse;

/**
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class TortueJoueuseFactory implements TortueFactory {

    private boolean random = false;

    public TortueJoueuseFactory() {
    }

    public TortueJoueuseFactory(boolean random) {
        this.random = random;
    }

    @Override
    public Tortue ajouterNouvelleTortue(ControllerJeu controller) {
        VueJeu fenetre = controller.getVueFenetre();
        VueJeuBalle vueFeuille = fenetre.getVueTerrain();
        Jeu terrain = controller.getJeu();

        TortueJoueuse tortue = new TortueJoueuse(/*fenetre.getTortueName()*/); // TODO change
        tortue.addObserver(vueFeuille);
        VueTortue tView = new VueTortueJoueuse(tortue);
        vueFeuille.ajouterTortues(tView);

        for (Tortue t : terrain.getTortues()) {
            tortue.ajouterTortue(t);
            
            if (t instanceof TortueJoueuse) {
                ((TortueJoueuse)t).ajouterTortue(tortue);
            }
        }

        if (random) {
            // Random position
            Random rand = new Random();
            int x = rand.nextInt(VueJeuBalle.width);
            int y = rand.nextInt(VueJeuBalle.height);
            tortue.setPosition(x, y);
            
            int nbRotation = rand.nextInt(8) + 1;
            for (int i = 0; i < nbRotation; i++) {
                tortue.droite(ControllerJeu.angle);
            }
        }

        return tortue;
    }
}
