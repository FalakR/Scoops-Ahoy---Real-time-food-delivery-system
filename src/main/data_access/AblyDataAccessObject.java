package data_access;

import entities.CommonLocation;
import entities.IceCream;
import entities.Location;
import io.ably.lib.realtime.AblyRealtime;
import io.ably.lib.realtime.Channel;
import io.ably.lib.types.AblyException;
import io.ably.lib.types.Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import use_cases.place_order.PlaceOrderDataAccessInterface;
import use_cases.track_order.TrackOrderDataAccessInterface;
import use_cases.track_order.TrackOrderDataAccessObjectSubscriber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AblyDataAccessObject implements TrackOrderDataAccessInterface, PlaceOrderDataAccessInterface {
    private final AblyRealtime realtime;
    private HashMap<String, ArrayList<TrackOrderDataAccessObjectSubscriber>> subs;

    public AblyDataAccessObject() throws AblyException {
        String API_KEY = "iYu0PA.w1HMSA:iH6lsyBOaa2prAUL98XRO2WCpDDaAJGScChSrASPRwg";
        this.realtime = new AblyRealtime(API_KEY);
        this.subs = new HashMap<>();
    }

    /**
     * Publish orders.
     * @param orderItems
     * @param userLoc
     * @return userId - uuid string
     */
    @Override
    public String publish(List<IceCream> orderItems, Location userLoc) {
        String orderId = UUID.randomUUID().toString();

        // Construct JSON object to publish
        HashMap<String, Object> o = new HashMap<>();

        o.put("orderId", orderId);

        ArrayList<BigDecimal> l = new ArrayList<>();

        l.add(userLoc.getX());
        l.add(userLoc.getY());

        o.put("userLoc", l);

        ArrayList<HashMap<String, Object>> h = new ArrayList<>();

        for (IceCream item: orderItems) {
            HashMap<String, Object> q = new HashMap<>();
            q.put("name", item.getName());
            q.put("flavour", item.getFlavour());
            q.put("price", item.getPrice());
            h.add(q);
        }

        o.put("orderItems", h);


        Channel c = this.realtime.channels.get("orders");

        try {
            System.out.println(JSONValue.toJSONString(o));
            c.publish("new_order", JSONValue.toJSONString(o));
        } catch (AblyException e) {
            throw new RuntimeException("Cannot publish message.");
        }

        return orderId;
    }

    private void subscribeHelper(Message message, String orderId) {
        try {
            JSONObject json = (JSONObject) JSONValue.parse(message.data.toString());

            if (json == null) return;

            JSONArray msg = (JSONArray) json.get("courierLocation");

            Location loc = new CommonLocation((double) msg.get(0), (double) msg.get(1));

            for (TrackOrderDataAccessObjectSubscriber sub: this.subs.get(orderId)) {
                sub.onChange(loc);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


//        ArrayList<Double> o = (ArrayList<Double>) json.get("courierLocation");
//
//
//        Location loc = new CommonLocation(o.get(0), o.get(1));
//        for (TrackOrderDataAccessObjectSubscriber sub:this.subs.get(orderId)) {
//            sub.onChange(loc);
//        }
    }

    /**
     * Subscribe to location updates.
     * @param sub - an object that has an onChange method
     * @param orderId - uuid string
     */
    @Override
    public void subscribe(TrackOrderDataAccessObjectSubscriber sub, String orderId) {

        System.out.println("ORDERID IN SUB " + orderId);

        if (!this.subs.containsKey(orderId)) {
            this.subs.put(orderId, new ArrayList<>());
            Channel c = this.realtime.channels.get("order:"+orderId);
            try {
                c.subscribe(
                        message -> this.subscribeHelper(message, orderId)
                );
                System.out.println("Subscribed to channel -> " + c.name);
            } catch (AblyException e) {
                System.err.println(e.getMessage());
            }
        }
        this.subs.get(orderId).add(sub);
    }
}
