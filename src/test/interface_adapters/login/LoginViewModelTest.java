package interface_adapters.login;

import interface_adapters.login.LoginState;
import interface_adapters.login.LoginViewModel;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.junit.Assert.*;

public class LoginViewModelTest {

    @Test
    public void testFirePropertyChangedNoErrors() {
        // Arrange
        LoginViewModel viewModel = new LoginViewModel();
        PropertyChangeSupport support = new PropertyChangeSupport(viewModel);
        PropertyChangeListener listener = evt -> fail("Should not be called");
        support.addPropertyChangeListener(listener);

        // Act
        viewModel.addPropertyChangeListener(listener);
        viewModel.firePropertyChanged();

        // Assert
        assertFalse(viewModel.isPropertyChanged());
        support.removePropertyChangeListener(listener);
    }

    @Test
    public void testFirePropertyChangedWithEmailError() {
        // Arrange
        LoginViewModel viewModel = new LoginViewModel();
        PropertyChangeSupport support = new PropertyChangeSupport(viewModel);
        PropertyChangeListener listener = evt -> {
            assertEquals("emailError", evt.getPropertyName());
            assertNotNull(evt.getNewValue());
            assertNull(evt.getOldValue());
        };
        support.addPropertyChangeListener(listener);

        // Act
        viewModel.addPropertyChangeListener(listener);
        viewModel.getState().setEmailError("Invalid email");
        viewModel.firePropertyChanged();

        // Assert
        assertTrue(viewModel.isPropertyChanged());
        support.removePropertyChangeListener(listener);
    }

    @Test
    public void testFirePropertyChangedWithPasswordError() {
        // Arrange
        LoginViewModel viewModel = new LoginViewModel();
        PropertyChangeSupport support = new PropertyChangeSupport(viewModel);
        PropertyChangeListener listener = evt -> {
            assertEquals("passwordError", evt.getPropertyName());
            assertNotNull(evt.getNewValue());
            assertNull(evt.getOldValue());
        };
        support.addPropertyChangeListener(listener);

        // Act
        viewModel.addPropertyChangeListener(listener);
        viewModel.getState().setPasswordError("Invalid password");
        viewModel.firePropertyChanged();

        // Assert
        assertTrue(viewModel.isPropertyChanged());
        support.removePropertyChangeListener(listener);
    }

    @Test
    public void testFirePropertyChangedWithEmailEmpty() {
        // Arrange
        LoginViewModel viewModel = new LoginViewModel();
        PropertyChangeSupport support = new PropertyChangeSupport(viewModel);
        PropertyChangeListener listener = evt -> {
            assertEquals("emailEmpty", evt.getPropertyName());
            assertNotNull(evt.getNewValue());
            assertNull(evt.getOldValue());
        };
        support.addPropertyChangeListener(listener);

        // Act
        viewModel.addPropertyChangeListener(listener);
        viewModel.getState().setEmailEmpty(true);
        viewModel.firePropertyChanged();

        // Assert
        assertTrue(viewModel.isPropertyChanged());
        support.removePropertyChangeListener(listener);
    }

    @Test
    public void testFirePropertyChangedWithPasswordEmpty() {
        // Arrange
        LoginViewModel viewModel = new LoginViewModel();
        PropertyChangeSupport support = new PropertyChangeSupport(viewModel);
        PropertyChangeListener listener = evt -> {
            assertEquals("passwordEmpty", evt.getPropertyName());
            assertNotNull(evt.getNewValue());
            assertNull(evt.getOldValue());
        };
        support.addPropertyChangeListener(listener);

        // Act
        viewModel.addPropertyChangeListener(listener);
        viewModel.getState().setPasswordEmpty(true);
        viewModel.firePropertyChanged();

        // Assert
        assertTrue(viewModel.isPropertyChanged());
        support.removePropertyChangeListener(listener);
    }

    // Add more test cases as needed
}
