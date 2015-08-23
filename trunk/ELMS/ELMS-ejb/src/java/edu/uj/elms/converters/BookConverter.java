/*
 * BookConverter.java
 *
 * Created on 20 maj 2007, 16:16
 */

package edu.uj.elms.converters;

import edu.uj.elms.beans.Book;
import edu.uj.elms.entities.BookEntity;

/**
 *
 * @author Piotrek
 */
public class BookConverter {
    
    /** Creates a new instance of BookConverter */
    public BookConverter() {
    }
    
    public Book fromEntity(BookEntity entity) {
        Book retval = new Book();
        retval.setAuthor(entity.getAuthor());
        retval.setTitle(entity.getTitle());
        retval.setPublishYear(entity.getPublishYear());
        retval.setPublisher(entity.getPublisher());
        retval.setBookId(entity.getId().intValue());
        return retval;
    }
    
    public BookEntity fromBean(Book bean) {
        BookEntity retval = new BookEntity();
        retval.setAuthor(bean.getAuthor());
        retval.setTitle(bean.getTitle());
        retval.setPublishYear(bean.getPublishYear());
        retval.setPublisher(bean.getPublisher());
        retval.setId((long)bean.getBookId());
        return retval;
    }
    
}
