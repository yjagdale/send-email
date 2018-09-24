package hello;

public class EmailResponse {
    private final int statuscode;

    public EmailResponse(int statuscode) {
        this.statuscode = statuscode;
    }

    public int getStatuscode() {
        return statuscode;
    }
}
