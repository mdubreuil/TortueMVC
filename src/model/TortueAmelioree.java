
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class TortueAmelioree extends Tortue {
    protected String name;
    protected List<Tortue> connues;
    
    public TortueAmelioree(String name) {
        this.name = name;
        this.connues = new ArrayList();
    }

    public TortueAmelioree() {
        this("Unknown");
    }

    public String getName() {
        return name;
    }
    
    public void addTortue(Tortue tortue) {
        this.connues.add(tortue);
    }
    
    public void removeTortue(Tortue tortue) {
        this.connues.remove(tortue);
    }
    
    public void resetTortues() {
        this.connues.clear();
    }
    
    public double getDistanceEuclidienne(Tortue tortue) {
        return Math.sqrt(Math.pow(this.getX() - tortue.getX(), 2) + Math.pow(this.getY() - tortue.getY(), 2));
    }
    
    @Override
    public void avancer(int dist) {
        super.avancer(dist);
        for (Tortue tortue : connues) {
            if (tortue instanceof TortueAmelioree && this.getDistanceEuclidienne(tortue) < 15) {
                System.out.println("> " + name + " : Salut " + ((TortueAmelioree)tortue).getName());
                tortue.avancer(dist);
            }
        }
    }
}
