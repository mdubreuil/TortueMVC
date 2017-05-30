package model;

import java.util.ArrayList;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */

public class JeuBalle extends Jeu {
    
    private ArrayList<Tortue> tortues; // la liste des tortues enregistrees
    private Tortue tortueCourante;
	
    public JeuBalle() {
        tortues = new ArrayList();
        tortueCourante = new Tortue();
        ajouterTortue(tortueCourante);
    }
    
    public JeuBalle(Tortue courante) {
        tortues = new ArrayList();
        this.tortueCourante = courante;
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

    public ArrayList<Tortue> getTortues() {
        return tortues;
    }

    public Tortue getTortueCourante() {
        return tortueCourante;
    }

    public void setTortueCourante(Tortue courante) {
        this.tortueCourante = courante;
    }
}
