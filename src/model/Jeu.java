package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public abstract class Jeu extends Observable {

    protected List<Tortue> tortues; // la liste des tortues enregistrees
    protected Tortue tortueCourante = null;
    protected Timer timer = new Timer();

    public abstract void start();

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
        return null;
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
        // S'il y avait déjà une tortue courante, il faut lui dire qu'elle ne l'est plus
        if (tortueCourante != null) {
            tortueCourante.setCourante(false);
        }
        // Si la tortue est une balle, elle ne peut pas être la tortue courante
        if(courante instanceof TortueBalle) return;
        // Si l'utilisateur a bien sélectionné une tortue, on la met en tant que tortue courante
        if(courante != null){
            courante.setCourante(true);  
        }
        this.tortueCourante = courante;        
    }
}
