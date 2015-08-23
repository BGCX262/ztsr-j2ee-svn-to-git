/*
 * Reader.java
 *
 * Created on 26 maj 2007, 14:54
 */

package edu.uj.elms.beans;

import java.io.Serializable;

/**
 *
 * @author Piotrek
 */
public class Reader implements Serializable {
    
    private int id;
    
    private String fullName;
    
    private String password;
    
    /** Creates a new instance of Reader */
    public Reader() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return String.format("reader no %d - %s", id, fullName);
    }
    
    private static final long serialVersionUID = 8578517790923723581L;
    
}
