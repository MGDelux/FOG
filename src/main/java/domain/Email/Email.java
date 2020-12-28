package domain.Email;

/**
 * CREATED BY mathias @ 02-12-2020 - 16:08
 **/
public class Email {
    private final String toAdress;
    private String subject;
    private final String message;

    public Email( String toAdress, String subject, String message) {
        this.toAdress = toAdress;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Email{" +
                " toAdress='" + toAdress + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


    public String getToAdress() {
        return toAdress;
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

}
