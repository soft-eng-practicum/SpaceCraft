package SpaceCraft.Controls;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.almasb.ents.Entity;

import static org.junit.Assert.*;


public class PlayerControlTest {
    
    public PlayerControlTest() {
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
     * Test of onAdded method, of class PlayerControl.
     */
    @Test
    public void testOnAdded() {
        System.out.println("onAdded");
        Entity entity = null;
        PlayerControl instance = new PlayerControl();
        instance.onAdded(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onUpdate method, of class PlayerControl.
     */
    @Test
    public void testOnUpdate() {
        System.out.println("onUpdate");
        Entity entity = null;
        double tpf = 0.0;
        PlayerControl instance = new PlayerControl();
        instance.onUpdate(entity, tpf);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of left method, of class PlayerControl.
     */
    @Test
    public void testLeft() {
        System.out.println("left");
        PlayerControl instance = new PlayerControl();
        instance.left();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of right method, of class PlayerControl.
     */
    @Test
    public void testRight() {
        System.out.println("right");
        PlayerControl instance = new PlayerControl();
        instance.right();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shoot method, of class PlayerControl.
     */
    @Test
    public void testShoot() {
        System.out.println("shoot");
        PlayerControl instance = new PlayerControl();
        instance.shoot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enableInvincibility method, of class PlayerControl.
     */
    @Test
    public void testEnableInvincibility() {
        System.out.println("enableInvincibility");
        PlayerControl instance = new PlayerControl();
        instance.enableInvincibility();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disableInvincibility method, of class PlayerControl.
     */
    @Test
    public void testDisableInvincibility() {
        System.out.println("disableInvincibility");
        PlayerControl instance = new PlayerControl();
        instance.disableInvincibility();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInvincible method, of class PlayerControl.
     */
    @Test
    public void testIsInvincible() {
        System.out.println("isInvincible");
        PlayerControl instance = new PlayerControl();
        boolean expResult = false;
        boolean result = instance.isInvincible();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of increaseAttackSpeed method, of class PlayerControl.
     */
    @Test
    public void testIncreaseAttackSpeed() {
        System.out.println("increaseAttackSpeed");
        double value = 0.0;
        PlayerControl instance = new PlayerControl();
        instance.increaseAttackSpeed(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
