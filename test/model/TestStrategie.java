package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TestStrategie {
    protected Strategie strategie;
    protected TortueJoueuse tortue1, tortue2;
    
    @Before
    public void setUp() {
        tortue1 = new TortueJoueuse();
        tortue1.setPosition(0, 0);
        tortue2 = new TortueJoueuse();
        tortue2.setPosition(300, 20);
        
        strategie = new Strategie(tortue1) {

            @Override
            public void deplacer(TortueJoueuse tortue) {
            }

            @Override
            public int getCouleurStrategie() {
                return 1;
            }
        };
        
        strategie.xMax = 500; strategie.yMax = 500;
        strategie.xMin = 5; strategie.yMin = 5;
    }
    
    @Test
    public void testPositionCorrecte() {
        assertFalse(strategie.isPositionCorrecte());
        
        strategie = new Strategie(tortue2) {

            @Override
            public void deplacer(TortueJoueuse tortue) {
            }

            @Override
            public int getCouleurStrategie() {
                return 2;
            }
        };
        
        assertTrue(strategie.isPositionCorrecte());
    }
}
