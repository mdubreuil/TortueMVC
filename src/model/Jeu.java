package model;

import model.etat.JeuFini;
import model.etat.JeuEnCours;
import model.etat.Etat;
import model.etat.JeuEnPause;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public abstract class Jeu extends Observable
{
    private Timer timer = new Timer();
    private Etat etat = new JeuEnCours();
    private int duree = 0;
    private List<Tortue> tortues = new ArrayList();

    protected abstract TimerTask getTimerTask();
    protected abstract int getTimeIncrement();

    private void lancer() {
        this.etat.jouerJeu(this);
    }

    public void pause() {
        setEtat(new JeuEnPause());
    }
    
    public void resume() {
        setEtat(new JeuEnCours());
    }

    public void stop() {
        setEtat(new JeuFini());
    }
    
    public void initialisation() throws InterruptedException {
        timer = new Timer();
        timer.scheduleAtFixedRate(getTimerTask(), 0, getTimeIncrement());
    }

    public void reinitialiser() {
        duree = 0;
        tortues.clear();
        reinitialiserTimer();
        this.setChanged();
        this.notifyObservers();
    }
    
    public void reinitialiserTimer() {
        getTimerTask().cancel();
        timer.cancel();
        timer.purge();
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
        lancer();
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
