package use_cases.track_order;

import entities.Location;

public class TrackOrderOutputData {
    public Location userLocation;
    public Location deliveryAgentLocation;

    public TrackOrderOutputData(Location userLocation,
                                Location deliveryAgentLocation) {
        this.userLocation = userLocation;
        this.deliveryAgentLocation = deliveryAgentLocation;
    }
}
