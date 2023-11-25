package use_cases.sign_up;

public class SignupInputData {

    final private String name;
    final private String email;
    final private String password;

    public SignupInputData(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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
