public class Plane {

    private Boolean flying;

    public Plane(){
        flying = true;
    }

    public Boolean isFlying(){
        return flying;
    }

    public void land(){
        flying = false;
    }

    public void takeOff(){
        flying = true;
    }
}
