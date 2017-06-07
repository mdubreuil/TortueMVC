
package factory;

import controller.ControllerJeu;
import model.Tortue;

/**
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public interface TortueFactory {
    public Tortue ajouterNouvelleTortue(ControllerJeu controller);
}
