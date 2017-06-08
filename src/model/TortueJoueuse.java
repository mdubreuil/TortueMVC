package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 * 
 */

public class TortueJoueuse extends Tortue {

    public static String DEFAULT_NAME = "Toto";
    public static int cpt = 0;

    protected String nom;
    protected List<Tortue> tortuesConnues;
    protected Strategie etat;
    protected int distanceBalle = 30;

    public TortueJoueuse(String name) {
        nom = name;
        tortuesConnues = new ArrayList();
        etat = new StrategieAleatoire();
        ++cpt;
    }

    public TortueJoueuse() {
        this(DEFAULT_NAME + cpt);
    }
    
    public void seDeplacer() {
        etat.deplacer(this);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        this.setChanged();
        this.notifyObservers();
    }

    public void setTortuesConnues(List<Tortue> tortuesConnues) {
        this.tortuesConnues = tortuesConnues;
    }

    public void setEtat(Strategie etat) {
        this.etat = etat;
        this.setChanged();
        this.notifyObservers();
    }

    public Strategie getEtat() {
        return etat;
    }

    public void ajouterTortue(Tortue tortue) {
        this.tortuesConnues.add(tortue);
    }
    
    public void enleverTortue(Tortue tortue) {
        this.tortuesConnues.remove(tortue);
    }
    
    public void reinitialiserTortuesConnues() {
        this.tortuesConnues.clear();
    }

    public List<Tortue> getTortuesConnues() {
        return tortuesConnues;
    }

    @Override
    public void avancer(int dist) {
        super.avancer(dist);

        for (Tortue tortue : tortuesConnues) {
            if (tortue instanceof TortueBalle && getDistanceEuclidienne(tortue) < distanceBalle) {
                TortueBalle balle = (TortueBalle) tortue;
                balle.setTortueSuivie(this);
            }
        }
    }
    public boolean estSuivie(){
        for (Tortue tortue : tortuesConnues) {
            if (tortue instanceof TortueBalle) {
                TortueBalle balle = (TortueBalle) tortue;
                if(balle.getX() == this.getX() && balle.getY() == this.getY()){
                    return true;
                }
            }
        }
        return false;
    }
    
    private double getDistanceEuclidienne(Tortue tortue) {
        return Math.sqrt(Math.pow(this.getX() - tortue.getX(), 2) + Math.pow(this.getY() - tortue.getY(), 2));
    }

    @Override
    public String toString() {
        return nom;
    }
}
