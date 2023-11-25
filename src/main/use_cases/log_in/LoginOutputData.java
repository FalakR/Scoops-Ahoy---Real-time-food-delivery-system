package use_cases.log_in;

public class LoginOutputData {

    private final String email;
    private boolean useCaseFailed;

    public LoginOutputData(String email, boolean useCaseFailed) {
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {
        return email;
    }

}
