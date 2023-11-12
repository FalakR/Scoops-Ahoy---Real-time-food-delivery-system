package interface_adapters.signup;

public class SignupState {
    private String name = "";
    private String nameError = null;
    private String email = "";
    private String emailError = null;
    private String password = "";
    private String passwordError = null;

    public SignupState(SignupState copy) {
        name = copy.name;
        nameError = copy.nameError;
        email = copy.email;
        emailError = copy.emailError;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SignupState() {}

    public String getName() {
        return name;
    }

    public String getNameError() {
        return nameError;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailError() {
        return emailError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }
}
