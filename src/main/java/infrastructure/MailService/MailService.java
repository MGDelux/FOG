package infrastructure.MailService;

import Repoistory.Email.EmailRepo;
import domain.Email.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * CREATED BY mathias @ 02-12-2020 - 16:17
 **/
public class MailService implements EmailRepo {
    private static final String host = "smtp.gmail.com" ;
    private static final String port = "587";
    private static final String fromAddress = "solidcodefog@gmail.com";
    private final String password;

    public MailService(String password) {
        this.password = password;
    }

    @Override
    public Iterable<Email> getAllMailInfo() {
        return null;
    }

    @Override
    public synchronized Email newEmail(Email email) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        javax.mail.Authenticator auth = new Authenticator() {
            @Override
            public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromAddress,password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(fromAddress));
        InternetAddress[] toAddresses = {new InternetAddress(email.getToAdress())};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(email.getSubject());
        msg.setSentDate(new Date());
        msg.setContent(email.getMessage(), "text/html; charset=utf-8 ");
        Transport.send(msg);
        return email;
    }
}
