package interface_adapters.add_to_cart;

import interface_adapters.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddToCartViewModel extends ViewModel {

    public final String TITLE_LABEL = "BrowseView";

    private AddToCartState state = new AddToCartState();

    public AddToCartViewModel() {
        super("BrowseView");
    }

    public void setState(AddToCartState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public AddToCartState getState() {
        return state;
    }

    public String getViewName() {
        return "BrowseView";
    }
}
