package use_cases.track_order;

import entities.Location;

public interface TrackOrderUserDataAccessInterface {
    public String getOrderId();
    public Location getUserLocation();
}
