/*
 * ReaderLogin.java
 *
 * Created on 2 czerwiec 2007, 22:04
 */

package uj.edu.elms.servlets;

import edu.uj.elms.beans.LoginData;
import edu.uj.elms.exceptions.LoginException;
import edu.uj.elms.session.ReaderSessionRemote;
import java.io.*;
import javax.naming.InitialContext;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Piotrek
 * @version
 */
public class ReaderLogin extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (request.getCharacterEncoding() == null || !request.getCharacterEncoding().equals("UTF8"))
            request.setCharacterEncoding("UTF8");
        
        int readerId = Integer.parseInt((String)request.getParameter("login"));
        String password = (String)request.getParameter("password");
        
        HttpSession session = request.getSession();
        ReaderSessionRemote readerSessionBean=null;
        
        try {
            InitialContext ctx = new InitialContext();
            readerSessionBean = (ReaderSessionRemote)ctx.lookup("ReaderSession");
            session.setAttribute("readerSession", readerSessionBean);
        } catch(Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
        
        try {
            LoginData ld = new LoginData(readerId, password);
            readerSessionBean.logIn(ld);
            response.sendRedirect("reader.jsp");
        } catch(LoginException e) {
            response.sendRedirect("login.jsp?error="+e.getLocalizedMessage());
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
