package interface_adapters.place_order;

import interface_adapters.ViewModel;
import interface_adapters.track.TrackState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlaceOrderViewModel extends ViewModel {

    public final String TITLE_LABEL = "Place Order View";
    public final String ADDRESS_LABEL = "Enter delivery address";
    public final String CARD_NUMBER_LABEL = "Enter credit card number";
    public final String CVV_LABEL = "Enter CVV";
    public final String EXPIRY_DATE_LABEL = "Enter expiry date";

    public final String PLACE_ORDER_BUTTON_LABEL = "Place Order";

    private PlaceOrderState state = new PlaceOrderState();

    public PlaceOrderViewModel() {
        super("place_order");
    }

    public void setState(PlaceOrderState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the PlaceOrder Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PlaceOrderState getState() {
        return state;
    }
}
