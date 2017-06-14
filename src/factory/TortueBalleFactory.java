
package factory;

import controller.ControllerJeu;
import model.Tortue;
import model.TortueBalle;
import view.VueJeuBalle;
import view.VueTortueBalle;

/**
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class TortueBalleFactory implements TortueFactory {

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
