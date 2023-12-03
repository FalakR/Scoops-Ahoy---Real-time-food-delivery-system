//package use_cases.place_order;
//
//
//import data_access.AblyDataAccessObject;
//import data_access.InMemoryDataAccessObject;
//import data_access.InMemoryUserDataAccessObject;
//import entities.CommonCart;
//import entities.IceCream;
//import io.ably.lib.types.AblyException;
//import org.junit.jupiter.api.Test;
//import use_cases.track_order.TrackOrderInputBoundary;
//import use_cases.track_order.TrackOrderInteractor;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//class PlaceOrderInteractorTest {
//    @Test
//    void PlaceOrderSummaryView() throws AblyException {
//        PlaceOrderInputData inputData= new PlaceOrderInputData(new CommonCart(null), "19 tower Road", "111",Integer.toString(123),"06062026");
//        PlaceOrderDataAccessInterface ablyDataAccessObject = new AblyDataAccessObject();
//        PlaceOrderUserDataAccessInterface userRepository = new InMemoryDataAccessObject();
//
//
//        PlaceOrderOutputBoundary myPresenter = new PlaceOrderOutputBoundary() {
//            @Override
//            public void prepareSummaryView(PlaceOrderOutputData order) {
//                assertNotNull(order.getOrderSummary());
//                assertEquals("19 tower Road", order.getUserAddress());
//            }
//
//
//            public void prepareChangeView() {
//                fail("Screen has already changed");
//            }
//
//            @Override
//            public void prepareFailView(String s) {
//                fail("Use case failure is unexpected");
//            }
//        };
//        //PlaceOrderInputBoundary interactor = new PlaceOrderInteractor(myPresenter, ablyDataAccessObject, userRepository,new TrackOrderInteractor());
//        //interactor.execute(inputData);
//    }
//
//}