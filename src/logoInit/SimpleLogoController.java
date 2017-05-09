
package logoInit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Epulapp
 */
public class SimpleLogoController implements ActionListener  {
    private static SimpleLogoView window;
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
    
    /** la gestion des actions des boutons
     * @param e */
    @Override
    public void actionPerformed(ActionEvent e)
    {
            String c = e.getActionCommand();
            
            // actions des boutons du haut
            if (c.equals("Avancer")) {
                    System.out.println("command avancer");
                    try {
                        int v = Integer.parseInt(window.getInputValue().getText());
                        courante.avancer(v);
                    } catch (NumberFormatException ex){
                            System.err.println("ce n'est pas un nombre : " + window.getInputValue().getText());
                    }

            }
            else if (c.equals("Droite")) {
                    try {
                            int v = Integer.parseInt(window.getInputValue().getText());
                            courante.droite(v);
                    } catch (NumberFormatException ex){
                            System.err.println("ce n'est pas un nombre : " + window.getInputValue().getText());
                    }
            }
            else if (c.equals("Gauche")) {
                    try {
                            int v = Integer.parseInt(window.getInputValue().getText());
                            courante.gauche(v);
                    } catch (NumberFormatException ex){
                            System.err.println("ce n'est pas un nombre : " + window.getInputValue().getText());
                    }
            }
            else if (c.equals("Lever")) 
                    courante.leverCrayon();
            else if (c.equals("Baisser"))
                    courante.baisserCrayon();
            // actions des boutons du bas
            else if (c.equals("Proc1"))
                    proc1();
            else if (c.equals("Proc2"))
                    proc2();
            else if (c.equals("Proc3"))
                    proc3();
            else if (c.equals("Effacer"))
                    effacer();
            //else if (c.equals("Quitter"))
                    //quitter();

            //feuille.repaint();
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
    
    public void effacer() {
//        feuille.reset();
//        feuille.repaint();
//
//        // Replace la tortue au centre
//        Dimension size = feuille.getSize();
//        courante.setPosition(size.width/2, size.height/2);
    }
    
}
