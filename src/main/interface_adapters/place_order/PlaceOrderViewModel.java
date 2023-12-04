package interface_adapters.place_order;

import interface_adapters.ViewModel;
import interface_adapters.track_order.TrackOrderState;

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
    private boolean propertyChanged;

    public PlaceOrderViewModel() {
        super("place order");
    }

    public void setState(PlaceOrderState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {

        propertyChanged = state.getAddressError() != null || state.getCardNumberError() != null;

        if (state.getAddressError() != null) {
            support.firePropertyChange("addressError", null, state);
        }

        if (state.getCardNumberError() != null) {
            support.firePropertyChange("cardNumberError", null, state);
        }

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public boolean isPropertyChanged() {
        return propertyChanged;
    }

    public PlaceOrderState getState() {
        return state;
    }


}
