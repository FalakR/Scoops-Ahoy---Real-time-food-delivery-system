package entities;

import java.time.LocalDateTime;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(String name, String email, String password, LocalDateTime ltd);
}
