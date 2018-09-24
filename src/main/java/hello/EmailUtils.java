package hello;

import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtils {
    private static String USER_NAME = "correlation.jenkins";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "********"; // GMail password
    private static String RECIPIENT = "yjagdale@qualys.com";
    private static String HOST = "smtp.gmail.com";

    private static Properties setProperties() {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.user", USER_NAME);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        return props;
    }

    public static int sendGmailEmail(EmailDetails emailDetails) {

        String[]  to  = emailDetails.getTo();
        String subject = emailDetails.getSubject();
        String body = emailDetails.getBody();
        Session session = Session.getDefaultInstance(setProperties());
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress("Qualys Jenkins<correlation.jenkins@qualys.com>"));

            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, USER_NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return 200;
        }
        catch (AddressException ae) {
            ae.printStackTrace();
            return 400;
        }
        catch (MessagingException me) {
            me.printStackTrace();
            return 500;
        }
    }
}
