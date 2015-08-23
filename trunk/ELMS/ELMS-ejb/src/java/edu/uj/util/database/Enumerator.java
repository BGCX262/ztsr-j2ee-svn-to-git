/*
 * Enumerator.java
 *
 * Created on 20 marzec 2007, 12:48
 *
 * Copyright (c) 2007 Piotr Kubowicz
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package edu.uj.util.database;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Helps create enumerations like "name='Peter' AND id=14 AND code=45"
 * or "one,two,three".
 *
 * <p>Example of use</p>
 * <pre>Enumerator e = new Enumerator(" AND ",true); // quote values
 *e.add("Peter",14).add("code",45);
 *return e.toString();
 *
 *Enumerator e2 = new Enumerator(",",false); // do not quote
 *e2.append("one").append("two").append("three");
 *return e2.toString();
 *
 * @author Piotrek
 */
public class Enumerator {
    
    /** entries like "name LIKE '%Peter%'", "userId", "date='2007-03-20'" */
    private Collection<String> entries;
    
    /** separates entries, eg. " AND " or ", " */
    private String separator;
    
    /** links key and value, eg. "=" or " LIKE " */
    private String linker;
    
    /** if values of types String and Date should be surrounded with ' sign */
    private boolean quoteStrings;
    
    /**
     * Creates a new instance of Enumerator.
     * @param separator separates entries, eg. " AND " or ", "
     * @param quoteStrings if values of types String and Date should be surrounded with ' sign
     * @param linker links key and value, eg. "=" or " LIKE "
     */
    public Enumerator(String separator, boolean quoteStrings, String linker) {
        entries = new ArrayList<String>();
        
        this.separator = separator;
        this.quoteStrings = quoteStrings;
        this.linker = linker;
    }
    
    /**
     * Creates a new instance of Enumerator.
     * Default values: linker "=".
     * @param separator separates entries, eg. " AND " or ", "
     * @param quoteStrings if values of types String and Date should be surrounded with ' sign
     */
    public Enumerator(String separator, boolean quoteStrings) {
        this(separator, quoteStrings, "=");
    }
    
    /**
     * Creates a new instance of Enumerator.
     * Default values: strings quoted, linker "=".
     * @param separator separates entries, eg. " AND " or ", "
     */
    public Enumerator(String separator) {
        this(separator, true, "=");
    }
    
    /** Creates a new instance of Enumerator.
     * Default values: separator ", ", strings not quoted, linker "=".
     */
    public Enumerator() {
        this(", ", false, "=");
    }
    
    /** returns number of entries */
    public int size() {
        return entries.size();
    }
    
    /** removes all entries */
    public void clear() {
        entries.clear();
    }
    
    /** checks if there are any entries */
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /** returns string representing the enumeration eg. "a AND b AND c" */
    public String toString() {
        StringBuffer retval = new StringBuffer();
        for(String entry : entries) {
            if (retval.length() != 0) retval.append(separator);
            retval.append(entry);
        }
        
        return retval.toString();
    }
    
    /** adds key and value linked by tempLinker, not default linker. */
    public Enumerator add(String key, String value, String tempLinker) {
        if (key.equals("") && value.equals(""))
            return this;
        if (quoteStrings) value = "'" + value + "'";
        entries.add(key+tempLinker+value);
        return this;
    }
    
    /** adds key and value linked by tempLinker, not default linker */
    public Enumerator add(String key, int value, String tempLinker) {
        return addNotQuote(key, Integer.toString(value), tempLinker);
    }
    
    /** adds key and value linked by tempLinker, not default linker and 
      without string quoting */
    public Enumerator addNotQuote(String key, String value, String tempLinker) {
        entries.add(key+tempLinker+value);
        return this;
    }
    
    /** adds key and value linked by default linker */
    public Enumerator add(String key, String value) {
        return add(key,value,this.linker);
    }
    
    /** adds key and value linked by default linker */
    public Enumerator add(String key, int value) {
        return add(key,value,this.linker);
    }
    
    /** adds key and value linked by default linker, without string quoting */
    public Enumerator addNotQuote(String key, String value) {
        return addNotQuote(key,value,this.linker);
    }
    
    /** adds only key, may be quoted. Empty strings are not added. */
    public Enumerator add(String key) {
        if (key.equals(""))
            return this;
        if (quoteStrings) key = "'" + key + "'";
        entries.add(key);
        return this;
    }
    
    /** adds only key */
    public Enumerator add(int key) {
        return addNotQuote(Integer.toString(key));
    }
    
    /** adds only key, without quoting. Emty strings are not added. */
    public Enumerator addNotQuote(String key) {
        if (key.equals(""))
            return this;
        entries.add(key);
        return this;
    }
    
}
