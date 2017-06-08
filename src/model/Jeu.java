package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public abstract class Jeu extends Observable {

    private List<Tortue> tortues; // la liste des tortues enregistrees
    private Tortue tortueCourante = null;

    public Jeu() {
        tortues = new ArrayList();
    }

    public Jeu(Tortue courante) {
        this();
        tortueCourante = courante;
        ajouterTortue(courante);
    }

    public Tortue getTortue(int x, int y){
        for (Tortue t : tortues) {
            if ((x - 15 < t.getX()) && (t.getX() < x + 15) && (y - 15 < t.getY()) &&(t.getY() < y + 15)){
                return t;
            }
        }
        return tortueCourante;
    }

    public void ajouterTortue(Tortue o) {
        if (!tortues.contains(o)) {
            tortues.add(o);
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void reinitialiser() {
        tortues.clear();
        this.setChanged();
        this.notifyObservers();
    }

    public List<Tortue> getTortues() {
        return tortues;
    }

    public Tortue getTortueCourante() {
        return tortueCourante;
    }

    public void setTortueCourante(Tortue courante) {
        if (tortueCourante != null) {
            tortueCourante.setCourante(false);
        }

        courante.setCourante(true);
        this.tortueCourante = courante;
    }
}
