package entities;

public class ValidatorService implements Validator {
    public boolean passwordIsValid(String password) {
        return password != null && password.length() > 5;
    }

    public boolean emailIsValid(String email) {
        return email != null && email.endsWith(".com");
    }
}
