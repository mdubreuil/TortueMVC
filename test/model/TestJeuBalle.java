package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TestJeuBalle {
    protected TortueJoueuse tortue2, tortue3, tortue4;
    protected JeuBalle jeu;
    
    @Before
    public void setUp() {
        try {
            jeu = new JeuBalle();
            
            tortue2 = new TortueJoueuse () {
                @Override
                public String toString() {
                    return getNom();
                }
            };
            tortue2.setNom("Gertrude");
            
            jeu.ajouterTortue(tortue2);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestJeuBalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testRecupererTortueParNom() {
        assertEquals(tortue2, jeu.getTortueParNom("Gertrude"));
        assertEquals(null, jeu.getTortueParNom("Gertrudeyfutyuui"));
        assertEquals(null, jeu.getTortueParNom(""));
    }
}
