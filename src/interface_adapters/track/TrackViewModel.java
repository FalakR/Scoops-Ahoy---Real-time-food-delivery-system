package interface_adapters.track;

import interface_adapters.ViewModel;
import interface_adapters.notification.NotifState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TrackViewModel extends ViewModel {
    public final String TITLE_LABEL = "Tracking View";

    private TrackState state = new TrackState();

    public static final String TRACK_BUTTON_LABEL = "Tracking";


    public TrackViewModel() {
        super("Tracking");
    }

    public void setState(TrackState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public TrackState getState() {
        return state;
    }

}

