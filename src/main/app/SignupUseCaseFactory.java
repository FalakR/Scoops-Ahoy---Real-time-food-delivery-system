package app;

import data_access.FileUserDataAccessObject;

import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartViewModel;

import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupController;
import interface_adapters.signup.SignupPresenter;
import interface_adapters.signup.SignupViewModel;
import entities.CommonUserFactory;
import entities.UserFactory;
import use_cases.sign_up.SignupDataAccessInterface;
import use_cases.sign_up.SignupInputBoundary;
import use_cases.sign_up.SignupInteractor;
import use_cases.sign_up.SignupOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(

            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, AddToCartViewModel browseViewModel, FileUserDataAccessObject userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, browseViewModel, userDataAccessObject);
            SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel, browseViewModel);
            return new SignupView(signupController, signupPresenter, signupViewModel);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }


    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, AddToCartViewModel browseViewModel, SignupDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel, browseViewModel);


        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}