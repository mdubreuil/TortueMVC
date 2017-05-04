
package controller;

import java.util.ArrayList;
import javax.swing.SwingUtilities;
import model.Tortue;
import view.SimpleLogoView;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class SimpleLogoController {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                SimpleLogoView fenetre = new SimpleLogoView(new SimpleLogoController());
                fenetre.setVisible(true);
            }
        });
    }
    
    private Tortue courante;
    private ArrayList<Tortue> tortues; // la liste des tortues enregistrees

    public SimpleLogoController() {
        tortues = new ArrayList();
        
        // Creation de la tortue
        courante = new Tortue();
        // Deplacement de la tortue au centre de la feuille
        courante.setPosition(500/2, 400/2); 		

        tortues.add(courante);
    }

    public void addTortue(Tortue o) {
        tortues.add(o);
    }
    
    public ArrayList<Tortue> getTortues() {
        return tortues;
    }

    public void reset() {
        for (Tortue t : tortues) {
            t.reset();
        }
    }
    
    
    
    public void changeColor(int n) {
        courante.setColor(n);
    }
    
    public void changePosition(int x, int y) {
        courante.setPosition(x, y);
    }
    
    public void quitter() {
        System.exit(0);
    }
    
    public void avancer(int v) {
        courante.avancer(v);
    }
    
    public void droite(int v) {
        courante.droite(v);
    }
    
    public void gauche(int v) {
        courante.gauche(v);
    }
    
    public void leverCrayon() {
        courante.leverCrayon();
    }
    
    public void baisserCrayon() {
        courante.baisserCrayon();
    }
    
    /** les procedures Logo qui combine plusieurs commandes..*/
    public void proc1() {
        courante.carre();
    }

    public void proc2() {
        courante.poly(60,8);
    }

    public void proc3() {
        courante.spiral(50,40,6);
    }
}
