package interface_adapters.signup;

import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;

public class SignupViewModelTest {

    @Test
    public void testAddPropertyChangeListener() {
        // Arrange
        SignupViewModel viewModel = new SignupViewModel();
        MockPropertyChangeListener listener = new MockPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);

        // Act
        viewModel.getState().setEmailError("Email already exists");
        viewModel.firePropertyChanged();

        // Assert
        assertEquals("emailError", listener.getPropertyName());
        assertEquals(null, listener.getOldValue());
        assertEquals(viewModel.getState(), listener.getNewValue());
    }

    private static class MockPropertyChangeListener implements PropertyChangeListener {
        private String propertyName;
        private Object oldValue;
        private Object newValue;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            this.propertyName = evt.getPropertyName();
            this.oldValue = evt.getOldValue();
            this.newValue = evt.getNewValue();
        }

        public String getPropertyName() {
            return propertyName;
        }

        public Object getOldValue() {
            return oldValue;
        }

        public Object getNewValue() {
            return newValue;
        }
    }
}
