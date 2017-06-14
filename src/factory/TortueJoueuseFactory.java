
package factory;

import controller.ControllerJeu;
import java.util.Random;
import model.Jeu;
import model.Tortue;
import model.TortueJoueuse;
import view.VueJeu;
import view.VueJeuBalle;
import view.VueAdministration;
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
        VueJeuBalle vueTerrain = fenetre.getVueTerrain();
        VueAdministration vueStrategie = fenetre.getVueStrategie();
        Jeu terrain = controller.getJeu();

        // Modèle
        TortueJoueuse tortue = new TortueJoueuse();
        tortue.addObserver(vueTerrain);
        tortue.addObserver(vueStrategie);
        
        // Vues & listeners de vue
        VueTortueJoueuse tView = new VueTortueJoueuse(tortue);
        vueTerrain.ajouterTortues(tView);

        for (Tortue t : terrain.getTortues()) {
            tortue.ajouterTortue(t);
        }
        
        
        vueStrategie.ajouterTortue(tView);

        if (random) {
            // Random position
            Random rand = new Random();
            int x = rand.nextInt(VueJeuBalle.width);
            int y = rand.nextInt(VueJeuBalle.height);
            tortue.setPosition(x, y);
            
            // Random angle
            int nbRotation = rand.nextInt(8) + 1;
            for (int i = 0; i < nbRotation; i++) {
                tortue.droite(ControllerJeu.angle);
            }
        }
        
        return tortue;
    }
}
