package infrastructure.Exceptions;

/**
 * CREATED BY Janus @ 2020-12-01 - 12:43
 **/
public class UnexpectedDbError extends RuntimeException{
    public UnexpectedDbError() {
    }

    public UnexpectedDbError(String message) {
        super(message);
    }

    public UnexpectedDbError(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedDbError(Throwable cause) {
        super(cause);
    }
}
