package use_cases.track_order;

import entities.Location;

public class TrackOrderInteractor implements TrackOrderInputBoundary, TrackOrderDataAccessObjectSubscriber {
    @Override
    public void execute(TrackOrderInputData inputData) {

    }

    /**
     * @param location
     */
    @Override
    public void onChange(Location location) {
        // Pass to view model

    }
}
