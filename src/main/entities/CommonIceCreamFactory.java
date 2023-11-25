package entities;

public class CommonIceCreamFactory implements IceCreamFactory{

    public IceCream create(String name, String flavour, Integer price){
        return new CommonIceCream(name, flavour, price);
    }
}
