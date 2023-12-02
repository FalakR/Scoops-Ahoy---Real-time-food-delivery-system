package interface_adapters.track_order;

import entities.Location;

public class TrackOrderState {
    public Location userLocation;
    public Location deliveryAgentLocation;

    TrackOrderState(Location userLocation, Location deliveryAgentLocation) {
        this.userLocation = userLocation;
        this.deliveryAgentLocation = deliveryAgentLocation;
    }
}
