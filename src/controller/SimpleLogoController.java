
package controller;

import javax.swing.SwingUtilities;
import model.Feuille;
import model.Tortue;
import view.FeuilleDessin;
import view.SimpleLogoView;
import view.TortueView;

/**
 * Class Main = Controlleur
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class SimpleLogoController {

    private static SimpleLogoView window = null;
    private Feuille feuille = null;
    private Tortue courante = null;
    private FeuilleDessin feuilleView = null;
    private TortueView couranteView = null;

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                SimpleLogoController controller = new SimpleLogoController();
//                controller.addListeners();
            }
        });
    }
    
    public SimpleLogoController() {
        // Modèle
        courante = new Tortue();
        feuille = new Feuille(courante);

        // Views
        feuilleView = new FeuilleDessin();
        couranteView = new TortueView(courante);
        feuilleView.addTortue(couranteView);

        // Add listeners
//        courante.addObserver(couranteView);
        feuille.addObserver(feuilleView);
        
        window = new SimpleLogoView(this, feuilleView);
    }

//    public void setCourante(Tortue courante) {
//        feuille.addTortue(courante); // TODO check if not in list
//        feuille.setCourante(courante);
//    }

    public void resetCourante()
    {
        getCourante().reset();
    }

    public void changeColor(int n) {
        getCourante().setColor(n);
    }
    
    public void changePosition(int x, int y) {
        getCourante().setPosition(x, y);
    }
    
    public void quitter() {
        System.exit(0);
    }
    
    public void avancer(int v) {
        getCourante().avancer(v);
    }
    
    public void droite(int v) {
        getCourante().droite(v);
    }
    
    public void gauche(int v) {
        getCourante().gauche(v);
    }
    
    public void leverCrayon() {
        getCourante().leverCrayon();
    }
    
    public void baisserCrayon() {
        getCourante().baisserCrayon();
    }
    
    /** les procedures Logo qui combine plusieurs commandes..*/
    public void proc1() {
        getCourante().carre();
    }

    public void proc2() {
        getCourante().poly(60,8);
    }

    public void proc3() {
        getCourante().spiral(50,40,6);
    }

    public void resetFeuille() {
        feuille.reset();
    }
    
    // Temporary while no tortueController
    protected Tortue getCourante()
    {
        return feuille.getCourante();
    }
    
    protected void setCourante(Tortue tortue)
    {
        feuille.addTortue(tortue);
        feuille.setCourante(courante);
    }
    
//    protected void addNewTortue(Color color)
//    {
//        
//    }
}
