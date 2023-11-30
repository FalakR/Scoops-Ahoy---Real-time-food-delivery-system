package data_access;

import entities.CommonLocation;
import entities.IceCream;
import entities.Location;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import io.ably.lib.types.*;
import io.ably.lib.realtime.*;
import use_cases.track_order.TrackOrderDataAccessObjectSubscriber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class AblyDataAccessObjectTest {
    AblyDataAccessObject a = null;
    String API_KEY = "iYu0PA.w1HMSA:iH6lsyBOaa2prAUL98XRO2WCpDDaAJGScChSrASPRwg";
    AblyRealtime r = null;
    Message message;
    String orderId;
    List<Message> messages;

    Location userLocation;
    @Before
    public void setUp() throws Exception, AblyException {
        this.a = new AblyDataAccessObject();

        ClientOptions options = new ClientOptions(API_KEY);
        this.r = new AblyRealtime(options);

        this.orderId = "";

    }

//    @Test
    public void publishAndSubscribe() throws AblyException {

        // Test for publish

        ArrayList<IceCream> orderItems = new ArrayList<>();
        orderItems.add(new IceCream() {
            @Override
            public String getName() {
                return "item1";
            }

            @Override
            public String getFlavour() {
                return "Chocolate";
            }

            @Override
            public Integer getPrice() {
                return 12;
            }
        });

        // Wilson Hall
        Location userLoc = new CommonLocation(43.6637432,-79.3951419);

        this.userLocation = userLoc;

        Channel c = this.r.channels.get("orders");
        c.subscribe(
                (Channel.MessageListener) message -> {
                    this.message = message;
                    System.out.println("Received message.");
                }
        );
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Waiting for Ably Subscription...");
        }
        this.orderId = this.a.publish(orderItems, userLoc);
        System.out.println("Orderid: " + this.orderId);

        // Sleep for a few seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Waiting for Ably Message...");
        }

        assertNotNull("Did not publish a message.", this.message);
        assertEquals(this.message.name, "new_order");

        JSONObject json = (JSONObject) JSONValue.parse(String.valueOf(this.message.data));

        assertNotNull(json.get("orderItems"));
        assertNotNull(json.get("userLoc"));
        assertNotNull(json.get("orderId"));
        assertEquals(json.get("orderId"), this.orderId);

        // Test for subscribe

        ArrayList<Location> locations = new ArrayList<>();
        System.out.println("Subscribing to updates in test");

        System.out.println(this.orderId);

        // Subscribe to orderId
        this.a.subscribe(
                location -> {
                    locations.add(location);
                    System.out.println("Location update received.");
                },
                this.orderId
        );

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            System.out.println("Waiting for Ably Message...");
        }

        assertNotEquals("Did not receive a location update", locations.size(), 0);

        // Test whether last locations are sufficiently close.
        Location last = locations.get(locations.size() - 1);
        BigDecimal XDiff = last.getX().subtract(this.userLocation.getX()).abs();
        BigDecimal YDiff = last.getY().subtract(this.userLocation.getY()).abs();

        BigDecimal tolerance = (new BigDecimal(1)).divide(new BigDecimal(10000));
        assertTrue(XDiff.compareTo(tolerance) < 0);
        assertTrue(YDiff.compareTo(tolerance) < 0);

    }
}