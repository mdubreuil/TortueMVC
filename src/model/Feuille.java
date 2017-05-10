
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
    private Tortue courante;
	
    public Feuille() {
        tortues = new ArrayList();
        courante = new Tortue();
        addTortue(courante);
    }
    
    public Feuille(Tortue courante) {
        tortues = new ArrayList();
        this.courante = courante;
        addTortue(courante);
    }
    
    public Tortue getTortue(int x, int y){
        for (Tortue t : tortues) {
            if ((x - 15 < t.getX()) && (t.getX() < x + 15) && (y - 15 < t.getY()) &&(t.getY() < y + 15)){
                System.out.println("nouvelle tortue");
                return t;
            }
        }
        return null;
    }

    public void addTortue(Tortue o) {
        if (!tortues.contains(o)) {
            tortues.add(o);
            this.setChanged();
            this.notifyObservers();
        }
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

    public Tortue getCourante() {
        return courante;
    }

    public void setCourante(Tortue courante) {
        this.courante = courante;
    }
}
