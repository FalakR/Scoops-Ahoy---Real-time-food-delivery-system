package interface_adapters.login;

import interface_adapters.ViewModel;
import interface_adapters.notification.NotifViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {

    public final String TITLE_LABEL = "Log In View";
    public final String EMAIL_LABEL = "Enter email";
    public final String PASSWORD_LABEL = "Enter password";

    public final String LOGIN_BUTTON_LABEL = "Log in";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private LoginState state = new LoginState();
    private boolean propertyChanged;

    public LoginViewModel() {
        super("log in");
    }

    public void setState(LoginState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        propertyChanged = state.getEmailError() != null || state.getPasswordError() != null || state.isEmailEmpty() || state.isPasswordEmpty();

        if (state.getEmailError() != null) {
            support.firePropertyChange("emailError", null, state);
        }

        if (state.getPasswordError() != null) {
            support.firePropertyChange("passwordError", null, state);
        }

        if (state.isPasswordEmpty()) {
            support.firePropertyChange("passwordEmpty", null, state);
        }

        if (state.isEmailEmpty()) {
            support.firePropertyChange("emailEmpty", null, state);
        }

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public boolean isPropertyChanged() {
        return propertyChanged;
    }

    public LoginState getState() {
        return state;
    }
}
