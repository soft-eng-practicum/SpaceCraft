package SpaceInvaders.Controls;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class BulletControlTest {
    
    public BulletControlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of onAdded method, of class BulletControl.
     */
    @Test
    public void testOnAdded() {
        System.out.println("onAdded");
        com.almasb.ents.Entity entity = null;
        BulletControl instance = null;
        instance.onAdded(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onUpdate method, of class BulletControl.
     */
    @Test
    public void testOnUpdate() {
        System.out.println("onUpdate");
        com.almasb.ents.Entity entity = null;
        double tpf = 0.0;
        BulletControl instance = null;
        instance.onUpdate(entity, tpf);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
