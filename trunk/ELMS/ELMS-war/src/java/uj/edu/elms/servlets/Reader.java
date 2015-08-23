/*
 * Reader.java
 *
 * Created on 25 maj 2007, 19:54
 */

package uj.edu.elms.servlets;

import edu.uj.elms.beans.BookCopy;
import edu.uj.elms.beans.LoginData;
import edu.uj.elms.beans.ReservationDetails;
import edu.uj.elms.exceptions.LibraryException;
import edu.uj.elms.session.ReaderSessionLocal;
import edu.uj.elms.session.ReaderSessionRemote;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Piotrek
 * @version
 */
public class Reader extends HttpServlet {
    /*
    @EJB
    private ReaderSessionLocal readerSessionBean;
     */
    
    private enum Action{REQUEST,RESERVE,CANCEL_RESERV,SHOW_BORROWED,SHOW_REQ,SHOW_RESERV,LOGOUT,NONE};
    
    private Map<String,Action> actionMap;
    
    public void init() throws ServletException {
        actionMap = new HashMap<String,Action>();
        actionMap.put("Cancel reservations",Action.CANCEL_RESERV);
        actionMap.put("Show borrowed", Action.SHOW_BORROWED);
        actionMap.put("Show requested", Action.SHOW_REQ);
        actionMap.put("Show reserved", Action.SHOW_RESERV);
        actionMap.put("Request", Action.REQUEST);
        actionMap.put("Reserve", Action.RESERVE);
        actionMap.put("Logout", Action.LOGOUT);
    }
    
    private Action getAction(HttpServletRequest request) {
        Action retval=Action.NONE;
        String actionStr=request.getParameter("action");
        if (actionMap.containsKey(actionStr))
            retval=actionMap.get(actionStr);
        return retval;
    }
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if (request.getCharacterEncoding() == null || !request.getCharacterEncoding().equals("UTF8"))
            request.setCharacterEncoding("UTF8");
        
        int copyIds[] = {};
        
        if (request.getParameter("copyIds") != null) {
            String[] copyIdStr = ((String)request.getParameter("copyIds")).split("\\s*,\\s*");
            List<Integer> idsList = new ArrayList<Integer>();
            for(String s : copyIdStr) {
                try {
                    int copyId = Integer.parseInt(s);
                    idsList.add(copyId);
                } catch(NumberFormatException e) {} // ok
            }
            
            copyIds = new int[idsList.size()];
            int i=0;
            for(int copyId : idsList) {
                copyIds[i++] = copyId;
            }
        }
        
        
        HttpSession session = request.getSession();
        if (session.getAttribute("readerSession") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        ReaderSessionRemote readerSessionBean=(ReaderSessionRemote)session.getAttribute("readerSession");
         
        Action action=getAction(request);
        
        try {
            if (action == Action.CANCEL_RESERV) {
                readerSessionBean.cancelReservations(copyIds);
                
                action = Action.SHOW_RESERV;
            }
            if (action == Action.REQUEST) {
                readerSessionBean.requestBooks(copyIds);
                
                action = Action.SHOW_REQ;
            }
            if (action == Action.RESERVE) {
                readerSessionBean.reserveBooks(copyIds);
                
                action = Action.SHOW_RESERV;
            }
            if (action == Action.SHOW_BORROWED) {
                
                Collection<BookCopy> result = readerSessionBean.getBorrowedBooks();
                request.setAttribute("result", result);
                request.setAttribute("fromServlet", true);
                request.setAttribute("showTitle", "Borrowed books");
                
                RequestDispatcher rd = request.getRequestDispatcher("showCopies.jsp");
                rd.forward(request,response);
            }
            if (action == Action.SHOW_REQ) {
                
                Collection<BookCopy> result = readerSessionBean.getRequestedBooks();
                request.setAttribute("result", result);
                request.setAttribute("fromServlet", true);
                request.setAttribute("showTitle", "Requested books");
                
                RequestDispatcher rd = request.getRequestDispatcher("showCopies.jsp");
                rd.forward(request,response);
            }
            if (action == Action.SHOW_RESERV) {
                
                Collection<ReservationDetails> result = readerSessionBean.getReservedBooks();
                request.setAttribute("result", result);
                request.setAttribute("fromServlet", true);
                request.setAttribute("showTitle", "Reserved books");
                
                RequestDispatcher rd = request.getRequestDispatcher("showReservationDetails.jsp");
                rd.forward(request,response);
            }
            if (action == Action.LOGOUT) {
                readerSessionBean.logOut();
                session.invalidate();
                response.sendRedirect(".");
            }
        } catch(LibraryException e) {
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
