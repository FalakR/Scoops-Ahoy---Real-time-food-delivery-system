package interface_adapters.track_order;

import interface_adapters.ViewModel;
import interface_adapters.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TrackOrderViewModel extends ViewModel {
    private TrackOrderState state;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public TrackOrderViewModel() {
        super("track order");
    }
    public void setState(TrackOrderState state) {this.state = state;}
    public TrackOrderState getState() {return this.state;}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }
}
