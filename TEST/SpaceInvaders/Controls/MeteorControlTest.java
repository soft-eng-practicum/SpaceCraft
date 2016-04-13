package SpaceCraft.Controls;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.almasb.ents.Entity;

import static org.junit.Assert.*;

public class MeteorControlTest {
    
    public MeteorControlTest() {
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
     * Test of onAdded method, of class MeteorControl.
     */
    @Test
    public void testOnAdded() {
        System.out.println("onAdded");
        Entity entity = null;
        MeteorControl instance = new MeteorControl();
        instance.onAdded(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onUpdate method, of class MeteorControl.
     */
    @Test
    public void testOnUpdate() {
        System.out.println("onUpdate");
        Entity entity = null;
        double tpf = 0.0;
        MeteorControl instance = new MeteorControl();
        instance.onUpdate(entity, tpf);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
