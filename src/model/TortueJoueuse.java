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
    
    public TortueJoueuse(String name) {
        this.nom = name;
        this.tortuesConnues = new ArrayList();
        ++cpt;
    }

    public TortueJoueuse() {
        this(DEFAULT_NAME + cpt);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTortuesConnues(List<Tortue> tortuesConnues) {
        this.tortuesConnues = tortuesConnues;
    }

    public void setEtat(Strategie etat) {
        this.etat = etat;
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

    public double getDistanceEuclidienne(Tortue tortue) {
        return Math.sqrt(Math.pow(this.getX() - tortue.getX(), 2) + Math.pow(this.getY() - tortue.getY(), 2));
    }

    @Override
    public void avancer(int dist) {
        super.avancer(dist);
        for (Tortue tortue : tortuesConnues) {
            if (getDistanceEuclidienne(tortue) < 15) {
                if (tortue instanceof TortueJoueuse) {
                    System.out.println(">> " + nom + ": Salut " + ((TortueJoueuse)tortue).getNom());
                } else {
                    System.out.println(">> " + nom + ": Bonjour Tortue!");
                }
                tortue.avancer(dist);
            }
        }
    }
}
