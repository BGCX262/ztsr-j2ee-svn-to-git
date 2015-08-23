/*
 * BookCopies.java
 *
 * Created on 25 maj 2007, 17:56
 */

package uj.edu.elms.servlets;

import edu.uj.elms.beans.ReservationDetails;
import edu.uj.elms.session.SimpleSessionLocal;
import java.io.*;
import java.util.Collection;
import javax.ejb.EJB;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Piotrek
 * @version
 */
public class BookCopies extends HttpServlet {
    
    @EJB
    private SimpleSessionLocal simpleSessionBean;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        
        Collection<ReservationDetails> result = simpleSessionBean.getBookCopies(bookId);
        request.setAttribute("result",result);
        request.setAttribute("fromServlet", true);
        
        RequestDispatcher rd = request.getRequestDispatcher("showReservationDetails.jsp");
        rd.forward(request,response);
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
