package use_cases.track_order;

import entities.Location;

public class TrackOrderInteractor implements TrackOrderInputBoundary, TrackOrderDataAccessObjectSubscriber {

    final TrackOrderOutputBoundary trackOrderPresenter;
    final TrackOrderDataAccessInterface trackOrderDataAccessObject;
    final TrackOrderUserDataAccessInterface trackOrderUserDataAccessObject;
    private Location userLocation;

    public TrackOrderInteractor(
            TrackOrderDataAccessInterface trackOrderDataAccessObject,
            TrackOrderUserDataAccessInterface trackOrderUserDataAccessObject,
            TrackOrderOutputBoundary trackOrderPresenter
    ) {
        this.trackOrderDataAccessObject = trackOrderDataAccessObject;
        this.trackOrderPresenter = trackOrderPresenter;
        this.trackOrderUserDataAccessObject = trackOrderUserDataAccessObject;
    }

    @Override
    public void execute() {
        System.out.println("Track order interactor.execute");
        // Executed once when the view is in view.
        String orderId = this.trackOrderUserDataAccessObject.getOrderId();
        this.userLocation = this.trackOrderUserDataAccessObject.getUserLocation();

        // Subscribe to ably data access object
        this.trackOrderDataAccessObject.subscribe(
                this,
                orderId
        );
    }

    @Override
    public void onChange(Location deliveryAgentLocation) {
        // When we receive a new location, we prepare a new view.
        TrackOrderOutputData out = new TrackOrderOutputData(
                this.userLocation,
                deliveryAgentLocation
        );

        this.trackOrderPresenter.prepareView(out);
    }
}
