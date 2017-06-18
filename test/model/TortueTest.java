package model;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Mélanie DUBREUIL 4APP
 * @author Ophélie EOUZAN 4APP
 */

public class TortueTest {
    protected Tortue tortue1;
    
    @Before
    public void setUp() {
        tortue1 = new Tortue () {
            @Override
            public String toString() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        tortue1.setDirection(90);
        tortue1.setPosition(300, 200);
    } 
    
    @Test
    public void testTournerDroite() {
        tortue1.droite(-30);
        assertEquals(60, tortue1.getDirection());
        
        tortue1.droite(30);
        assertEquals(90, tortue1.getDirection());
        
        tortue1.droite(390);
        assertEquals(120, tortue1.getDirection());
        
        tortue1.droite(-390);
        assertEquals(-270, tortue1.getDirection());
        
        tortue1.droite(0);
        assertEquals(-270, tortue1.getDirection());
    }
    
    @Test
    public void testTournerGauche() {
        tortue1.gauche(-30);
        assertEquals(120, tortue1.getDirection());
        
        tortue1.gauche(30);
        assertEquals(90, tortue1.getDirection());
        
        tortue1.gauche(390);
        assertEquals(-300, tortue1.getDirection());
        
        tortue1.gauche(-390);
        assertEquals(90, tortue1.getDirection());
        
        tortue1.gauche(0);
        assertEquals(90, tortue1.getDirection());
    }
    
    @Test
    public void testSetPosition() {
        tortue1.setPosition(200, 300);
        assertEquals(200, tortue1.getX());
        assertEquals(300, tortue1.getY());
        
        tortue1.setPosition(-200, 300);
        assertEquals(-200, tortue1.getX());
        assertEquals(300, tortue1.getY());
        
        tortue1.setPosition(200, -300);
        assertEquals(200, tortue1.getX());
        assertEquals(-300, tortue1.getY());
        
        tortue1.setPosition(-200, -300);
        assertEquals(-200, tortue1.getX());
        assertEquals(-300, tortue1.getY());
    }
    
}