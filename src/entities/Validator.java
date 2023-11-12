package entities;

public interface Validator {

    public boolean passwordIsValid(String password);

    public boolean emailIsValid(String email);

}