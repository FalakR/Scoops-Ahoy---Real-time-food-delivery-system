package use_cases.track_order;

import entities.CommonLocation;
import entities.Location;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrackOrderInteractorTest {

    @Test
    public void test() {

        // Arrange
        final int[] subscribeCallCount = {0};
        String orderId = "some_order_id";
        final String[] returnedOrderId = {null};
        final TrackOrderDataAccessObjectSubscriber[] sub = new TrackOrderDataAccessObjectSubscriber[1];
        Location userLocation = new CommonLocation(1.0, 1.0);
        Location deliveryAgentLocation = new CommonLocation(2.0, 2.0);
        List<TrackOrderOutputData> sentToPresenter = new ArrayList<>();

        TrackOrderInputBoundary interactor = new TrackOrderInteractor(
                (s, orderId1) -> {
                    subscribeCallCount[0] += 1;
                    sub[0] = s;
                    returnedOrderId[0] = orderId1;
                },
                new TrackOrderUserDataAccessInterface() {
                    @Override
                    public String getOrderId() {
                        return orderId;
                    }

                    @Override
                    public Location getUserLocation() {
                        return userLocation;
                    }
                },
                new TrackOrderOutputBoundary() {
                    @Override
                    public void prepareView(TrackOrderOutputData data) {
                        sentToPresenter.add(data);
                    }

                    @Override
                    public void prepareSuccessView() {

                    }
                }
        );
        // Act 1: execute

        interactor.execute();

        // Assert

        assertEquals(1, subscribeCallCount[0]);


        // Act 2: call onChange
        sub[0].onChange(
                deliveryAgentLocation
        );

        assertEquals(
                1,
                sentToPresenter.size()
        );
        assertEquals(
                userLocation,
                sentToPresenter.get(0).userLocation
        );
        assertEquals(
                deliveryAgentLocation,
                sentToPresenter.get(0).deliveryAgentLocation
        );
    }
}