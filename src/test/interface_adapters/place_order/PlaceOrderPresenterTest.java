package interface_adapters.place_order;

import static org.junit.jupiter.api.Assertions.*;

import entities.CommonCart;
import entities.CommonIceCreamFactory;
import entities.IceCream;
import entities.IceCreamFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartPresenter;
import interface_adapters.add_to_cart.AddToCartPresenterTest;
import interface_adapters.track_order.TrackOrderState;
import interface_adapters.track_order.TrackOrderViewModel;
import org.junit.Before;
import org.junit.Test;
import use_cases.place_order.PlaceOrderOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class PlaceOrderPresenterTest {
    private PlaceOrderPresenter placeOrderPresenter;
    private TestViewManagerModel viewManagerModel;
    private TestPlaceOrderViewModel placeOrderViewModel;
    private TestTrackOrderViewModel trackOrderViewModel;

    @Before
    public void setUp() {
        viewManagerModel = new TestViewManagerModel();
        placeOrderViewModel = new TestPlaceOrderViewModel();
        trackOrderViewModel = new TestTrackOrderViewModel();
        placeOrderPresenter = new PlaceOrderPresenter(placeOrderViewModel,viewManagerModel,trackOrderViewModel);

    }
    @Test
    public void testprepareSummaryView(){
        IceCreamFactory factory = new CommonIceCreamFactory();

        IceCream iceCream1 = factory.create("ChocolateChip","Cookie Dough",10);

        ArrayList<IceCream> list = new ArrayList<>();
        list.add(iceCream1);

        CommonCart cart = new CommonCart(list);
        String userAddress = "123 Main St";
        String creditCardNumber = "1234567890123456";
        String cvv = "123";
        String expiryDate = "1223";

        String summary  = createOrderSummary(list,userAddress);
        PlaceOrderOutputData outputData = new PlaceOrderOutputData(summary, userAddress);
        placeOrderPresenter.prepareSummaryView(outputData);

        assertEquals(1, placeOrderViewModel.getStateCallCount);

        assertEquals(1, placeOrderViewModel.firePropertyChangedCallCount);


        assertEquals(1, viewManagerModel.setActiveViewCallCount);
        assertEquals(1, viewManagerModel.firePropertyChangedCallCount);
    }
    @Test
    public void testprepareFailView(){
        IceCreamFactory factory = new CommonIceCreamFactory();

        IceCream iceCream1 = factory.create("ChocolateChip","Cookie Dough",10);

        ArrayList<IceCream> list = new ArrayList<>();
        list.add(iceCream1);

        CommonCart cart = new CommonCart(list);
        String userAddress = "";
        String creditCardNumber = "1234567890123456";
        String cvv = "123";
        String expiryDate = "1223";

        String summary  = createOrderSummary(list,userAddress);
        PlaceOrderOutputData outputData = new PlaceOrderOutputData(summary, userAddress);
        PlaceOrderState state= new PlaceOrderState();
        placeOrderPresenter.prepareFailView("Order Failed");

        assertEquals(1, placeOrderViewModel.getStateCallCount);

        assertEquals(1, placeOrderViewModel.firePropertyChangedCallCount);
        assertEquals(state.getAddressError(),"Order Failed");

    }
    private String createOrderSummary(List<IceCream> iceCreams, String userAddress) {
        if (iceCreams.isEmpty()) return "No items in the order.";
        StringBuilder orderSummaryBuilder = new StringBuilder("Order Summary:\n");

        double totalPrice = 0.0;

        for (int i = 0; i < iceCreams.size(); i++) {
            IceCream iceCream = iceCreams.get(i);
            orderSummaryBuilder.append(i + 1)
                    .append(". ")
                    .append("Name: ").append(iceCream.getName())
                    .append(", Flavor: ").append(iceCream.getFlavour())
                    .append(", Price: $").append(iceCream.getPrice())
                    .append("\n");

            totalPrice += iceCream.getPrice();
        }
        // Add user address to the summary
        orderSummaryBuilder.append("User Address: ").append(userAddress).append("\n");

        // Add total price to the summary
        orderSummaryBuilder.append("Total Price: $").append(totalPrice);

        return orderSummaryBuilder.toString();

    }
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
    private static class TestTrackOrderViewModel  extends TrackOrderViewModel{
        int getStateCallCount = 0;
        int setStateCallCount = 0;
        int firePropertyChangedCallCount = 0;

        @Override
        public TrackOrderState getState() {
            getStateCallCount++;
            return new TrackOrderState(); // You may need to customize this depending on your actual implementation
        }


        public void setState(PlaceOrderState state) {
            setStateCallCount++;
        }

        @Override
        public void firePropertyChanged() {
            firePropertyChangedCallCount++;
        }
    }
}