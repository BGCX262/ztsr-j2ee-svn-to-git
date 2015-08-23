/*
 * LoginData.java
 *
 * Created on 18 maj 2007, 15:28
 */

package edu.uj.elms.beans;

import java.io.Serializable;

/**
 *
 * @author Piotrek
 */
public class LoginData implements Serializable {
    
    private int login;
    
    private String password;
    
    /** Creates a new instance of LoginData */
    public LoginData(int login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }
    
    public LoginData() {
        this(-1,"");
    }

    public int getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    
    public void setLogin(int login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private static final long serialVersionUID = 425117185527691826L;
    
}
