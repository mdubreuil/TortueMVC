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
 * Modélise un jeu générique : ne comporte que des méthodes communes à tous les jeux
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public abstract class Jeu extends Observable
{
    private Timer timer = new Timer();
    private Etat etat = new JeuEnCours();
    private int duree = 0;
    private List<Tortue> tortues = new ArrayList();

    protected abstract TimerTask getTimerTask();
    protected abstract int getTimeIncrement();
    
    /**
     * Lance une partie de jeu
     */
    private void lancer() {
        this.etat.jouerJeu(this);
    }
    
    /**
     * Met une partie de jeu en pause
     */
    public void pause() {
        setEtat(new JeuEnPause());
    }
    
    /**
     * Lance une partie de jeu suite à pause
     */
    public void resume() {
        setEtat(new JeuEnCours());
    }
    
    /**
     * Arrête une partie de jeu
     */
    public void stop() {
        setEtat(new JeuFini());
    }
    
    /**
     * Crée le Timer du jeu lorsqu'une nouvelle partie est lancée
     * @throws java.lang.InterruptedException lorsque le Timer ne parvient pas à s'initialiser
     */
    public void initialisation() throws InterruptedException {
        timer = new Timer();
        timer.scheduleAtFixedRate(getTimerTask(), 0, getTimeIncrement());
    }
    
    /**
     * Réinitialise le jeu lorsqu'une partie est arrêtée : timer à zéro, suppression des tortues
     */
    public void reinitialiser() {
        duree = 0;
        tortues.clear();
        reinitialiserTimer();
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Met le timer en pause
     */
    public void reinitialiserTimer() {
        getTimerTask().cancel();
        timer.cancel();
        timer.purge();
    }
    
    /**
     * Récupère une tortue selon des coordonnées
     * @param x coordonnée X
     * @param y coordonnée y
     * @return Tortue : tortue correspondant aux coordonnées saisies s'il y en a une, null sinon
     */
    public Tortue getTortue(int x, int y) {
        for (Tortue t : tortues) {
            if ((x - 15 < t.getX()) && (t.getX() < x + 15) && (y - 15 < t.getY()) &&(t.getY() < y + 15)){
                return t;
            }
        }
        return null;
    }
    
    /**
     * Récupère l'état courant du jeu
     * 
     * @return Etat : etat courant du jeu
     */
    public Etat getEtat() {
        return etat;
    }
    
    /**
     * Modifie l'état courant du jeu
     * 
     * @param etat : etat du jeu à modifier
     */
    public void setEtat(Etat etat) {
        this.etat = etat;
        lancer();
    }
    
    /**
     * Récupère la durée depuis laquelle le jeu a été lancé
     * 
     * @return int : durée du jeu
     */
    public int getDuree() {
        return duree;
    }
    
    /**
     * Récupère l'objet Timer associé à la partie courante
     * 
     * @return Timer : objet timer associé à la partie courante
     */
    public Timer getTimer() {
        return timer;
    }
    
    /**
     * Incrémente la durée de la partie
     */
    public void incrementerDuree() {
        duree++;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Récupère la liste des tortues du jeu
     * 
     * @return listeTortues
     */
    public List<Tortue> getTortues() {
        return tortues;
    }
    
    /**
     * Associe une liste de tortues au jeu
     * 
     * @param tortues : listes des tortues à associer
     */
    public void setTortues(List<Tortue> tortues) {
        this.tortues = tortues;
    }
    
    /**
     * Ajoute une tortue à la liste des tortues du jeu
     * 
     * @param tortue : tortue à ajouter à la liste des tortues du jeu
     */
    public void ajouterTortue(Tortue tortue) {
        this.tortues.add(tortue);
    }
    
    /**
     * Supprime une tortue de la liste des tortues du jeu
     * 
     * @param tortue : tortue à supprimer de la liste des tortues du jeu
     */
    public void supprimerTortue(Tortue tortue) {
        this.tortues.remove(tortue);
    }
}
