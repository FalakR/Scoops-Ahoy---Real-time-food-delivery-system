package interface_adapters.place_order;

import entities.Cart;
import entities.CommonCart;
import entities.IceCream;

import java.util.List;

public class PlaceOrderState {
    private Cart cart;
    private List<IceCream> iceCreams;
    private String address = "";
    private String addressError = null;
    private String cardNumber = "";
    private String cardNumberError = null;
    private int cvv = 0;
    private String expiryDate = "";
    private String expiryDateError = null;
    private String ordersummary;

    public PlaceOrderState(PlaceOrderState copy) {
        cart = copy.cart;
        iceCreams = copy.iceCreams;
        address = copy.address;
        addressError = copy.addressError;
        cardNumber = copy.cardNumber;
        cardNumberError = copy.cardNumberError;
        cvv = copy.cvv;
        expiryDate = copy.expiryDate;

    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public PlaceOrderState() {}
    public Cart getCart() {
        return cart;
    }
    public List<IceCream> getIceCreams() {
        return iceCreams;
    }
    public String getAddress() {
        return address;
    }

    public String getAddressError() {
        return addressError;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardNumberError() {
        return cardNumberError;
    }

    public int getCvv() {
        return cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardNumberError(String cardNumberError) {
        this.cardNumberError = cardNumberError;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setExpiryDateError(String expiryDateError) {
        this.expiryDateError = expiryDateError;
    }

    public void setOrderSummary(String orderSummary) {this.ordersummary = orderSummary;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
