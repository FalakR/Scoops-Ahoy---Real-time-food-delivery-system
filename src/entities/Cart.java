package entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<IceCream> items = new ArrayList<>();

    public void addItem(IceCream iceCream) {
        items.add(iceCream);
    }

    public void removeItem(IceCream iceCream) {
        items.remove(iceCream);
    }

    public void clearCart() {
        items.clear();
    }

    public List<IceCream> getItems() {
        return new ArrayList<>(items);
    }

    public double calculateTotalPrice() {
        return items.stream().mapToDouble(IceCream::getPrice).sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder cartContents = new StringBuilder("Cart Contents:\n");

        if (isEmpty()) {
            cartContents.append("Empty\n");
        } else {
            for (IceCream iceCream : items) {
                cartContents.append(String.format("- %s (%s) - $%.2f\n",
                        iceCream.getName(), iceCream.getFlavour(), iceCream.getPrice()));
            }
            cartContents.append(String.format("Total: $%.2f\n", calculateTotalPrice()));
        }

        return cartContents.toString();
    }
}

