
package controller;

import javax.swing.SwingUtilities;
import model.Feuille;
import model.Tortue;
import view.SimpleLogoView;
import view.TortueView;

/**
 * Class Main = Controlleur
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class SimpleLogoController {

    private static SimpleLogoView window;
    private Feuille feuille;
    private Tortue courante;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                window = new SimpleLogoView(new SimpleLogoController());
                window.setVisible(true);
            }
        });
    }

    public SimpleLogoController() {
        feuille = new Feuille();
        feuille.addObserver(window.getFeuille());

        // Creation de la tortue
        Tortue tortue = new Tortue();
        this.addTortue(tortue);
        courante = tortue;
    }
    
    public void addTortue(Tortue tortue)
    {
        TortueView view = new TortueView(tortue);
        tortue.addObserver(view);
        
        window.addTortue(view);
        feuille.addTortue(tortue);
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

    public void resetFeuille() {
        feuille.reset();
    }
}
