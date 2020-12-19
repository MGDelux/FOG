package infrastructure.DatabaseUser.Execptions;

import java.sql.SQLException;

/**
 * CREATED BY mathias @ 19-12-2020 - 20:11
 **/
public class CustomerExecption  extends SQLException {
    public CustomerExecption(String message) {
        super(message);
    }

}
