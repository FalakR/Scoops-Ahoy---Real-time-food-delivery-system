package entities;

import java.time.LocalDateTime;

public interface User {

    String getName();

    String getEmail();

    String getPassword();

    LocalDateTime getCreationTime();
}