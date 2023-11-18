package interface_adapters.notification;

import interface_adapters.ViewModel;
import interface_adapters.notification.NotifState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class NotifViewModel extends ViewModel {
    public final String TITLE_LABEL = "Notification View";

    private NotifState state = new NotifState();

    public static final String NOTIF_BUTTON_LABEL = "Notification";
    private String notifiedUser;

    public NotifViewModel() {
        super("notification");
    }

    public void setState(NotifState state) {
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

    public NotifState getState() {
        return state;
    }


    public String getNotifiedUser() {
        return notifiedUser;
    }

    public void setNotifiedUser(String notifiedUser) {
        this.notifiedUser = notifiedUser;
    }
}

