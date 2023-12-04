package interface_adapters.place_order;

import entities.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlaceOrderStateTest {

    @Test
    public void testCopyConstructor() {
        IceCreamFactory factory = new CommonIceCreamFactory();

        IceCream iceCream1 = factory.create("ChocolateChip","Cookie Dough",10);

        ArrayList<IceCream> list = new ArrayList<>();
        list.add(iceCream1);

        CommonCart cart = new CommonCart(list);
        // Arrange
        PlaceOrderState originalState = new PlaceOrderState();
        originalState.setCart(cart);
        originalState.setIceCreams(list);
        originalState.setAddress("123 Main Street");
        originalState.setAddressError("Invalid address");
        originalState.setCardNumber("1234567890123456");
        originalState.setCardNumberError("Invalid card number");
        originalState.setCvv("123");
        originalState.setExpiryDate("12/25");
        originalState.setExpiryDateError("");
        originalState.setOrderSummary(createOrderSummary(cart.getItems(), originalState.getAddress()));

        // Act
        PlaceOrderState copyState = new PlaceOrderState(originalState);

        // Assert
        assertEquals(originalState.getCart(), copyState.getCart());
        assertEquals(originalState.getIceCreams(), copyState.getIceCreams());
        assertEquals(originalState.getAddress(), copyState.getAddress());
        assertEquals(originalState.getAddressError(), copyState.getAddressError());
        assertEquals(originalState.getCardNumber(), copyState.getCardNumber());
        assertEquals(originalState.getCardNumberError(), copyState.getCardNumberError());
        assertEquals(originalState.getCvv(), copyState.getCvv());
        assertEquals(originalState.getExpiryDate(), copyState.getExpiryDate());
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
    @Test
    public void testSettersAndGetters() {
        IceCreamFactory factory = new CommonIceCreamFactory();

        IceCream iceCream1 = factory.create("ChocolateChip","Cookie Dough",10);

        ArrayList<IceCream> list = new ArrayList<>();
        list.add(iceCream1);

        CommonCart cart = new CommonCart(list);
        // Arrange
        PlaceOrderState state = new PlaceOrderState();
        state.setCart(cart);

        state.setAddress("456 Oak Street");
        state.setAddressError("Valid address");
        state.setCardNumber("9876543210123456");
        state.setCardNumberError("Valid card number");
        state.setCvv("456");
        state.setExpiryDate("06/23");

        // Assert
        assertEquals(cart, state.getCart());
        assertEquals("456 Oak Street", state.getAddress());
        assertEquals("Valid address", state.getAddressError());
        assertEquals("9876543210123456", state.getCardNumber());
        assertEquals("Valid card number", state.getCardNumberError());
        assertEquals("456", state.getCvv());
        assertEquals("06/23", state.getExpiryDate());
    }
}
