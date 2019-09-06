import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

public class WeatherTest {

    Weather weather;

    @Before
    public void setUpWeather() {
        weather = new Weather();
    }

    @After
    public void destroyWeather() {
        weather = null;
    }

    @Test
    public void hasWeatherRatingFromOneToTen(){
        Assert.assertTrue(weather.weatherRating >= 1);
        Assert.assertTrue(weather.weatherRating <= 10);
    }
    @Test
    public void canCheckIfStormy(){
        Assert.assertThat(weather.isStormy(), instanceOf(Boolean.class));
    }

}
