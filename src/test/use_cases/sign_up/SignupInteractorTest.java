package use_cases.sign_up;

import entities.CommonUser;
import entities.User;
import entities.UserFactory;
import org.junit.Test;
import use_cases.sign_up.SignupDataAccessInterface;
import use_cases.sign_up.SignupInputData;
import use_cases.sign_up.SignupInteractor;
import use_cases.sign_up.SignupOutputBoundary;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class SignupInteractorTest {

    @Test
    public void testSignupSuccess() {
        // Arrange
        SignupDataAccessInterface mockDataAccess = new MockSignupDataAccess();
        SignupOutputBoundary mockPresenter = new MockSignupPresenter();
        UserFactory mockUserFactory = new MockUserFactory();

        SignupInteractor interactor = new SignupInteractor(mockDataAccess, mockPresenter, mockUserFactory);

        SignupInputData input = new SignupInputData("John Doe", "john@example.com", "password123");

        // Act
        interactor.execute(input);

        // Assert
        // Verify that the presenter's prepareSuccessView() method was called
        MockSignupPresenter signupPresenter = (MockSignupPresenter) mockPresenter;
        assertTrue(signupPresenter.isPrepareSuccessViewCalled());

        // Verify that the data access object's save() method was called
        MockSignupDataAccess signupDataAccess = (MockSignupDataAccess) mockDataAccess;
        assertTrue(signupDataAccess.isSaveCalled());
    }

    @Test
    public void testSignupFailureUserExists() {
        // Arrange
        SignupDataAccessInterface mockDataAccess = new MockSignupDataAccess();
        SignupOutputBoundary mockPresenter = new MockSignupPresenter();
        UserFactory mockUserFactory = new MockUserFactory();

        SignupInteractor interactor = new SignupInteractor(mockDataAccess, mockPresenter, mockUserFactory);

        // Set up the data access object to return true for existsByEmail()
        MockSignupDataAccess signupDataAccess = (MockSignupDataAccess) mockDataAccess;
        signupDataAccess.setExistsByEmailResult(true);

        SignupInputData input = new SignupInputData("John Doe", "john@example.com", "password123");

        // Act
        interactor.execute(input);

        // Assert
        // Verify that the presenter's prepareFailView() method was called with the correct error message
        MockSignupPresenter signupPresenter = (MockSignupPresenter) mockPresenter;
        assertTrue(signupPresenter.isPrepareFailViewCalled());
        assertEquals("User already exists.", signupPresenter.getFailViewErrorMessage());

        // Verify that the data access object's save() method was not called
        assertFalse(signupDataAccess.isSaveCalled());
    }

    // Mock implementation for SignupDataAccessInterface
    private static class MockSignupDataAccess implements SignupDataAccessInterface {
        private boolean existsByEmailResult;
        private boolean saveCalled;

        @Override
        public boolean existsByEmail(String identifier) {
            return existsByEmailResult;
        }

        @Override
        public void save(User user) {
            saveCalled = true;
        }

        public void setExistsByEmailResult(boolean existsByEmailResult) {
            this.existsByEmailResult = existsByEmailResult;
        }

        public boolean isSaveCalled() {
            return saveCalled;
        }
    }

    // Mock implementation for SignupOutputBoundary
    private static class MockSignupPresenter implements SignupOutputBoundary {
        private boolean prepareSuccessViewCalled;
        private boolean prepareFailViewCalled;
        private String failViewErrorMessage;

        @Override
        public void prepareSuccessView() {
            prepareSuccessViewCalled = true;
        }

        @Override
        public void prepareLoginView() {
            // Not needed for this test
        }

        @Override
        public void prepareFailView(String error) {
            prepareFailViewCalled = true;
            failViewErrorMessage = error;
        }

        public boolean isPrepareSuccessViewCalled() {
            return prepareSuccessViewCalled;
        }

        public boolean isPrepareFailViewCalled() {
            return prepareFailViewCalled;
        }

        public String getFailViewErrorMessage() {
            return failViewErrorMessage;
        }
    }

    // Mock implementation for UserFactory
    private static class MockUserFactory implements UserFactory {
        @Override
        public User create(String name, String email, String password, LocalDateTime creationTime) {
            return new CommonUser(name, email, password, creationTime);
        }
    }
}
