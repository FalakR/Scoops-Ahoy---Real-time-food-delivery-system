package interface_adapters.signup;

import entities.CommonUser;
import entities.User;
import junit.framework.TestCase;
import org.junit.Test;
import use_cases.log_in.LoginDataAccessInterface;
import use_cases.log_in.LoginInputData;
import use_cases.log_in.LoginInteractor;
import use_cases.log_in.LoginOutputBoundary;
import use_cases.sign_up.SignupInputBoundary;
import use_cases.sign_up.SignupInputData;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignupControllerTest {

    @Test
    public void testSignupController() {
        // Arrange
        SignupInputBoundary mockSignupUseCaseInteractor = new MockSignupUseCaseInteractor();
        SignupController signupController = new SignupController(mockSignupUseCaseInteractor);

        // Act
        signupController.execute("John Doe", "john@example.com", "password123");

        // Assert
        MockSignupUseCaseInteractor mockInteractor = (MockSignupUseCaseInteractor) mockSignupUseCaseInteractor;
        assertTrue(mockInteractor.isExecuteCalled());
    }

    // Mock implementation for SignupInputBoundary
    private static class MockSignupUseCaseInteractor implements SignupInputBoundary {
        private boolean executeCalled;

        @Override
        public void execute(SignupInputData signupInputData) {
            executeCalled = true;
        }

        public boolean isExecuteCalled() {
            return executeCalled;
        }
    }
}
