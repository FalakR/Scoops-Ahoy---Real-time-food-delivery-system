package use_cases.place_order;


import data_access.AblyDataAccessObject;
import data_access.InMemoryDataAccessObject;
import entities.*;
import io.ably.lib.types.AblyException;
//import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import use_cases.track_order.*;

import java.util.ArrayList;

public class PlaceOrderInteractorTest extends TestCase{

    public void testPlaceOrderSummaryView() throws AblyException {
        IceCreamFactory factory = new CommonIceCreamFactory();

        IceCream iceCream1 = factory.create("ChocolateChip","Cookie Dough",10);
        ArrayList<IceCream> list = new ArrayList<>();
        list.add(iceCream1);

        CommonCart cart = new CommonCart(list);
        String userAddress = "123 Main St";
        String creditCardNumber = "1234567890123456";
        String cvv = "123";
        String expiryDate = "1223";

        PlaceOrderInputData inputData= new PlaceOrderInputData(cart, userAddress, creditCardNumber,cvv,expiryDate);
        PlaceOrderDataAccessInterface ablyDataAccessObject = new AblyDataAccessObject();
        PlaceOrderUserDataAccessInterface userRepository = new InMemoryDataAccessObject();
        TrackOrderDataAccessInterface mocktrackOrderDataAccessObject =  new AblyDataAccessObject();
        TrackOrderUserDataAccessInterface mocktrackOrderUserDataAccessObject = new InMemoryDataAccessObject();
        TrackOrderOutputBoundary mocktrackOrderPresenter= new MockTrackOrderPresenter();
        TrackOrderInteractor mockTrackOrderInteractor = new TrackOrderInteractor(mocktrackOrderDataAccessObject,
                mocktrackOrderUserDataAccessObject,mocktrackOrderPresenter);


        PlaceOrderOutputBoundary myPresenter = new PlaceOrderOutputBoundary() {
            @Override
            public void prepareSummaryView(PlaceOrderOutputData order) {
                assertNotNull(order.getOrderSummary());
                assertEquals("19 tower Road", order.getUserAddress());
            }


            @Override
            public void prepareFailView(String s) {
                fail("Use case failure is unexpected");
            }
        };
        // Create a custom implementation of PlaceOrderInteractor that allows controlling convertToCoordinates
        PlaceOrderInputBoundary interactor = new PlaceOrderInteractor(myPresenter, ablyDataAccessObject, userRepository, mockTrackOrderInteractor) {

            private Location convertToCoordinates(String userAddress) {
                // Simulate the case where no results are found
                System.err.println("Cannot get location from address");
                return null;
            }
        };

//        interactor.execute(inputData);



    }

    private static class MockTrackOrderPresenter implements TrackOrderOutputBoundary {


        @Override
        public void prepareView(TrackOrderOutputData data) {

        }

        @Override
        public void prepareSuccessView() {

        }
    }
    public void testTestFailureUserAddressEmpty() throws AblyException {
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
        String userAddress = "";
        String creditCardNumber = "1234567890123456";
        String cvv = "123";
        String expiryDate = "1223";

        PlaceOrderInputData inputData= new PlaceOrderInputData(cart, userAddress, creditCardNumber,cvv,expiryDate);
        PlaceOrderDataAccessInterface ablyDataAccessObject = new AblyDataAccessObject();
        PlaceOrderUserDataAccessInterface userRepository = new InMemoryDataAccessObject();
        TrackOrderDataAccessInterface mocktrackOrderDataAccessObject =  new AblyDataAccessObject();
        TrackOrderUserDataAccessInterface mocktrackOrderUserDataAccessObject = new InMemoryDataAccessObject();
        TrackOrderOutputBoundary mocktrackOrderPresenter= new MockTrackOrderPresenter();
        TrackOrderInteractor mockTrackOrderInteractor = new TrackOrderInteractor(mocktrackOrderDataAccessObject,
                mocktrackOrderUserDataAccessObject,mocktrackOrderPresenter);
        PlaceOrderOutputBoundary myPresenter = new PlaceOrderOutputBoundary() {
            @Override
            public void prepareSummaryView(PlaceOrderOutputData order) {
                fail("Use case success is unexpected");
            }


            @Override
            public void prepareFailView(String s) {
                assertEquals("User address cannot be empty.", s);
            }
        };
        PlaceOrderInputBoundary interactor = new PlaceOrderInteractor(myPresenter, ablyDataAccessObject, userRepository,mockTrackOrderInteractor);
        interactor.execute(inputData);

    }

    public void testTestFailureCreditcard() throws AblyException {
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
        String userAddress = "19 Tower Road";
        String creditCardNumber = "";
        String cvv = "123";
        String expiryDate = "1223";

        PlaceOrderInputData inputData= new PlaceOrderInputData(cart, userAddress, creditCardNumber,cvv,expiryDate);
        PlaceOrderDataAccessInterface ablyDataAccessObject = new AblyDataAccessObject();
        PlaceOrderUserDataAccessInterface userRepository = new InMemoryDataAccessObject();
        TrackOrderDataAccessInterface mocktrackOrderDataAccessObject =  new AblyDataAccessObject();
        TrackOrderUserDataAccessInterface mocktrackOrderUserDataAccessObject = new InMemoryDataAccessObject();
        TrackOrderOutputBoundary mocktrackOrderPresenter= new MockTrackOrderPresenter();
        TrackOrderInteractor mockTrackOrderInteractor = new TrackOrderInteractor(mocktrackOrderDataAccessObject,
                mocktrackOrderUserDataAccessObject,mocktrackOrderPresenter);
        PlaceOrderOutputBoundary myPresenter = new PlaceOrderOutputBoundary() {
            @Override
            public void prepareSummaryView(PlaceOrderOutputData order) {
                fail("Use case success is unexpected");
            }


            @Override
            public void prepareFailView(String s) {
                assertEquals("Invalid credit card information.", s);
            }
        };
        PlaceOrderInputBoundary interactor = new PlaceOrderInteractor(myPresenter, ablyDataAccessObject, userRepository,mockTrackOrderInteractor);
        interactor.execute(inputData);

    }

}