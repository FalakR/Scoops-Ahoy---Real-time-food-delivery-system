package entities;

public class CommonIceCream implements IceCream{

    private final String name;
    private final String flavour;
    private final Integer price;

    CommonIceCream(String name, String flavour, Integer price) {
        this.name = name;
        this.flavour = flavour;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getFlavour() {
        return flavour;
    }

    public Integer getPrice(){
        return price;
    }



}
