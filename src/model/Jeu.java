package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public abstract class Jeu extends Observable
{
    private Timer timer = new Timer();
    private Etat etat = Etat.ARRETE;
    private int duree = 0;
    private List<Tortue> tortues = new ArrayList();

    public enum Etat {
        EN_COURS,
        EN_PAUSE,
        ARRETE
    }

    public abstract void run();

    public void pause() {
        setEtat(Etat.EN_PAUSE);
    }
    
    public void resume() {
        setEtat(Etat.EN_COURS);
    }

    public void stop() {
        setEtat(Etat.ARRETE);
        reinitialiser();
    }

    public void reinitialiser() {
        duree = 0;
        this.setChanged();
        this.notifyObservers();
    }
    
    public Tortue getTortue(int x, int y) {
        for (Tortue t : tortues) {
            if ((x - 15 < t.getX()) && (t.getX() < x + 15) && (y - 15 < t.getY()) &&(t.getY() < y + 15)){
                return t;
            }
        }

        return null;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public int getDuree() {
        return duree;
    }

    public Timer getTimer() {
        return timer;
    }

    public void incrementerDuree() {
        duree++;
        this.setChanged();
        this.notifyObservers();
    }

    public List<Tortue> getTortues() {
        return tortues;
    }

    public void setTortues(List<Tortue> tortues) {
        this.tortues = tortues;
    }
    
    public void ajouterTortue(Tortue tortue) {
        this.tortues.add(tortue);
    }
    
    public void supprimerTortue(Tortue tortue) {
        this.tortues.remove(tortue);
    }
}
