package api;

/**
 * CREATED BY mathias @ 03-12-2020 - 12:03
 **/
public class Utils {
    /** we use this to prevent HTML injection
     * @param string to be stripped
     * @return no HTML TAGS
     */
    public static String removeHTML(String string){
        return string.replaceAll("\\<.*?\\>", "");
    }
}
