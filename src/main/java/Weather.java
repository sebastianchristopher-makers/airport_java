import java.util.Random;

public class Weather {

    public int weatherRating;
    static final int MIN_WEATHER = 1;
    static final int MAX_WEATHER = 10;

    public Weather() {
        generateWeather();
    }

    public Boolean isStormy(){
        generateWeather();
        return weatherRating >= 8;
    }

    private void generateWeather(){
        Random random = new Random();
        weatherRating = random.nextInt((MAX_WEATHER - MIN_WEATHER) + 1) + MIN_WEATHER;
    }
}
