package classes;

public class Location {
private int location_id;

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    private String Location_Name;
    private String Gate;
    private int Floor;

    public String getLocation_Name() {
        return Location_Name;
    }

    public void setLocation_Name(String location_Name) {
        Location_Name = location_Name;
    }

    public String getGate() {
        return Gate;
    }

    public void setGate(String gate) {
        Gate = gate;
    }

    public int getFloor() {
        return Floor;
    }

    public void setFloor(int floor) {
        Floor = floor;
    }
}
