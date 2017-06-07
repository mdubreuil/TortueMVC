
package factory;

import controller.ControllerJeu;
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
        
        return tortue;
    }
}
