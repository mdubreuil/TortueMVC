
package factory;

import controller.ControllerJeu;
import model.Tortue;
import model.TortueBalle;
import view.VueJeuBalle;
import view.VueTortueBalle;

/**
 * Pattern Factory : instancie des balles dans le jeu de balles
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class TortueBalleFactory implements TortueFactory {
    
     /**
     * Ajoute une nouvelle balle (modèle et vue) + observateurs au controller de jeu
     *
     * @param controller dans lequel on souhaite ajouter une balle
     */
    @Override
    public Tortue ajouterNouvelleTortue(ControllerJeu controller) {
        VueJeuBalle terrain = controller.getVueFenetre().getVueTerrain();

        TortueBalle tortue = new TortueBalle();
        tortue.addObserver(terrain);
        tortue.addObserver(controller.getVueFenetre().getVueStrategie());

        VueTortueBalle tView = new VueTortueBalle(tortue);
        terrain.ajouterTortues(tView);
        
        return tortue;
    }
}
