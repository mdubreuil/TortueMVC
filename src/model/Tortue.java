
package model;

import java.util.Observable;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class Tortue extends Observable {

    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
    protected int x = 0, y = 0;
    protected int direction = -90;
    protected boolean crayon = true;
    protected int couleur = 0;

    public Tortue() {
        reset();
        x = 500/2;
        y = 400/2;
    }

    public void reset() {
        x = 0;
        y = 0;
        direction = -90;
        couleur = 0;
        crayon = true;
        this.setChanged();
        this.notifyObservers();
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
        this.setChanged();
        this.notifyObservers();
    }

    public void avancer(int dist) {
        int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad * direction));
        int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad * direction));

//		if (crayon==true) {
//			Segment seg = new Segment();
//			
//			seg.ptStart.x = x;
//			seg.ptStart.y = y;
//			seg.ptEnd.x = newX;
//			seg.ptEnd.y = newY;
//			seg.color = decodeColor(coul);
//	
//			listSegments.add(seg);
//		}

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

    public void baisserCrayon() {
        crayon = true;
        this.setChanged();
        this.notifyObservers();
    }

    public void leverCrayon() {
        crayon = false;
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

    /** quelques classiques */

    public void carre() {
        for (int i=0;i<4;i++) {
            avancer(100);
            droite(90);
        }
    }

    public void poly(int n, int a) {
        for (int j=0;j<a;j++) {
            avancer(n);
            droite(360/a);
        }
    }

    public void spiral(int n, int k, int a) {
        for (int i = 0; i < k; i++) {
            couleur(couleur+1);
            avancer(n);
            droite(360/a);
            n = n+1;
        }
    }

    public static double getRatioDegRad() {
        return ratioDegRad;
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

    public boolean isCrayon() {
        return crayon;
    }

    public int getCouleur() {
        return couleur;
    }
    
    public void setColor(int n) {
        this.couleur = n;
        this.setChanged();
        this.notifyObservers();
    }

    public int getColor() {
        return couleur;
    }
}
