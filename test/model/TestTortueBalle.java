package model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TestTortueBalle {
    protected TortueJoueuse tortue1, tortue2, tortue3, tortue4;
    protected TortueBalle balle;
    
    @Before
    public void setUp() {
        tortue1 = new TortueJoueuse () {
            @Override
            public String toString() {
                return getNom();
            }
        };
        
        tortue2 = null;
        
        tortue3 = new TortueJoueuse () {
            @Override
            public String toString() {
                return getNom();
            }
        };
        tortue3.setNom("Toto1");
        
        tortue4 = new TortueJoueuse () {
            @Override
            public String toString() {
                return getNom();
            }
        };
        tortue4.setNom("Toto4");
        
        balle = new TortueBalle () {
            @Override
            public String toString() {
                return "TortueBalle";
            }
        };
        balle.setTortueSuivie(tortue1);
    } 
    
    @Test
    public void testSetTortueSuivie() {
        balle.setTortueSuivie(tortue2);
        assertEquals(tortue1, balle.getTortueSuivie());
        
        balle.setTortueSuivie(tortue3);
        assertEquals(tortue1, balle.getTortueSuivie());
        
        balle.dureePossession = 50;
        balle.setTortueSuivie(tortue4);
        assertEquals(tortue4, balle.getTortueSuivie());
    }
}
