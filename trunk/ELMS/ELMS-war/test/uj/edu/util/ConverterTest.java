/*
 * ConverterTest.java
 * JUnit based test
 *
 * Created on 21 maj 2007, 23:56
 */

package uj.edu.util;

import java.util.ResourceBundle;
import junit.framework.*;

/**
 * tests Converter class.
 *
 *<p><strong>Important</strong>: This file has to have UTF-8 encoding!</p>
 * @author Piotrek
 */
public class ConverterTest extends TestCase {
    
    public ConverterTest(String testName) {
        super(testName);
    }

    /**
     * Test of utf8ToUnicode method, of class uj.edu.util.Converter.
     */
    public void testUtf8ToUnicode() {
        System.out.println("utf8ToUnicode");
        
        String utf8 = "Tytuł";
        Converter instance = new Converter();
        
        String expResult = "Tytu\u0142";
        String result = instance.utf8ToUnicode(utf8);
        assertEquals(expResult, result);
    }

    /**
     * Test of unicodeToUtf8 method, of class uj.edu.util.Converter.
     */
    public void testUnicodeToUtf8() {
        System.out.println("unicodeToUtf8");
        
        String unicode = "Tytu\u0142";
        Converter instance = new Converter();
        
        String expResult = "Tytuł";
        String result = instance.unicodeToUtf8(unicode);
        assertEquals(expResult, result);
    }
    
}
