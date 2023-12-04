package data_access;

import entities.CommonUserFactory;
import entities.User;
import entities.UserFactory;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import static org.junit.Assert.*;

public class FileUserDataAccessObjectTest {

    private FileUserDataAccessObject userDao;
    private UserFactory userFactory;

    @Before
    public void setUp() throws IOException {
        // Set up a temporary CSV file for testing
        File tempFile = File.createTempFile("temp_users", ".csv");
        String csvPath = tempFile.getAbsolutePath();

        // Mock UserFactory
        userFactory = new CommonUserFactory();

        // Initialize FileUserDataAccessObject for testing
        userDao = new FileUserDataAccessObject(csvPath, userFactory);
    }

    @Test
    public void testConstructorAndSave() {
        // Arrange
        User user = userFactory.create("John Doe", "john@example.com", "password123", LocalDateTime.now());

        // Act
        userDao.save(user);

        // Assert
        assertTrue(userDao.existsByEmail("john@example.com"));
        assertEquals(user, userDao.get("john@example.com"));
    }

    @Test
    public void testExistsByEmail() {
        // Arrange
        User user = userFactory.create("Alice", "alice@example.com", "password456", LocalDateTime.now());
        userDao.save(user);

        // Assert
        assertTrue(userDao.existsByEmail("alice@example.com"));
        assertFalse(userDao.existsByEmail("nonexistent@example.com"));
    }

    @Test
    public void testGet() {
        // Arrange
        User user = userFactory.create("Bob", "bob@example.com", "securePwd", LocalDateTime.now());
        userDao.save(user);

        // Act
        User retrievedUser = userDao.get("bob@example.com");

        // Assert
        assertEquals(user, retrievedUser);
    }

    // Add more tests for edge cases, exception handling, and other methods

    @Test(expected = RuntimeException.class)
    public void testSaveIOException() throws IOException {
        // Create a read-only file to force an IOException during save
        File readOnlyFile = File.createTempFile("readonly", null);
        readOnlyFile.setReadOnly();

        // Attempt to create a FileUserDataAccessObject with a read-only file
        new FileUserDataAccessObject(readOnlyFile.getAbsolutePath(), userFactory);
    }
}
