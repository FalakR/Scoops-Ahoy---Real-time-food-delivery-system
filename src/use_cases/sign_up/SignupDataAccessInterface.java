package use_cases.sign_up;

import entities.User;

public interface SignupDataAccessInterface {
    boolean existsByEmail(String identifier);

    void save(User user);
}
