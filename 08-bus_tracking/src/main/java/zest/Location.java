package zest;


public class Location {
    private double latitude;
    private double longitude;
    private boolean keyWaypoint;
    private String waypointName;

    public Location(double latitude, double longitude, boolean keyWaypoint, String waypointName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.keyWaypoint = keyWaypoint;
        this.waypointName = waypointName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isKeyWaypoint() {
        return keyWaypoint;
    }

    public String getWaypointName() {
        return waypointName;
    }
}
