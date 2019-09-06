import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert.*;

public class PlaneTest {

    Plane plane;

    @Before
    public void setUpPlane(){
        plane = new Plane();
    }

    @After
    public void destroyPlane(){
        plane = null;
    }

    @Test
    public void canFly(){
        Assert.assertTrue(plane.isFlying());
    }
    @Test
    public void canLand() {
        plane.land();
        Assert.assertFalse(plane.isFlying());
    }
    @Test
    public void canTakeOff(){
        plane.land();
        plane.takeOff();
        Assert.assertTrue(plane.isFlying());
    }
    @Test
    public void ifFlyingAndLandsIsNoLongerFlying() {
        Assert.assertTrue(plane.isFlying());
        plane.land();
        Assert.assertFalse(plane.isFlying());
    }
    @Test
    public void ifNotFlyingAndTakesOffIsNowFlying() {
        plane.land();
        Assert.assertFalse(plane.isFlying());
        plane.takeOff();
        Assert.assertTrue(plane.isFlying());
    }
}
