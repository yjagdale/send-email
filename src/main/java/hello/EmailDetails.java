package hello;

public class EmailDetails {



    private final String subject;
    private final String[] to;
    private final String body;
    private final String username;
    private final String password;

    public EmailDetails(String subject, String[] to, String body, String username, String password) {
        this.subject = subject;
        this.to = to;
        this.body = body;
        this.username = username;
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public String[] getTo() {
        return this.to;
    }

    public String getBody() {
        return this.body;
    }

    public String getUserName() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
