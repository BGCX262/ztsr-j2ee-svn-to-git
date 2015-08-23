<%
    if(request.getAttribute("fromServlet") == null)
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    String title="Show book copies";
    if (request.getAttribute("showTitle") != null)
        title = (String)request.getAttribute("showTitle");
%>
<%@page import="java.util.Collection,edu.uj.elms.beans.*"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache">
        <title><%= title %></title>
    </head>
    <body>

    <h1><%= title %></h1>
    
    <p>All: <%= ((Collection)request.getAttribute("result")).size() %></p>
    <%
        Collection<ReservationDetails> result = (Collection<ReservationDetails>)request.getAttribute("result");
        for(ReservationDetails rd : result) {
            out.print(rd.toString());
            out.println("<br />");
        }
    %>
    </body>
</html>
