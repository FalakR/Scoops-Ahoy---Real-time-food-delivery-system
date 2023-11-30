package interface_adapters.place_order;

public class PlaceOrderState {
    private String address = "";
    private String addressError = null;
    private String cardNumber = "";
    private String cardNumberError = null;
    private int cvv = 0; // Assuming CVV is an integer
    private String expiryDate = "";
    private String expiryDateError = null;

    public PlaceOrderState(PlaceOrderState copy) {
        address = copy.address;
        addressError = copy.addressError;
        cardNumber = copy.cardNumber;
        cardNumberError = copy.cardNumberError;
        cvv = copy.cvv;
        expiryDate = copy.expiryDate;
        expiryDateError = copy.expiryDateError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public PlaceOrderState() {}

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

    public String getExpiryDateError() {
        return expiryDateError;
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
}
