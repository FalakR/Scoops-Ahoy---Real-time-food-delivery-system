package interface_adapters.signup;

import org.junit.Test;
import static org.junit.Assert.*;

public class SignupStateTest {

    @Test
    public void testDefaultConstructor() {
        // Arrange
        SignupState state = new SignupState();

        // Act and Assert
        assertNotNull(state);
        assertEquals("", state.getName());
        assertNull(state.getNameError());
        assertEquals("", state.getEmail());
        assertNull(state.getEmailError());
        assertEquals("", state.getPassword());
        assertNull(state.getPasswordError());
    }

    @Test
    public void testParameterizedConstructor() {
        // Arrange
        SignupState originalState = new SignupState();
        originalState.setName("John");
        originalState.setNameError("Name error");
        originalState.setEmail("john@example.com");
        originalState.setEmailError("Email error");
        originalState.setPassword("password123");
        originalState.setPasswordError("Password error");

        // Act
        SignupState newState = new SignupState(originalState);

        // Assert
        assertNotNull(newState);
        assertNotSame(originalState, newState); // Ensure a new instance is created
        assertEquals("John", newState.getName());
        assertEquals("Name error", newState.getNameError());
        assertEquals("john@example.com", newState.getEmail());
        assertEquals("Email error", newState.getEmailError());
        assertEquals("password123", newState.getPassword());
        assertEquals("Password error", newState.getPasswordError());
    }

    @Test
    public void testSetterMethods() {
        // Arrange
        SignupState state = new SignupState();

        // Act
        state.setName("Alice");
        state.setNameError("Name error");
        state.setEmail("alice@example.com");
        state.setEmailError("Email error");
        state.setPassword("securepassword");
        state.setPasswordError("Password error");

        // Assert
        assertEquals("Alice", state.getName());
        assertEquals("Name error", state.getNameError());
        assertEquals("alice@example.com", state.getEmail());
        assertEquals("Email error", state.getEmailError());
        assertEquals("securepassword", state.getPassword());
        assertEquals("Password error", state.getPasswordError());
    }
}
