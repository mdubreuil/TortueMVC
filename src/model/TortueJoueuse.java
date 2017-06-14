package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 * 
 */

public class TortueJoueuse extends Tortue
{
    protected static int DUREE_POSSESSION_MIN = 10;
    protected static String DEFAULT_NAME = "Toto";
    protected static int cpt = 0;

    protected String nom;
    protected List<Tortue> tortuesConnues;
    protected Strategie etat;
    protected int distanceBalle = 30; // TODO static & majuscules
    protected List<TortueBalle> suiveurs;

    public TortueJoueuse(String name) {
        super();
        couleur = 1;
        nom = name;
        tortuesConnues = new ArrayList();
        suiveurs = new ArrayList();
        etat = new StrategieAleatoire(this);
        cpt++;
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

    public void setEtat(Strategie e) {
        this.etat = e;
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
        for (TortueBalle balle : suiveurs) {
            if (balle.getX() == this.getX() && balle.getY() == this.getY()){ // condition inutile normalement, grâce au pattern observer/observable (tout le temps vrai)
                return true;
            }
        }

        return false;
    }

    public List<TortueBalle> getSuiveurs() {
        return suiveurs;
    }
    
    public void setSuiveurCourant(TortueBalle balle) {
        this.suiveurs.clear();
        ajouterSuiveur(balle);
    }

    public void ajouterSuiveur(TortueBalle balle) {
        this.suiveurs.add(balle);
    }
    
    public void supprimerSuiveur(TortueBalle balle) {
        this.suiveurs.remove(balle);
    }

    public static void reinitialiserCpt() {
        cpt = 0;
    }

    private double getDistanceEuclidienne(Tortue tortue) {
        return Math.sqrt(Math.pow(this.getX() - tortue.getX(), 2) + Math.pow(this.getY() - tortue.getY(), 2));
    }

    @Override
    public String toString() {
        return getNom();
    }
}
