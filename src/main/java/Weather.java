import java.util.Random;

public class Weather {

    private Random random = new Random();
    private int weatherRating;
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
        weatherRating = random.nextInt((MAX_WEATHER - MIN_WEATHER) + 1) + MIN_WEATHER;
    }
}
