/*
 * ReaderAdder.java
 *
 * Created on 26 maj 2007, 14:38
 */

package uj.edu.elms.servlets;

import edu.uj.elms.beans.LoginData;
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
public class ReaderAdder extends HttpServlet {
    
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
        
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        
        if (password == "" || fullName == "") {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "fill all fields");
            return;
        }
        
        try {
            edu.uj.elms.beans.Reader reader = new edu.uj.elms.beans.Reader();
            reader.setFullName(fullName);
            reader.setPassword(password);
            
            librarianSessionBean.addReader(reader);
            
            response.sendRedirect("readers");
        } catch(Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
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
