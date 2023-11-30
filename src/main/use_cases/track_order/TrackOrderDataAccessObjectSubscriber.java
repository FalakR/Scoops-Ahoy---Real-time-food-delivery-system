package use_cases.track_order;
import entities.Location;

public interface TrackOrderDataAccessObjectSubscriber {
    void onChange(Location location);
}
