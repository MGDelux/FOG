package Repoistory.Email;

import domain.Email.Email;

import javax.mail.MessagingException;

/**
 * CREATED BY mathias @ 02-12-2020 - 16:15
 **/
public interface EmailFactory {
    Email newEmail (Email email) throws MessagingException;

}
