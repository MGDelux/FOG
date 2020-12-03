package domain.Email;

/**
 * CREATED BY mathias @ 02-12-2020 - 16:08
 **/
public class Email {
    private final String host = "smtp.gmail.com" ;
    private final String port = "587";
    private final String fromAddress = "solidcodefog@gmail.com";
    private final String password = "O&4#AL+^SF3,"; //plain text gang
    private String toAdress;
    private String subject;
    private String message;

    public Email( String toAdress, String subject, String message) {
        this.toAdress = toAdress;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Email{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", password='" + password + '\'' +
                ", toAdress='" + toAdress + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getToAdress() {
        return toAdress;
    }

    public void setToAdress(String toAdress) {
        this.toAdress = toAdress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
