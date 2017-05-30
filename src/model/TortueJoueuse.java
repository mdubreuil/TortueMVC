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
    protected String nom;
    protected List<Tortue> tortuesConnues;
    
    public TortueJoueuse(String name) {
        this.nom = name;
        this.tortuesConnues = new ArrayList();
    }

    public TortueJoueuse() {
        this("Unknown");
    }

    public String getNom() {
        return nom;
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

    public double getDistanceEuclidienne(Tortue tortue) {
        return Math.sqrt(Math.pow(this.getX() - tortue.getX(), 2) + Math.pow(this.getY() - tortue.getY(), 2));
    }
    
    @Override
    public void avancer(int dist) {
        super.avancer(dist);
        for (Tortue tortue : tortuesConnues) {
            if (tortue instanceof TortueJoueuse && this.getDistanceEuclidienne(tortue) < 15) {
                System.out.println("> " + nom + " : Salut " + ((TortueJoueuse)tortue).getNom());
                tortue.avancer(dist);
            }
        }
    }
}
