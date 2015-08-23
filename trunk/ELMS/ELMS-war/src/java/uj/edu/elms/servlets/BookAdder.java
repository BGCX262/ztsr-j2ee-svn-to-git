/*
 * BookAdder.java
 *
 * Created on 20 maj 2007, 16:37
 */

package uj.edu.elms.servlets;

import edu.uj.elms.beans.Book;
import edu.uj.elms.beans.BookCopy;
import edu.uj.elms.session.LibrarianSessionLocal;
import java.io.*;
import javax.ejb.EJB;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Piotrek
 * @version
 */
public class BookAdder extends HttpServlet {
    
    @EJB
    private LibrarianSessionLocal librarianSessionBean;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (request.getCharacterEncoding() == null || !request.getCharacterEncoding().equals("UTF8"))
            request.setCharacterEncoding("UTF8");
        
        Book addedBook = new Book();
        if (request.getParameter("author") == "" || request.getParameter("title") == ""
                || request.getParameter("publisher") == "" || request.getParameter("publishYear") =="") {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "fill all fields");
            return;
        }
        
        addedBook.setAuthor(request.getParameter("author"));
        addedBook.setTitle(request.getParameter("title"));
        addedBook.setPublisher(request.getParameter("publisher"));
        addedBook.setPublishYear(Integer.parseInt(request.getParameter("publishYear")));
        
        BookCopy firstCopy = new BookCopy();
        
        try {
            librarianSessionBean.addBook(addedBook, firstCopy);
            
            request.setAttribute("message", "successfully added 1 book");
            
            response.sendRedirect("finder");
            
            //RequestDispatcher rd = request.getRequestDispatcher("finder");
            //rd.forward(request,response);
        } catch(Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
