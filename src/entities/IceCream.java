package entities;

public class IceCream {
    private final String name;
    private final String flavor;
    private final double price;

    public IceCream(String name, String flavor, double price) {
        this.name = name;
        this.flavor = flavor;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getFlavour() {
        return flavor;
    }

    public double getPrice() {
        return price;
    }
}
