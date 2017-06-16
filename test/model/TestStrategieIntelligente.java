package model;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TestStrategieIntelligente {
    protected StrategieIntelligente strategie, strategie2, strategie3, strategie4;
    protected TortueJoueuse tortue1, tortue2, tortue3, tortue4;
    protected TortueBalle balle, balle2, balle3, balle4;
    
    @Before
    public void setUp() {
        tortue1 = new TortueJoueuse();
        tortue1.setPosition(300, 200);        
        balle = new TortueBalle();
        balle.setPosition(200, 300);        
        tortue1.ajouterTortue(balle);        
        strategie = new StrategieIntelligente(tortue1) {

            @Override
            public void deplacer(TortueJoueuse tortue) {
            }

            @Override
            public int getCouleurStrategie() {
                return 1;
            }
        };
        
        tortue2 = new TortueJoueuse();
        tortue2.setPosition(300, 200);
        
        balle2 = new TortueBalle();
        balle2.setPosition(400, 300);
        
        tortue2.ajouterTortue(balle2);
        
        strategie2 = new StrategieIntelligente(tortue2) {

            @Override
            public void deplacer(TortueJoueuse tortue) {
            }

            @Override
            public int getCouleurStrategie() {
                return 1;
            }
        };
        
        tortue3 = new TortueJoueuse();
        tortue3.setPosition(300, 200);
        
        balle3 = new TortueBalle();
        balle3.setPosition(200, 100);
        
        tortue3.ajouterTortue(balle3);
        
        strategie3 = new StrategieIntelligente(tortue3) {

            @Override
            public void deplacer(TortueJoueuse tortue) {
            }

            @Override
            public int getCouleurStrategie() {
                return 1;
            }
        };
        
        tortue4 = new TortueJoueuse();
        tortue4.setPosition(300, 200);
        
        balle4 = new TortueBalle();
        balle4.setPosition(400, 100);
        
        tortue4.ajouterTortue(balle4);
        
        strategie4 = new StrategieIntelligente(tortue4) {

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
    public void testCalculerCoordonneesBalle() {
        strategie.calculerCoordonneesBalle(tortue1);        
        assertEquals(200,strategie.getCoordonneesBalle()[0]);
        assertEquals(300,strategie.getCoordonneesBalle()[1]);
        
        strategie2.calculerCoordonneesBalle(tortue2);        
        assertEquals(400,strategie2.getCoordonneesBalle()[0]);
        assertEquals(300,strategie2.getCoordonneesBalle()[1]);
        
        strategie3.calculerCoordonneesBalle(tortue3);        
        assertEquals(200,strategie3.getCoordonneesBalle()[0]);
        assertEquals(100,strategie3.getCoordonneesBalle()[1]);
        
        strategie4.calculerCoordonneesBalle(tortue4);        
        assertEquals(400,strategie4.getCoordonneesBalle()[0]);
        assertEquals(100,strategie4.getCoordonneesBalle()[1]);
    }
    
    @Test
    public void testestPresDeLaBalle() {
        strategie.calculerCoordonneesBalle(tortue1);        
        assertTrue(strategie.estPresDeLaBalle(tortue1));
        assertEquals(1,strategie.positionBalle[0]);
        
        strategie2.calculerCoordonneesBalle(tortue2);        
        assertTrue(strategie2.estPresDeLaBalle(tortue2));
        assertEquals(3,strategie2.positionBalle[0]);
        
        strategie3.calculerCoordonneesBalle(tortue3);        
        assertTrue(strategie3.estPresDeLaBalle(tortue3));
        assertEquals(1,strategie3.positionBalle[0]);
        
        strategie4.calculerCoordonneesBalle(tortue4);        
        assertTrue(strategie4.estPresDeLaBalle(tortue4));
        assertEquals(3,strategie4.positionBalle[0]);
    }
}
