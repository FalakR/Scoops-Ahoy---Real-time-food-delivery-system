package interface_adapters.add_to_cart;

import entities.Cart;
import entities.CartFactory;
import entities.IceCream;
import entities.IceCreamFactory;
import junit.framework.TestCase;
import interface_adapters.ViewManagerModel;
import interface_adapters.place_order.PlaceOrderState;
import interface_adapters.place_order.PlaceOrderViewModel;
import use_cases.add_to_cart.AddToCartOutputData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AddToCartPresenterTest {

    private AddToCartPresenter addToCartPresenter;
    private TestViewManagerModel viewManagerModel;
    private TestAddToCartViewModel addToCartViewModel;
    private TestPlaceOrderViewModel placeOrderViewModel;

    @Before
    public void setUp() {
        viewManagerModel = new TestViewManagerModel();
        addToCartViewModel = new TestAddToCartViewModel();
        placeOrderViewModel = new TestPlaceOrderViewModel();

        addToCartPresenter = new AddToCartPresenter(viewManagerModel, addToCartViewModel, placeOrderViewModel);
    }

    @Test
    public void testPrepareSuccessView() {

        IceCreamFactory factory = new IceCreamFactory() {
            @Override
            public IceCream create(String name, String flavour, Integer price) {
                return null;
            }
        };

        IceCream iceCream1 = factory.create("ChocolateChip","Cookie Dough",10);

        ArrayList<IceCream> list = new ArrayList<>();
        list.add(iceCream1);


        CartFactory cartFactory = new CartFactory() {
            @Override
            public Cart create(ArrayList<IceCream> items) {
                return null;
            }
        };

        Cart my_cart = cartFactory.create(list);
        // Given
        AddToCartOutputData cartOutputData = new AddToCartOutputData(my_cart);

        // When
        addToCartPresenter.prepareSuccessView(cartOutputData);

        // Then
        assertEquals(1, placeOrderViewModel.getStateCallCount);
        assertEquals(1, placeOrderViewModel.setStateCallCount);
        assertEquals(1, placeOrderViewModel.firePropertyChangedCallCount);

        assertEquals(1, viewManagerModel.setActiveViewCallCount);
        assertEquals(1, viewManagerModel.firePropertyChangedCallCount);

        // You might want to further assert the state of objects or validate the arguments passed to the methods
    }

    // Add more test methods to cover other scenarios and edge cases

    private static class TestViewManagerModel extends ViewManagerModel {
        int setActiveViewCallCount = 0;
        int firePropertyChangedCallCount = 0;

        @Override
        public void setActiveView(String viewName) {
            setActiveViewCallCount++;
        }

        @Override
        public void firePropertyChanged() {
            firePropertyChangedCallCount++;
        }
    }

    private static class TestAddToCartViewModel extends AddToCartViewModel {
        // Implement methods if needed for testing
    }

    private static class TestPlaceOrderViewModel extends PlaceOrderViewModel {
        int getStateCallCount = 0;
        int setStateCallCount = 0;
        int firePropertyChangedCallCount = 0;

        @Override
        public PlaceOrderState getState() {
            getStateCallCount++;
            return new PlaceOrderState(); // You may need to customize this depending on your actual implementation
        }

        @Override
        public void setState(PlaceOrderState state) {
            setStateCallCount++;
        }

        @Override
        public void firePropertyChanged() {
            firePropertyChangedCallCount++;
        }
    }
}
