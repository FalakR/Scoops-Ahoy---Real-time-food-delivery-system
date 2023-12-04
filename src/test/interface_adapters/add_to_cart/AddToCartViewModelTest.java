package interface_adapters.add_to_cart;

import junit.framework.TestCase;
import org.junit.Test;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class AddToCartViewModelTest extends TestCase {

    public void testFirePropertyChanged() {
        // Arrange
        AddToCartViewModel viewModel = new AddToCartViewModel();
        PropertyChangeSupport support = new PropertyChangeSupport(viewModel);
        PropertyChangeListener listener = evt -> {
            assertEquals("state", evt.getPropertyName());
            assertNull(evt.getOldValue());
            assertNotNull(evt.getNewValue());
        };
        support.addPropertyChangeListener(listener);

        // Act
        viewModel.addPropertyChangeListener(listener);
        viewModel.firePropertyChanged();

        // Assert
        support.removePropertyChangeListener(listener);
    }

    @Test
    public void testSetState() {
        // Arrange
        AddToCartViewModel viewModel = new AddToCartViewModel();
        AddToCartState newState = new AddToCartState();

        // Act
        viewModel.setState(newState);

        // Assert
        assertEquals(newState, viewModel.getState());
    }

    @Test
    public void testGetViewName() {
        // Arrange
        AddToCartViewModel viewModel = new AddToCartViewModel();

        // Assert
        assertEquals("BrowseView", viewModel.getViewName());
    }


}