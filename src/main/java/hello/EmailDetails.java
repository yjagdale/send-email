package hello;

public class EmailDetails {



    private final String subject;
    private final String[] to;
    private final String body;

    public EmailDetails(String subject, String[] to, String body) {
        this.subject = subject;
        this.to = to;
        this.body = body;
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
}
