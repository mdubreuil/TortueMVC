package model;

/**
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */

public class Equipe {
    int points, couleur;
    String nom;

    public Equipe(String nom, int couleur) {
        this.nom = nom;
        this.couleur = couleur;
        this.points = 0;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
