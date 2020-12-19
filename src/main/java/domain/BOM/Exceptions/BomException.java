package domain.BOM.Exceptions;

/**
 * CREATED BY mathias @ 19-12-2020 - 16:19
 **/
public class BomException extends Exception {
    public BomException(String error){super("BOM ERROR: " + error);}


}
