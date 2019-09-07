import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.*;

@RunWith(HierarchicalContextRunner.class)
public class AirportTest {
    Airport airport;
    Weather weather;
    Plane plane;

    @Before
    public void setUp(){
        plane = mock(Plane.class);
        when(plane.isFlying()).thenReturn(true);
        weather = mock(Weather.class);
        airport = new Airport(weather);
    }

    @Test
    public void aPlaneCanLand(){
        when(weather.isStormy()).thenReturn(false);
        when(plane.isFlying()).thenReturn(true);
        airport.instructPlaneToLand(plane);
        assertThat(airport.getPlanes(), contains(plane));
    }

    public class airportCapacity{
        @Before
        public void setUp(){
            when(weather.isStormy()).thenReturn(false);
        }
        @Rule
        public ExpectedException thrown = ExpectedException.none();
        @Test
        public void hasDefaultCapacity(){
            for(int i = 0; i < Airport.DEFAULT_CAPACITY; i++) {
                airport.instructPlaneToLand(plane);
            }
            thrown.expect(Exception.class);
            thrown.expectMessage("Cannot land - airport is full!");
            airport.instructPlaneToLand(plane);
        }
        @Test
        public void hasVariableCapacity(){
            int variableCapacity = 25;
            Airport biggerAirport = new Airport (weather, variableCapacity);
            for(int i = 0; i < variableCapacity; i++) {
                biggerAirport.instructPlaneToLand(plane);
            }
            thrown.expect(Exception.class);
            thrown.expectMessage("Cannot land - airport is full!");
            biggerAirport.instructPlaneToLand(plane);
        }
    }

    public class whenWeatherIsStormy{
        @Rule
        public ExpectedException thrown = ExpectedException.none();

        @Test
        public void preventsLandingWhenStormy(){
            when(weather.isStormy()).thenReturn(true);
            thrown.expect(Exception.class);
            thrown.expectMessage("Cannot land - severe weather warning!");
            airport.instructPlaneToLand(plane);
            assertThat(airport.getPlanes(), not(contains(plane)));
        }
        @Test
        public void preventsPlanesTakingOffWhenStormy() throws AirportException {
            when(weather.isStormy()).thenReturn(false);
            airport.instructPlaneToLand(plane);
            when(weather.isStormy()).thenReturn(true);
            thrown.expect(Exception.class);
            thrown.expectMessage("Cannot take off - severe weather warning!");
            airport.instructPlaneToTakeOff(plane);
            assertThat(airport.getPlanes(), contains(plane));
        }
    }

    public class whenWeatherIsNotStormy{
        @Before
        public void setUp(){
            when(weather.isStormy()).thenReturn(false);
        }
        public class whenPlaneIsFlying{
            @Before
            public void setUp(){}
            @Test
            public void instructPlaneToLand(){
                airport.instructPlaneToLand(plane);
                assertThat(airport.getPlanes(), contains(plane));
            }
            @Rule
            public ExpectedException thrown = ExpectedException.none();
            @Test
            public void preventLandingWhenFull(){
                Airport fullAirport = new Airport(weather, 0);
                thrown.expect(Exception.class);
                thrown.expectMessage("Cannot land - airport is full!");
                fullAirport.instructPlaneToLand(plane);
                assertThat(fullAirport.getPlanes(), contains(plane));
            }
            @Test
            public void aFlyingPlaneCannotTakeOff() throws AirportException {
                thrown.expect(Exception.class);
                thrown.expectMessage("This plane is not in your airport!");
                airport.instructPlaneToTakeOff(plane);
            }
            @Test
            public void aPlaneLandsWhenItEntersTheAirport(){
                airport.instructPlaneToLand(plane);
                verify(plane, times(1)).land();
            }
        }
        public class whenPlaneIsNotFlying{
            @Before
            public void setUp(){
                airport.instructPlaneToLand(plane);
            }
            @Test
            public void canInstructPlaneToTakeOff() throws AirportException {
                assertThat(airport.getPlanes(), contains(plane));
                airport.instructPlaneToTakeOff(plane);
                assertThat(airport.getPlanes(), not(contains(plane)));
            }
            @Rule
            public ExpectedException thrown = ExpectedException.none();
            @Test
            public void aPlaneCannotLandIfAtAnAirport(){
                when(plane.isFlying()).thenReturn(false);
                assertThat(airport.getPlanes(), contains(plane));
                thrown.expect(Exception.class);
                thrown.expectMessage("This plane is not in flight!");
                airport.instructPlaneToLand(plane);
            }
            public void aPlaneCannotLandIfNotFlying(){
                when(plane.isFlying()).thenReturn(false);
                thrown.expect(Exception.class);
                thrown.expectMessage("This plane is not in flight!");
                airport.instructPlaneToLand(plane);
            }
            public void aPlaneCanOnlyTakeOffFromAirportItIsIn() throws AirportException {
                Airport airportTwo = new Airport(weather);
                thrown.expect(Exception.class);
                thrown.expectMessage("This plane is not in your airport!");
                airportTwo.instructPlaneToTakeOff(plane);
                assertThat(airport.getPlanes(), contains(plane));
            }
            public void aPlaneTakesOffWhenItLeavesTheAirport() throws AirportException {
                verify(plane, times(1)).takeOff();
                airport.instructPlaneToTakeOff(plane);
            }
        }
    }


    @After
    public void tearDown(){
        airport = null;
    }
}
