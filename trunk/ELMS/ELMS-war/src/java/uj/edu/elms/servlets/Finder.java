/*
 * Finder.java
 *
 * Created on 18 maj 2007, 17:54
 */

package uj.edu.elms.servlets;

import edu.uj.elms.beans.Book;
import edu.uj.elms.beans.SearchPattern;
import edu.uj.elms.session.SimpleSessionLocal;
import java.io.*;
import java.util.Collection;
import javax.ejb.EJB;

import javax.servlet.*;
import javax.servlet.http.*;
import uj.edu.util.Converter;

/**
 *
 * @author Piotrek
 * @version
 */
public class Finder extends HttpServlet {

    @EJB
    private SimpleSessionLocal simpleSessionBean;
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (request.getCharacterEncoding() == null || !request.getCharacterEncoding().equals("UTF8"))
            request.setCharacterEncoding("UTF8");
        
        SearchPattern pattern = new SearchPattern();
        if (request.getParameter("author") != null) pattern.setAuthor((String)request.getParameter("author"));
        if (request.getParameter("title") != null) pattern.setTitle((String)request.getParameter("title"));
        if (request.getParameter("publisher") != null) pattern.setPublisher((String)request.getParameter("publisher"));
        try {
            if (request.getParameter("publishYear") != null)
                pattern.setPublishYear(Integer.parseInt((String)request.getParameter("publishYear")));
        } catch(NumberFormatException e) {}
        
        Collection<Book> result = simpleSessionBean.findBooks(pattern);
        
        request.setAttribute("result", result);
        request.setAttribute("fromServlet", true);
        
        //response.sendRedirect("listBooks.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("listBooks.jsp");
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
