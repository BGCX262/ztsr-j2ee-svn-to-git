/*
 * Main.java
 *
 * Created on 19 maj 2007, 00:12
 */

package testelmsclient;

import java.util.Collection;
import javax.xml.ws.WebServiceRef;
import webservices.Book;
import webservices.ReservationDetails;
import webservices.SearchPattern;
import webservices.SimpleSessionBean;
import webservices.SimpleSessionBeanService;

/**
 *
 * @author Piotrek
 */
public class Main {
    
    @WebServiceRef(wsdlLocation="http://localhost:3316/SimpleSessionBeanService/SimpleSessionBean?WSDL")
    private static SimpleSessionBeanService service;
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleSessionBean simpleSession = service.getSimpleSessionBeanPort();
        SearchPattern pattern = new SearchPattern();
        Collection<Book> result = simpleSession.findBooks(pattern);
        System.out.println("dostępne książki:");
        for(Book book : result) {
            System.out.printf("%s - %s\n", book.getAuthor(), book.getTitle());
        }
        System.out.printf("wyników: %d\n", result.size());
    }
    
}
