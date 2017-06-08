package model;

import java.util.Observable;
import view.VueJeuBalle;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public abstract class Tortue extends Observable implements Cloneable {

    public static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
    protected int x = 0, y = 0;
    protected int direction = -90;
    protected int couleur = 0;
    protected boolean courante = false;

    public Tortue() {
        reinitialiser();
        x = VueJeuBalle.width/2; // TODO remove reference by choosing position before calling constructor
        y = VueJeuBalle.height/2;
    }

    public void reinitialiser() {
        x = 0;
        y = 0;
        direction = -90;
        couleur = 0;
        this.setChanged();
        this.notifyObservers();
    }
    
    public void avancer(int dist) {
        int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad * direction));
        int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad * direction));
        x = newX;
        y = newY;
        this.setChanged();
        this.notifyObservers();
    }

    public void droite(int ang) {
        direction = (direction + ang) % 360;
        this.setChanged();
        this.notifyObservers();
    }

    public void gauche(int ang) {
        direction = (direction - ang) % 360;
        this.setChanged();
        this.notifyObservers();
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
        this.setChanged();
        this.notifyObservers();
    }

    public void couleur(int n) {
        couleur = n % 12;
        this.setChanged();
        this.notifyObservers();
    }

    public void couleurSuivante() {
        couleur(couleur + 1);
        this.setChanged();
        this.notifyObservers();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
        this.setChanged();
        this.notifyObservers();
    }

    public int getCouleur() {
        return couleur;
    }

    public boolean isCourante() {
        return courante;
    }

    public void setCourante(boolean courante) {
        this.courante = courante;
        this.setChanged();
        this.notifyObservers();
    }

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
