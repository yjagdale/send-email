package hello;

import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtils {
    private static String HOST = "smtp.gmail.com";

    private static Properties setProperties(String USER_NAME, String PASSWORD) {
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
        String USER_NAME = emailDetails.getUserName();
        String PASSWORD = emailDetails.getPassword();
        Session session = Session.getDefaultInstance(setProperties(USER_NAME, PASSWORD));
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
            message.setContent(body, "text/html; charset=utf-8");
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
