package use_cases.log_in;

import entities.CommonUser;
import entities.User;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginInteractorTest {

    @Test
    public void testLoginSuccess() {
        // Arrange
        LoginDataAccessInterface mockDataAccess = new MockLoginDataAccess();
        LoginOutputBoundary mockPresenter = new MockLoginPresenter();

        LoginInteractor interactor = new LoginInteractor(mockDataAccess, mockPresenter);

        String email = "john@example.com";
        String password = "password123";
        LoginInputData input = new LoginInputData(email, password);

        // Stubbing user creation
        User user = new CommonUser("John Doe", email, password, LocalDateTime.now());
        ((MockLoginDataAccess) mockDataAccess).setExistsByEmailResult(true);
        ((MockLoginDataAccess) mockDataAccess).setUser(user);

        // Act
        interactor.execute(input);

        // Assert
        assertTrue(((MockLoginPresenter) mockPresenter).isPrepareSuccessViewCalled());
    }

    @Test
    public void testLoginFailureEmailNotFound() {
        // Arrange
        LoginDataAccessInterface mockDataAccess = new MockLoginDataAccess();
        LoginOutputBoundary mockPresenter = new MockLoginPresenter();

        LoginInteractor interactor = new LoginInteractor(mockDataAccess, mockPresenter);

        String email = "nonexistent@example.com";
        String password = "password123";
        LoginInputData input = new LoginInputData(email, password);

        // Stubbing user creation
        ((MockLoginDataAccess) mockDataAccess).setExistsByEmailResult(false);

        // Act
        interactor.execute(input);

        // Assert
        assertTrue(((MockLoginPresenter) mockPresenter).isPrepareFailViewEmailCalled());
        assertFalse(((MockLoginPresenter) mockPresenter).isPrepareFailViewPasswordCalled());
        assertFalse(((MockLoginPresenter) mockPresenter).isPrepareSuccessViewCalled());
    }

    @Test
    public void testLoginFailureIncorrectPassword() {
        // Arrange
        LoginDataAccessInterface mockDataAccess = new MockLoginDataAccess();
        LoginOutputBoundary mockPresenter = new MockLoginPresenter();

        LoginInteractor interactor = new LoginInteractor(mockDataAccess, mockPresenter);

        String email = "john@example.com";
        String correctPassword = "correctPassword";
        String incorrectPassword = "incorrectPassword";
        LoginInputData input = new LoginInputData(email, incorrectPassword);

        // Stubbing user creation
        User user = new CommonUser("John Doe", email, correctPassword, LocalDateTime.now());
        ((MockLoginDataAccess) mockDataAccess).setExistsByEmailResult(true);
        ((MockLoginDataAccess) mockDataAccess).setUser(user);

        // Act
        interactor.execute(input);

        // Assert
        assertFalse(((MockLoginPresenter) mockPresenter).isPrepareSuccessViewCalled());
        assertTrue(((MockLoginPresenter) mockPresenter).isPrepareFailViewPasswordCalled());
        assertFalse(((MockLoginPresenter) mockPresenter).isPrepareFailViewEmailCalled());
    }

    // Mock implementation for LoginDataAccessInterface
    private static class MockLoginDataAccess implements LoginDataAccessInterface {
        private boolean existsByEmailResult;
        private User user;

        @Override
        public boolean existsByEmail(String identifier) {
            return existsByEmailResult;
        }

        @Override
        public void save(User user) {
            // Not needed for this test
        }

        @Override
        public User get(String email) {
            return user;
        }

        public void setExistsByEmailResult(boolean existsByEmailResult) {
            this.existsByEmailResult = existsByEmailResult;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    // Mock implementation for LoginOutputBoundary
    private static class MockLoginPresenter implements LoginOutputBoundary {
        private boolean prepareSuccessViewCalled;
        private boolean prepareFailViewEmailCalled;
        private boolean prepareFailViewPasswordCalled;

        @Override
        public void prepareSuccessView() {
            prepareSuccessViewCalled = true;
        }

        @Override
        public void prepareFailViewEmail(String error) {
            prepareFailViewEmailCalled = true;
        }

        @Override
        public void prepareFailViewPassword(String error) {
            prepareFailViewPasswordCalled = true;
        }

        public boolean isPrepareSuccessViewCalled() {
            return prepareSuccessViewCalled;
        }

        public boolean isPrepareFailViewEmailCalled() {
            return prepareFailViewEmailCalled;
        }

        public boolean isPrepareFailViewPasswordCalled() {
            return prepareFailViewPasswordCalled;
        }
    }
}
