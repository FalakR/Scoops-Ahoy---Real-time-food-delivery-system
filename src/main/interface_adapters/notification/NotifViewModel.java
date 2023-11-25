package interface_adapters.notification;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NotifViewModel extends ViewModel {
    public final String TITLE_LABEL = "Notification View";

    private NotifState state = new NotifState();

    public NotifViewModel() {
        super("notification");
    }

    public void setState(boolean isDelivered) {
        NotifState newState = new NotifState();
        this.state = newState;
        // Notify listeners about the state change
        support.firePropertyChange("state", null, this.state);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public NotifState getState() {
        return state;
    }
}

