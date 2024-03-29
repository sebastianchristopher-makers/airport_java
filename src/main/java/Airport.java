import java.util.ArrayList;

public class Airport {

    static final int DEFAULT_CAPACITY = 10;

    private Weather weather;
    private ArrayList<Plane> planes = new ArrayList<>();
    private int capacity;

    public Airport(){
        weather = new Weather();
        capacity = DEFAULT_CAPACITY;
    }

    public Airport(int userCapacity){
        weather = new Weather();
        capacity = userCapacity;
    }

    public Airport(Weather newWeather){
        weather = newWeather;
        capacity = DEFAULT_CAPACITY;
    }

    public Airport(Weather newWeather, int userCapacity){
        weather = newWeather;
        capacity = userCapacity;
    }

    public void instructPlaneToLand(Plane plane){
        if(getPlanes().size() == capacity){
            throw new RuntimeException("Cannot land - airport is full!");
        }
        if(weather.isStormy()){
            throw new RuntimeException("Cannot land - severe weather warning!");
        }
        if(!plane.isFlying()){
            throw new RuntimeException("This plane is not in flight!");
        }
        plane.land();
        planes.add(plane);
    }

    public void instructPlaneToTakeOff(Plane plane) throws AirportException {
        if(weather.isStormy()){
            throw new RuntimeException("Cannot take off - severe weather warning!");
        }
        if(!isInAirport(plane)){
            throw new AirportException("This plane is not in your airport!");
        }
        planes.remove(plane);
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    private Boolean isInAirport(Plane plane){
        return getPlanes().contains(plane);
    }
}
