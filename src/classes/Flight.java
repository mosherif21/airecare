package classes;


public class Flight extends Location{
private static String Flight_id;
private String airplane_type;
private String departing_destination;
private String arriving_destination;
private String Boarding_time;
private String DATE;
private String hasArrived;
    private volatile static Flight SINGLE_INSTANCE ;
    private Flight() {}
    public static Flight getInstance() {
        if(SINGLE_INSTANCE==null)
            SINGLE_INSTANCE = new Flight();
        return SINGLE_INSTANCE;
    }

    public String isHasArrived() {
        return hasArrived;
    }

    public void setHasArrived(String hasArrived) {
        this.hasArrived = hasArrived;
    }

    public String getFlight_id() {
        return Flight_id;
    }

    public void setFlight_id(String flight_id) {
        Flight_id = flight_id;
    }

    public String getAirplane_type() {
        return airplane_type;
    }

    public void setAirplane_type(String airplane_type) {
        this.airplane_type = airplane_type;
    }

    public String getDeparting_destination() {
        return departing_destination;
    }

    public void setDeparting_destination(String departing_destination) {
        this.departing_destination = departing_destination;
    }

    public String getArriving_destination() {
        return arriving_destination;
    }

    public void setArriving_destination(String arriving_destination) {
        this.arriving_destination = arriving_destination;
    }

    public String getBoarding_time() {
        return Boarding_time;
    }

    public void setBoarding_time(String boarding_time) {
        Boarding_time = boarding_time;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }
}
