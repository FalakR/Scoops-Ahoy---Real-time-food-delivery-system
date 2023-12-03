package data_access;

import entities.CommonLocation;
import entities.IceCream;
import entities.Location;
import io.ably.lib.types.AblyException;
import org.junit.Test;
import use_cases.track_order.TrackOrderDataAccessObjectSubscriber;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AblyDataAccessObjectTest {
    @Test
    public void ablyTest() throws AblyException {
        // This test requires server!

        // Arrange
        AblyDataAccessObject ab = new AblyDataAccessObject();

        Location userLoc = new CommonLocation(43.659383, -79.381846);
        List<IceCream> iceCreams = new ArrayList<>();

        List<Location> locationsReceived = new ArrayList<>();


        // Act
        String orderId = ab.publish(iceCreams, userLoc);

        // Add to list of locations
        ab.subscribe(locationsReceived::add, orderId);

        // Wait
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Assert
        assertNotEquals(
                0,
                locationsReceived.size()
        );


    }
}