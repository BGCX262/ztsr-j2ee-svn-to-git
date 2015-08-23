/*
 * Converter.java
 *
 * Created on 21 maj 2007, 23:43
 */

package uj.edu.util;

/**
 * Changes text encoding
 * @author Piotrek
 */
public class Converter {
    
    /** Creates a new instance of Converter */
    public Converter() {
    }
    
    public String utf8ToUnicode(String utf8) {
        try {
            return new String(utf8.getBytes(),"UTF8");
        } catch(Exception e) {
            e.printStackTrace();
            return utf8;
        }
    }
    
    public String unicodeToUtf8(String unicode) {
        try {
            return new String(unicode.getBytes("UTF8"));
        } catch(Exception e) {
            e.printStackTrace();
            return unicode;
        }
    }
    
}
