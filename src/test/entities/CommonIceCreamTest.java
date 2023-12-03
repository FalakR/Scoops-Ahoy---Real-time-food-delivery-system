package entities;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommonIceCreamTest extends TestCase {

    private CommonIceCream iceCream;

    public void setUp() throws Exception {
        this.iceCream = new CommonIceCream("ChocolateChip","Cookie Dough",10);}

    @Test
    public void testName() {
        assertEquals("ChocolateChip", this.iceCream.getName());
    }

    @Test
    public void testFlavour() {
        assertEquals("Cookie Dough", this.iceCream.getFlavour());
    }

    @Test
    public void testPrice() {
        assertEquals("10", Integer.toString(this.iceCream.getPrice()));
    }

    @Test
    public void testTestGetName() {
    }

    @Test
    public void testGetFlavour() {
    }

    @Test
    public void testGetPrice() {
    }
}