
package model;

import java.util.ArrayList;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class JeuBalle extends Jeu {
    
    private ArrayList<Tortue> tortues; // la liste des tortues enregistrees
    private Tortue courante;
	
    public JeuBalle() {
        tortues = new ArrayList();
        courante = new Tortue();
        addTortue(courante);
    }
    
    public JeuBalle(Tortue courante) {
        tortues = new ArrayList();
        this.courante = courante;
        addTortue(courante);
    }
    
    public Tortue getTortue(int x, int y){
        for (Tortue t : tortues) {
            if ((x - 15 < t.getX()) && (t.getX() < x + 15) && (y - 15 < t.getY()) &&(t.getY() < y + 15)){
                return t;
            }
        }
        return courante;
    }

    public void addTortue(Tortue o) {
        if (!tortues.contains(o)) {
            tortues.add(o);
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void reset() {
//        for (Tortue t : tortues) {
//            t.reset();
//        }
        tortues.clear();
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
