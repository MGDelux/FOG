package Repoistory.Email;

import domain.Email.Email;

/**
 * CREATED BY mathias @ 02-12-2020 - 16:15
 **/
public interface EmailRepo extends EmailFactory {
    Iterable<Email> getAllMailInfo();
}
