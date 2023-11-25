// NotifState class
package interface_adapters.notification;

public class NotifState {


    private boolean isDelivered;

    public NotifState(NotifState copy) {
        isDelivered = copy.isDelivered;
    }
    public NotifState(){}

    public boolean isDelivered() {
        return isDelivered;
    }
}

