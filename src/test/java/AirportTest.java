import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class AirportTest {

    Airport airport;
    Weather weather;
    Plane plane;

    @Before
    public void setUpAirport(){
        airport = new Airport();
        plane = mock(Plane.class);
        when(plane.isFlying()).thenReturn(true);
        weather = mock(Weather.class);

//        before(:each) { allow(plane).to receive(:land) }
    }

    @After
    public void destroyAirport(){
        airport = null;
    }

    @Test
    public void aPlaneCanLand(){
        when(weather.isStormy()).thenReturn(false);
        airport.instructPlaneToLand(plane, weather);
        assertThat(airport.planes(), contains(plane));
    }
//    @Test
//    public void hasADefaultCapacity(){
//        when(weather.isStormy()).thenReturn(false);
//        for(i = 0; i < Airport::DEFAULT_CAPACITY; i++){
//            airport.instructPlaneToLand(plane, weather);
//        }
//        expect { subject.instruct_plane_to_land(plane, weather) }.to raise_error("Cannot land - airport is full!")
//    }

}
