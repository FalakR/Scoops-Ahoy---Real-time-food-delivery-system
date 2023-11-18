package interface_adapters.notification;

public class NotifState {
    private String couriername = "";

    public NotifState(NotifState copy) {
        couriername = copy.couriername;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public NotifState() {}

    public String getCourierName() {
        return couriername;
    }
    public void setCourierName(String couriername) {
        this.couriername = couriername;
    }
}
