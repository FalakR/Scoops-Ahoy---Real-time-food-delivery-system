package use_cases.add_to_cart;

import entities.*;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;


public class AddToCartInteractorTest extends TestCase {

    @Test
    public void testAddToCartSuccess(){
        AddToCartDataAccessInterface mockDataAccess = new MockAddToCartDataAccess();
        AddToCartOutputBoundary mockPresenter = new MockAddToCartPresenter();
        CartFactory mockUserFactory = new MockCartFactory();


        AddToCartInteractor interactor = new AddToCartInteractor(mockDataAccess, mockPresenter, mockUserFactory);

        IceCreamFactory factory = new IceCreamFactory() {
            @Override
            public IceCream create(String name, String flavour, Integer price) {
                return null;
            }
        };

        IceCream iceCream1 = factory.create("ChocolateChip","Cookie Dough",10);

        ArrayList<IceCream> list = new ArrayList<>();
        list.add(iceCream1);

        AddToCartInputData input = new AddToCartInputData(list);

        // Act
        interactor.execute(input);

        // Assert
        // Verify that the presenter's prepareSuccessView() method was called
        MockAddToCartPresenter addToCartPresenter = (MockAddToCartPresenter) mockPresenter;
        assertTrue(addToCartPresenter.isPrepareSuccessViewCalled());

    }



    public void testExecute() {
    }

    // MockAddToCartDataAccess
    private static class MockAddToCartDataAccess implements AddToCartDataAccessInterface {
        private IceCream iceCream;

        public IceCream getIceCream(String identifier) {
            return iceCream;
        }

        public void setIceCream(IceCream iceCream) {
            this.iceCream = iceCream;
        }

    }


    // mock AddToCartPresenter
    private static class MockAddToCartPresenter implements AddToCartOutputBoundary {
        private boolean prepareSuccessViewCalled;

        public void prepareSuccessView(AddToCartOutputData my_cart) {
            prepareSuccessViewCalled = true;
        }

        public boolean isPrepareSuccessViewCalled() {
            return prepareSuccessViewCalled;
        }
    }


    private static class MockCartFactory implements CartFactory {
        public Cart create(ArrayList<IceCream> mylist) {
            return new CommonCart(mylist);
        }
    }


}