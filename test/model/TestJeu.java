package model;

import java.util.TimerTask;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TestJeu {
    protected TortueJoueuse tortue2, tortue3, tortue4;
    protected Jeu jeu;
    
    @Before
    public void setUp() {
        jeu = new Jeu() {

            @Override
            protected TimerTask getTimerTask() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected int getTimeIncrement() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        tortue2 = new TortueJoueuse () {
            @Override
            public String toString() {
                return getNom();
            }
        };
        tortue2.setPosition(300, 200);
        
        tortue3 = new TortueJoueuse () {
            @Override
            public String toString() {
                return getNom();
            }
        };
              
        jeu.ajouterTortue(tortue2);
    }
    
    @Test
    public void testAjouterTortueAuJeu() {
        jeu.ajouterTortue(tortue4);
        assertEquals(2, jeu.getTortues().size());
    }
    
    @Test
    public void testSupprimerTortueDuJeu() {
        jeu.supprimerTortue(tortue2);
        assertEquals(0, jeu.getTortues().size());
    }
    
    @Test
    public void testGetTortue() {
        assertEquals(tortue2, jeu.getTortue(300, 200));
        assertEquals(null, jeu.getTortue(50, 400));
    }
}
