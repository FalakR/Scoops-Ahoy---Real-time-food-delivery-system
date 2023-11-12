package use_cases.sign_up;

public class SignupInputData {

    final private String name;
    final private String email;
    final private String password;

    public SignupInputData(String username, String password, String repeatPassword) {
        this.name = username;
        this.email = password;
        this.password = repeatPassword;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
