package Repoistory.DB;

/**
 * CREATED BY mathi @ 23-11-2020 - 15:51
 **/
public class DBError  extends Exception{
    public DBError(String error){super("DB ERROR: " + error);}

}
