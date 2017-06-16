package model;

import java.util.Observable;
import view.VueJeuBalle;

/**
 * Classe astraite Tortue : contient toutes les méthodes propres à toutes les tortues
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public abstract class Tortue extends Observable implements Cloneable {

    public static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
    protected int x = 0, y = 0;
    protected int direction = 90;
    protected int couleur = 0;
    protected boolean courante = false;

    public Tortue() {
        reinitialiser();
    }
    
    /**
     * Réinitialise une Tortue en la replaçant au milieu du terrain
     */
    public void reinitialiser() {
        x = VueJeuBalle.width/2; // TODO remove reference by choosing position before calling constructor
        y = VueJeuBalle.height/2;
        direction = 90;
        couleur = 0;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Calcule les nouvelles coordonnées de la Tortue et la fait avancer dans l'espace
     *
     * @param dist : distance à laquelle on souhaite faire avancer les tortues
     */
    public void avancer(int dist) {
        int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad * direction));
        int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad * direction));
        x = newX;
        y = newY;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Fait pivoter une Tortue de ang degrees vers la droite
     *
     * @param ang : angle
     */
    public void droite(int ang) {
        direction = (direction + ang) % 360;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Fait pivoter une Tortue de ang degrees vers la gauche
     *
     * @param ang : angle
     */
    public void gauche(int ang) {
        direction = (direction - ang) % 360;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Modifie la position de la tortue
     *
     * @param newX : nouvelle coordonnée X de la tortue
     * @param newY : nouvelle coordonnée Y de la tortue
     */
    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Récupère la coordonnée X de la tortue
     *
     * @return int : coordonnée X de la tortue
     */
    public int getX() {
        return x;
    }
    
    /**
     * Récupère la coordonnée Y de la tortue
     *
     * @return int : coordonnée Y de la tortue
     */
    public int getY() {
        return y;
    }
    
    /**
     * Récupère la direction de la tortue dans l'espace (ie angle trigonométrique)
     *
     * @return int : direction en degrées de la tortue
     */
    public int getDirection() {
        return direction;
    }
    
    /**
     * Change la direction de la tortue
     *
     * @param direction : direction en degrées
     */
    public void setDirection(int direction) {
        this.direction = direction;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Retourne la valeur entière correspondant à la couleur de la Tortue
     *
     * @return int : entier correspondant à la couleur de la tortue
     */
    public int getCouleur() {
        return couleur;
    }
    
    /**
     * Permet de déterminer si la tortue est courante ou non
     *
     * @return boolean
     */
    public boolean isCourante() {
        return courante;
    }
    
    /**
     * Retourne la valeur entière correspondant à la couleur de la Tortue
     *
     * @param courante : entier correspondant à la couleur de la tortue
     */
    public void setCourante(boolean courante) {
        this.courante = courante;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Change la couleur correspondant à la tortue
     *
     * @param n : entier correspondant à la couleur à changer
     */
    public void setCouleur(int n) {
        this.couleur = n;
        this.setChanged();
        this.notifyObservers();
    }
    
    @Override
    public Tortue clone() throws CloneNotSupportedException {
        return (Tortue) super.clone();
    }

    @Override
    public abstract String toString();
}
