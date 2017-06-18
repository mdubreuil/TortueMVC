
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
 * Pattern stratégie : instancie des tortues joueuses dans le jeu de balles
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class TortueJoueuseFactory implements TortueFactory {

    private boolean aleatoire = false;

    public TortueJoueuseFactory() {
    }

    public TortueJoueuseFactory(boolean random) {
        this.aleatoire = random;
    }
    
     /**
     * Ajoute des tortues (modèle et vue) + observateurs au controller de jeu
     * Affecte également une position aléatoire
     * 
     * @param controller dans lequel on souhaite ajouter une totue
     */
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

        if (aleatoire) {
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
