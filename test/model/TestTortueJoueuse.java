package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TestTortueJoueuse {
    protected TortueJoueuse tortue2, tortue3, tortue4;
    
    @Before
    public void setUp() {
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
        tortue3.setPosition(150, 100);
        
        tortue4 = new TortueJoueuse () {
            @Override
            public String toString() {
                return getNom();
            }
        };
        tortue4.setPosition(600, 400);
        
        tortue2.ajouterTortue(tortue3);
    }
    
    @Test
    public void testAjouterTortueConnue() {
        tortue2.ajouterTortue(tortue4);
        assertEquals(2, tortue2.getTortuesConnues().size());
    }
    
    @Test
    public void testEnleverTortueConnue() {
        tortue2.enleverTortue(tortue3);
        assertEquals(0, tortue2.getTortuesConnues().size());
    }
    
    @Test
    public void testReinitialiserTortuesConnues() {
        tortue2.reinitialiserTortuesConnues();
        assertEquals(0, tortue2.getTortuesConnues().size());
    }
    
    @Test
    public void testEstSuivieParBalle() {
        assertFalse(tortue2.estSuivie());
        TortueBalle balle1 = new TortueBalle();
        balle1.setPosition(300, 200);
        tortue2.setSuiveurCourant(balle1);
        assertTrue(tortue2.estSuivie());
    }
    
    @Test
    public void testDistanceEuclidienne() {
        assertEquals(180, (int) tortue2.getDistanceEuclidienne(tortue3));
        assertEquals(0,(int) tortue2.getDistanceEuclidienne(tortue2));
        assertEquals(360, (int) tortue2.getDistanceEuclidienne(tortue4));
    }
}
