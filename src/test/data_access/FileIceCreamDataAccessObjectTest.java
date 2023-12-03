package data_access;

import entities.CommonIceCreamFactory;
import entities.IceCream;
import entities.IceCreamFactory;
import org.junit.Before;
import org.junit.Test;
import use_cases.add_to_cart.AddToCartDataAccessInterface;

import java.io.IOException;

import static org.junit.Assert.*;

public class FileIceCreamDataAccessObjectTest {
    private static final String TEST_CSV_PATH = "./icecreamsinfo.csv"; // Replace with your test CSV path

    private AddToCartDataAccessInterface dataAccessObject;

    @Before
    public void setUp() {
        // You may want to create a test CSV file with sample data for testing
        IceCreamFactory iceCreamFactory = new CommonIceCreamFactory();
        try {
            dataAccessObject = new FileIceCreamDataAccessObject(TEST_CSV_PATH, iceCreamFactory);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing FileIceCreamDataAccessObject for testing.", e);
        }
    }

    @Test
    public void getIceCream_ValidName_ReturnsIceCream() {
        // Arrange
        String validIceCreamName = "ChocolateChip";

        // Act
        IceCream iceCream = ((FileIceCreamDataAccessObject) dataAccessObject).getIceCream(validIceCreamName);

        // Assert
        assertNotNull(iceCream);
        assertEquals(validIceCreamName, iceCream.getName());
    }

    @Test
    public void getIceCream_InvalidName_ReturnsNull() {
        // Arrange
        String invalidIceCreamName = "NonexistentFlavor";

        // Act
        IceCream iceCream = ((FileIceCreamDataAccessObject) dataAccessObject).getIceCream(invalidIceCreamName);

        // Assert
        assertNull(iceCream);
    }

}