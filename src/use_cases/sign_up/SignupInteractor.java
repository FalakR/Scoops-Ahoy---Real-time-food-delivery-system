package use_cases.sign_up;

import entities.User;
import entities.UserFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupDataAccessInterface userSignupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByEmail(signupInputData.getEmail())) {
            userPresenter.prepareFailView("User already exists.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(signupInputData.getName(), signupInputData.getEmail(), signupInputData.getPassword(), now);
            userDataAccessObject.save(user);

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
