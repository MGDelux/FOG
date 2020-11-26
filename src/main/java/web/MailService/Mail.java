package web.MailService;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * CREATED BY mathias @ 25-11-2020 - 15:54
 **/
public class Mail {


    public static synchronized void sendEmail(String host, String port, final String username, final String password, String toAdress, String subject, String message) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username));
        InternetAddress[] toAddresses = { new InternetAddress(toAdress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(message,"text/html; charset=utf-8");
        Transport.send(msg);

    }

}
