package interface_adapters.place_order;

import entities.CommonCart;
import entities.IceCream;
import entities.IceCreamFactory;
import interface_adapters.add_to_cart.AddToCartControllerTest;

import junit.framework.TestCase;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import use_cases.place_order.PlaceOrderInputBoundary;
import use_cases.place_order.PlaceOrderInputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlaceOrderControllerTest extends TestCase{
    @Test
    public void testPlaceOrderController() {

        PlaceOrderInputBoundary mockPlaceOrderUseCaseInteractor = new MockPlaceOrderUseCaseInteractor();
        PlaceOrderController placeOrderController = new PlaceOrderController(mockPlaceOrderUseCaseInteractor);

        IceCreamFactory factory = new IceCreamFactory() {
            @Override
            public IceCream create(String name, String flavour, Integer price) {
                return null;
            }
        };

        IceCream iceCream1 = factory.create("ChocolateChip","Cookie Dough",10);

        ArrayList<IceCream> list = new ArrayList<>();
        list.add(iceCream1);

        CommonCart cart = new CommonCart(list);
        String userAddress = "123 Main St";
        String creditCardNumber = "1234567890123456";
        String cvv = "123";
        String expiryDate = "1223";

        // Execute the place order controller
        placeOrderController.execute(cart, list, userAddress, creditCardNumber, cvv, expiryDate);
        MockPlaceOrderUseCaseInteractor mockInteractor = (MockPlaceOrderUseCaseInteractor)mockPlaceOrderUseCaseInteractor;
        assertTrue(mockInteractor.isExecuteCalled());

    }

    public static class MockPlaceOrderUseCaseInteractor implements PlaceOrderInputBoundary {
        private boolean executeCalled;

        @Override
        public void execute(PlaceOrderInputData placeOrderInputData) {
            executeCalled = true;
        }

        public boolean isExecuteCalled() {
            return executeCalled;
        }
    }

}