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
    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        // Returns the euclidian distance between two points.
        return Math.sqrt(Math.pow(lat2 - lat1, 2) + Math.pow(lon2 - lon1, 2));
    }
    @Override
    public void onChange(Location deliveryAgentLocation) {
        // When we receive a new location, we prepare a new view.
        TrackOrderOutputData out = new TrackOrderOutputData(
                this.userLocation,
                deliveryAgentLocation
        );

        double distance = getDistance(
                this.userLocation.getX().doubleValue(),
                this.userLocation.getY().doubleValue(),
                deliveryAgentLocation.getX().doubleValue(),
                deliveryAgentLocation.getY().doubleValue()
        );

        System.out.println("Distance: " + distance);
        if (distance < 4E-4) {
            this.trackOrderPresenter.prepareSuccessView();
        }
        this.trackOrderPresenter.prepareView(out);
    }
}
