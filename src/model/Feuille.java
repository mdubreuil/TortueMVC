
package model;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class Feuille extends Observable {
    
    private ArrayList<Tortue> tortues; // la liste des tortues enregistrees
	
    public Feuille() {
        tortues = new ArrayList();
    }

    public void addTortue(Tortue o) {
        tortues.add(o);
        this.setChanged();
        this.notifyObservers();
    }

    public void reset() {
        for (Tortue t : tortues) {
            t.reset();
        }
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<Tortue> getTortues() {
        return tortues;
    }
}
