package interface_adapters.login;

import junit.framework.TestCase;
import interface_adapters.login.LoginState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginStateTest {

    @Test
    public void testCopyConstructor() {
        // Arrange
        LoginState originalState = new LoginState();
        originalState.setEmail("test@example.com");
        originalState.setEmailError("Email error");
        originalState.setPassword("password123");
        originalState.setPasswordError("Password error");

        // Act
        LoginState copyState = new LoginState(originalState);

        // Assert
        assertEquals(originalState.getEmail(), copyState.getEmail());
        assertEquals(originalState.getEmailError(), copyState.getEmailError());
        assertEquals(originalState.getPassword(), copyState.getPassword());
        assertEquals(originalState.getPasswordError(), copyState.getPasswordError());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        LoginState state = new LoginState();
        state.setEmail("test@example.com");
        state.setEmailError("Email error");
        state.setPassword("password123");
        state.setPasswordError("Password error");

        // Assert
        assertEquals("test@example.com", state.getEmail());
        assertEquals("Email error", state.getEmailError());
        assertEquals("password123", state.getPassword());
        assertEquals("Password error", state.getPasswordError());
    }
}
