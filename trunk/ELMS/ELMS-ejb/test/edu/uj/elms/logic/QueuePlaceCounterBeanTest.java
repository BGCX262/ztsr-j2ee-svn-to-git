/*
 * QueuePlaceCounterBeanTest.java
 * JUnit based test
 *
 * Created on 20 maj 2007, 12:15
 */

package edu.uj.elms.logic;

import java.util.ArrayList;
import junit.framework.*;
import java.util.Collection;

/**
 *
 * @author Piotrek
 */
public class QueuePlaceCounterBeanTest extends TestCase {
    
    public QueuePlaceCounterBeanTest(String testName) {
        super(testName);
    }
    
    private int[] createPair(int first, int last) {
        int[] retval = {first,last};
        return retval;
    }

    /**
     * Test of count method, of class edu.uj.elms.logic.QueuePlaceCounterBean.
     */
    public void testCount() {
        System.out.println("count");
        
        int featuredElement = 10;
        Collection<int[]> pairs = new ArrayList<int[]>();
        QueuePlaceCounterBean instance = new QueuePlaceCounterBean();
        
        int expResult = 0;
        int result = instance.count(featuredElement, pairs);
        assertEquals(expResult, result);
        
        int[] pair = {1,22};
        pairs.add(pair);
        expResult = 1;
        result = instance.count(1, pairs);
        assertEquals(expResult, result);
        
        expResult = 1;
        pairs.clear();
        pairs.add(createPair(3,4));
        pairs.add(createPair(6,7));
        pairs.add(createPair(1,2));
        pairs.add(createPair(2,3));
        pairs.add(createPair(8,22));
        pairs.add(createPair(5,6));
        pairs.add(createPair(7,8));
        pairs.add(createPair(4,5));
        result = instance.count(8, pairs);
        assertEquals(expResult, result);
        
        expResult = 8;
        result = instance.count(1, pairs);
        assertEquals(expResult, result);
        
        
    }
    
}
