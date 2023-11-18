package use_cases.place_order;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PlaceOrderInputData {

    private final String address;

    private final Integer cardNo;

    private final Integer cvv;

    private final Date expiryDate;

    public PlaceOrderInputData(String address, Integer cardNo, Integer cvv, Date expiryDate) {
        this.address = address;
        this.cardNo= cardNo;
        this.cvv=cvv;
        this.expiryDate=expiryDate;
    }

    String getAddress(){return address;}

    Integer getCardNo(){return cardNo;}

    Integer getCvv(){return cvv;}

    String getExpiryDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(expiryDate);
    }






}
