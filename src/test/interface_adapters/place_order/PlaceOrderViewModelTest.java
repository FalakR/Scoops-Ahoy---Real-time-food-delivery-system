package interface_adapters.place_order;

import interface_adapters.place_order.PlaceOrderState;
import interface_adapters.place_order.PlaceOrderViewModel;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.junit.Assert.*;

public class PlaceOrderViewModelTest {

    @Test
    public void testFirePropertyChangedNoErrors() {
        // Arrange
        PlaceOrderViewModel viewModel = new PlaceOrderViewModel();
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
    public void testFirePropertyChangedWithAddressError() {
        // Arrange
        PlaceOrderViewModel viewModel = new PlaceOrderViewModel();
        PropertyChangeSupport support = new PropertyChangeSupport(viewModel);
        PropertyChangeListener listener = evt -> {
            assertEquals("addressError", evt.getPropertyName());
            assertNotNull(evt.getNewValue());
            assertNull(evt.getOldValue());
        };
        support.addPropertyChangeListener(listener);

        // Act
        viewModel.addPropertyChangeListener(listener);
        viewModel.getState().setAddressError("Invalid address");
        viewModel.firePropertyChanged();

        // Assert
        assertTrue(viewModel.isPropertyChanged());
        support.removePropertyChangeListener(listener);
    }

    @Test
    public void testFirePropertyChangedWithCardNumberError() {
        // Arrange
        PlaceOrderViewModel viewModel = new PlaceOrderViewModel();
        PropertyChangeSupport support = new PropertyChangeSupport(viewModel);
        PropertyChangeListener listener = evt -> {
            assertEquals("cardNumberError", evt.getPropertyName());
            assertNotNull(evt.getNewValue());
            assertNull(evt.getOldValue());
        };
        support.addPropertyChangeListener(listener);

        // Act
        viewModel.addPropertyChangeListener(listener);
        viewModel.getState().setCardNumberError("Invalid card number");
        viewModel.firePropertyChanged();

        // Assert
        assertTrue(viewModel.isPropertyChanged());
        support.removePropertyChangeListener(listener);
    }

    // Add more test cases as needed
}
