package use_cases.sign_up;

public class SignupOutputData {

    private final String email;
    private String creationTime;

    private boolean useCaseFailed;

    public SignupOutputData(String email, String creationTime, boolean useCaseFailed) {
        this.email = email;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {
        return email;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}
