package interface_adapters.add_to_cart;

import entities.IceCream;
import entities.IceCreamFactory;
import junit.framework.TestCase;
import org.junit.Test;
import use_cases.add_to_cart.AddToCartInputBoundary;
import use_cases.add_to_cart.AddToCartInputData;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class AddToCartControllerTest extends TestCase {
    @Test
    public void testAddToCartController(){

        AddToCartInputBoundary mockAddToCartUseCaseInteractor = new MockAddToCartUseCaseInteractor();
        AddToCartController addToCartController = new AddToCartController(mockAddToCartUseCaseInteractor);

        IceCreamFactory factory = new IceCreamFactory() {
            @Override
            public IceCream create(String name, String flavour, Integer price) {
                return null;
            }
        };

        IceCream iceCream1 = factory.create("ChocolateChip","Cookie Dough",10);

        ArrayList<IceCream> list = new ArrayList<>();
        list.add(iceCream1);

        addToCartController.execute(list);
        MockAddToCartUseCaseInteractor mockInteractor = (MockAddToCartUseCaseInteractor) mockAddToCartUseCaseInteractor;
        assertTrue(mockInteractor.isExecuteCalled());

    }

    public static class MockAddToCartUseCaseInteractor implements AddToCartInputBoundary{

        private boolean executeCalled;

        public void execute(AddToCartInputData addToCartInputData){executeCalled = true;}
        public boolean isExecuteCalled() {
            return executeCalled;
        }


    }
    @Test
    public void testExecute() {
    }
}