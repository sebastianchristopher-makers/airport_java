import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AirportTest {

    Airport airport;
    Weather weather;
    Plane plane;

    @Before
    public void setUpAirport(){
        plane = mock(Plane.class);
        when(plane.isFlying()).thenReturn(true);
        weather = mock(Weather.class);
        airport = new Airport(weather);

//        before(:each) { allow(plane).to receive(:land) }
    }

    @After
    public void destroyAirport(){
        airport = null;
    }

    @Test
    public void aPlaneCanLand(){
        when(weather.isStormy()).thenReturn(false);
        airport.instructPlaneToLand(plane);
        assertThat(airport.getPlanes(), contains(plane));
    }
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void hasADefaultCapacity() throws Exception {
        when(weather.isStormy()).thenReturn(false);
        for(int i = 0; i < Airport.DEFAULT_CAPACITY; i++) {
            airport.instructPlaneToLand(plane);
        }
        thrown.expect(Exception.class);
        thrown.expectMessage("Cannot land - airport is full!");
        airport.instructPlaneToLand(plane);
    }
}
