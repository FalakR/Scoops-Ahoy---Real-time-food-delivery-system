package entities;

import java.time.LocalDateTime;

class CommonUser implements User {

    private final String name;
    private final String email;
    private final String password;
    private final LocalDateTime creationTime;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String email, String password, LocalDateTime creationTime) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.creationTime = creationTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
