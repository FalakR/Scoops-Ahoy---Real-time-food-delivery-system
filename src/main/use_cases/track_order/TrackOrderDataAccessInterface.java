package use_cases.track_order;

public interface TrackOrderDataAccessInterface {
    /*
    * The main purpose is to subscribe to the ably api to get location updates
    * (on channel "order:{orderId}").
    * */

    public void subscribe(TrackOrderDataAccessObjectSubscriber sub, String orderId);
    // Object o has an onChange method
}
