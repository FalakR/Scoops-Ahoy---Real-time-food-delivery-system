package view;

import data_access.FileIceCreamDataAccessObject;
import entities.CommonIceCreamFactory;
import entities.IceCream;
import entities.IceCreamFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartController;
import interface_adapters.add_to_cart.AddToCartPresenter;
import interface_adapters.add_to_cart.AddToCartViewModel;
import interface_adapters.place_order.PlaceOrderViewModel;
import org.junit.Before;
import org.junit.Test;
import use_cases.add_to_cart.AddToCartInputBoundary;
import use_cases.add_to_cart.AddToCartOutputData;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class BrowseViewTest {


    private AddToCartViewModel addToCartViewModel1;
    private ViewManagerModel viewManagerModel;
    private PlaceOrderViewModel placeOrderViewModel;
    private TestAddToCartController addToCartController;
    private TestAddToCartPresenter addToCartPresenter;
    private TestAddToCartViewModel addToCartViewModel;
    private TestFileIceCreamDataAccessObject fileIceCreamDataAccessObject;
    private BrowseView browseView;
    private AddToCartInputBoundary addToCartInputBoundary;

    @Before
    public void setUp() throws IOException {

        IceCreamFactory iceCreamFactory1 = new CommonIceCreamFactory();


        addToCartController = new TestAddToCartController(addToCartInputBoundary);
        addToCartPresenter = new TestAddToCartPresenter(viewManagerModel, addToCartViewModel1, placeOrderViewModel);
        addToCartViewModel = new TestAddToCartViewModel();
        fileIceCreamDataAccessObject = new TestFileIceCreamDataAccessObject(iceCreamFactory1);

        browseView = new BrowseView(addToCartController, addToCartPresenter, addToCartViewModel, fileIceCreamDataAccessObject);
    }

    @Test
    public void testActionPerformed() {
        browseView.actionPerformed(null);

    }

    @Test
    public void testPropertyChange() {
        browseView.propertyChange(null);
    }

    // Mock implementation of AddToCartController for testing
    static class TestAddToCartController extends AddToCartController {

        private boolean executeCalled = false;

        public TestAddToCartController(AddToCartInputBoundary addToCartUseCaseInteractor) {
            super(addToCartUseCaseInteractor);
        }

        @Override
        public void execute(ArrayList<IceCream> iceCreams) {
            executeCalled = true;
        }

        public boolean isExecuteCalled() {
            return executeCalled;
        }
    }

    // Mock implementation of AddToCartPresenter for testing
    static class TestAddToCartPresenter extends AddToCartPresenter {

        private boolean prepareSuccessViewCalled = false;

        public TestAddToCartPresenter(ViewManagerModel viewManagerModel, AddToCartViewModel addToCartViewModel, PlaceOrderViewModel placeOrderViewModel) {
            super(viewManagerModel, addToCartViewModel, placeOrderViewModel);
        }

        @Override
        public void prepareSuccessView(AddToCartOutputData cart) {
            prepareSuccessViewCalled = true;
        }

        public boolean isPrepareSuccessViewCalled() {
            return prepareSuccessViewCalled;
        }
    }

    // Mock implementation of AddToCartViewModel for testing
    static class TestAddToCartViewModel extends AddToCartViewModel {

    }

    // Mock implementation of FileIceCreamDataAccessObject for testing

    static class TestFileIceCreamDataAccessObject extends FileIceCreamDataAccessObject {

        private static final String TEST_CSV_PATH = "./icecreamsinfo.csv";

        public TestFileIceCreamDataAccessObject(IceCreamFactory iceCreamFactory) throws IOException {
            super(TEST_CSV_PATH, iceCreamFactory);
        }

        @Override
        public IceCream getIceCream(String name) {
            // Implement custom behavior for testing
            return super.getIceCream(name);
        }
    }
}
