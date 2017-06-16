
package factory;

import controller.ControllerJeu;
import model.Tortue;

/**
 * Pattern Factory
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public interface TortueFactory {
    public Tortue ajouterNouvelleTortue(ControllerJeu controller);
}
