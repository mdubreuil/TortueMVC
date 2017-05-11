
package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import model.Feuille;
import model.Tortue;
import model.TortueBalle;
import view.TortueBalleView;
import model.TortueAmelioree;
import view.FeuilleDessin;
import view.SimpleLogoView;
import view.TortueAmelioreeView;
import view.TortueView;

/**
 * Class Main = Controlleur
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class SimpleLogoController implements MouseListener {

    private static SimpleLogoView window = null;
    private Feuille feuille = null;
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
        Tortue courante = new Tortue();
        feuille = new Feuille(courante);

        // Views
        feuilleView = new FeuilleDessin();
        feuilleView.addMouseListener(this);
        couranteView = new TortueView(courante);
        feuilleView.addTortue(couranteView);

        // Add listeners
        feuille.addObserver(feuilleView);
        courante.addObserver(feuilleView);
        
        window = new SimpleLogoView(this, feuilleView);
    }

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
        feuille.setCourante(tortue);
    }
    
    public void addNewTortueClassique()
    {
        Tortue t = new Tortue();
        t.addObserver(feuilleView);        
        TortueView tView = new TortueView(t);
        feuilleView.addTortue(tView);
        setCourante(t);
    }
    
    public void addNewTortueAmelioree(String name)
    {
        TortueAmelioree t = new TortueAmelioree(name);

        for (Tortue tortue : feuille.getTortues()) {
            t.addTortue(tortue);
            
            if (tortue instanceof TortueAmelioree) {
                ((TortueAmelioree)tortue).addTortue(t);
            }
        }
        
        t.addObserver(feuilleView);
        TortueView tView = new TortueAmelioreeView(t);
        feuilleView.addTortue(tView);
        setCourante(t);
    }
    
    public void addNewTortueBalle()
    {
        TortueBalle t = new TortueBalle();
        t.addObserver(feuilleView);        
        TortueBalleView tView = new TortueBalleView(t);
        feuilleView.addTortue(tView);
        setCourante(t);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        Tortue tortue = feuille.getTortue(x, y);
        feuille.setCourante(tortue);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
