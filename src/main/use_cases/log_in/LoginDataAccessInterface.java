package use_cases.log_in;

import entities.User;

public interface LoginDataAccessInterface {
    boolean existsByEmail(String identifier);

    void save(User user);

    User get(String email);
}
