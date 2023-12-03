package interface_adapters.signup;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {

    public final String TITLE_LABEL = "Sign Up View";
    public final String NAME_LABEL = "Enter name";
    public final String EMAIL_LABEL = "Enter email";
    public final String PASSWORD_LABEL = "Enter password";

    public final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String LOGIN_BUTTON_LABEL = "Log In";

    private SignupState state = new SignupState();
    private boolean propertyChanged;

    public SignupViewModel() {
        super("sign up");
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        propertyChanged = state.getEmailError() != null;

        if (propertyChanged) {
            support.firePropertyChange("emailError", null, this.state);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getState() {
        return state;
    }

    public boolean isPropertyChanged() {
        return propertyChanged;
    }
}
