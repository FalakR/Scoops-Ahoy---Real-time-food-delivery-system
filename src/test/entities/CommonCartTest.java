package entities;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;

public class CommonCartTest extends TestCase {


    private CommonCart cart;

    IceCreamFactory factory = new IceCreamFactory() {
        @Override
        public IceCream create(String name, String flavour, Integer price) {
            return null;
        }
    };

    public void setUp() throws Exception {
    IceCream iceCream1 = factory.create("ChocolateChip", "Cookie Dough", 10);

    ArrayList<IceCream> mylist = new ArrayList<>();
    mylist.add(iceCream1);

    this.cart = new CommonCart(mylist);}


    @Test
    public void testItems() {

        IceCream iceCream1 = factory.create("ChocolateChip", "Cookie Dough", 10);

        ArrayList<IceCream> mylist = new ArrayList<>();
        mylist.add(iceCream1);

        assertEquals(mylist, this.cart.getItems());
    }

    @Test
    public void testAddItems(IceCream iceCream){

        IceCream iceCream1 = factory.create("ChocolateChip", "Cookie Dough", 10);

        ArrayList<IceCream> mylist = new ArrayList<>();
        mylist.add(iceCream1);
        mylist.add(iceCream);

        this.cart.addItems(iceCream);
        assertEquals(mylist, this.cart.getItems());
    }

    @Test
    public void testGetItems() {
    }

    @Test
    public void testAddItems() {
    }
}