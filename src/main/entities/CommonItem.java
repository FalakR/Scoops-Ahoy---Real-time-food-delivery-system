package entities;

class CommonItem implements Item {

    private final String name;
    private final Integer code;
    private final Double price;

    CommonItem(String name, Integer code, Double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public Double getPrice() {
        return price;
    }
}
