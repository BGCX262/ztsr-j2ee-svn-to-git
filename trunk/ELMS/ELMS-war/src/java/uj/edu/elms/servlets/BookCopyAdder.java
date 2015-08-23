/*
 * BookCopyAdder.java
 *
 * Created on 4 czerwiec 2007, 00:11
 */

package uj.edu.elms.servlets;

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
public class BookCopyAdder extends HttpServlet {

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
        
        if (request.getParameter("bookId") == "" || request.getParameter("state") == "") {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "fill all fields");
            return;
        }
        
        int bookId = Integer.parseInt((String)request.getParameter("bookId"));
        int state = Integer.parseInt((String)request.getParameter("state"));
        
        BookCopy addedCopy = new BookCopy(state,0);
        
        try {
            librarianSessionBean.addBookCopy(bookId, addedCopy);
            
            response.sendRedirect("bookCopies?bookId="+Integer.toString(bookId));
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
