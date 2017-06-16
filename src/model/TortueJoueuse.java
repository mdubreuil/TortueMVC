package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Correspondant à une tortue joueuse : peut jouer avec la balle et changer de stratégie
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class TortueJoueuse extends Tortue
{
    protected static int DUREE_POSSESSION_MIN = 10;
    protected static String DEFAULT_NAME = "Toto";
    protected static int indiceNomTortue = 0;

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
        indiceNomTortue++;
    }

    public TortueJoueuse() {
        this(DEFAULT_NAME + indiceNomTortue);
    }
    
    /**
     * Déplace une tortue selon sa stratégie
     *
     */
    public void seDeplacer() {
        etat.deplacer(this);
    }
    
    /**
     * Récupère le nom de la tortue joueuse
     *
     * @return String : nom de la TortueJoueuse
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Change le nom d'une TortueJoueuse
     *
     * @param nom : nouveau nom de la TortueJoueuese
     */
    public void setNom(String nom) {
        this.nom = nom;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Paramètre la liste des tortues connues par une TortueJoueuse
     *
     * @param tortuesConnues : liste des tortues
     */
    public void setTortuesConnues(List<Tortue> tortuesConnues) {
        this.tortuesConnues = tortuesConnues;
    }
    
    /**
     * Change la stratégie de déplacement d'une tortue
     *
     * @param e : stratégie de déplacement
     */
    public void setEtat(Strategie e) {
        this.etat = e;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Récupère la stratégie courante d'un TortueJoueuse
     *
     * @return Strategie : stratégie couramment suivie par la tortue
     */
    public Strategie getEtat() {
        return etat;
    }
    
    /**
     * Ajoute une tortue dans la liste des tortues connues d'une TortueJoueuse
     *
     * @param tortue : tortue à ajouter dans la liste des tortues connues
     */
    public void ajouterTortue(Tortue tortue) {
        this.tortuesConnues.add(tortue);
    }
    
    /**
     * Enlève une tortue dans la liste des tortues connues d'une TortueJoueuse
     *
     * @param tortue : tortue à enlever de la liste des tortues connues
     */
    public void enleverTortue(Tortue tortue) {
        this.tortuesConnues.remove(tortue);
    }
    
    /**
     * Vide la liste des tortues connues par la TortueJoueuse
     *
     */
    public void reinitialiserTortuesConnues() {
        this.tortuesConnues.clear();
    }
    
    /**
     * Retourne la liste des tortues connues
     *
     * @return la liste des tortues connues par la TortueJoueuse
     */
    public List<Tortue> getTortuesConnues() {
        return tortuesConnues;
    }
    
    /**
     * Fait avancer une tortue et récupère la balle si celle-cie est proche de la TortueJoueuse
     *
     * @param dist : distance
     */
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
    
    /**
     * Détermine si la tortue est suivie par la balle
     *
     * @return boolean : vrai si la tortue est suivie par la balle, faux sinon
     */
    public boolean estSuivie(){
        for (TortueBalle balle : suiveurs) {
            if (balle.getX() == this.getX() && balle.getY() == this.getY()){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Récupère la liste des balles suivant la TortueJoueuse
     *
     * @return liste des balles suivant la tortue
     */
    public List<TortueBalle> getSuiveurs() {
        return suiveurs;
    }
    
    /**
     * Réinitialise la liste des TortuesBalles suivant la TortueJoueuse puis ajoute une TortueBalle
     *
     * @param balle : TortueBalle à ajouter
     */
    public void setSuiveurCourant(TortueBalle balle) {
        this.suiveurs.clear();
        ajouterSuiveur(balle);
    }
    
    /**
     * Ajoute une balle dans la liste des balles suivant la TortueJoueuse
     *
     * @param balle : TortueBalle à ajouter
     */
    public void ajouterSuiveur(TortueBalle balle) {
        this.suiveurs.add(balle);
    }
    
    /**
     * Enlève une tortue dans la liste des TortuesBalle suivant une TortueJoueuse
     *
     * @param balle : tortue à enlever de la liste des balles suivant la TortueJoueuse
     */
    public void supprimerSuiveur(TortueBalle balle) {
        this.suiveurs.remove(balle);
    }
    
    /**
     * Réinitialise le compteur pour génrer le nom des tortues (0)
     */
    public static void reinitialiserCpt() {
        indiceNomTortue = 0;
    }
    
    /**
     * Calcule la distance euclidienne entre la TortueJoueuse et une autre tortue
     * @param tortue avce laquelle on souhaite calculer la distance
     * @return distance euclidienne
     */
    protected double getDistanceEuclidienne(Tortue tortue) {
        return Math.sqrt(Math.pow(this.getX() - tortue.getX(), 2) + Math.pow(this.getY() - tortue.getY(), 2));
    }

    @Override
    public String toString() {
        return getNom();
    }
}
