<%
    if(request.getAttribute("fromServlet") == null)
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.Collection,edu.uj.elms.beans.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache">
        <title>Readers</title>
    </head>
    <body>

    <h1>Readers</h1>
    
    <p>All: <%= ((Collection)request.getAttribute("result")).size() %></p>
    <ul>
        <c:forEach items="${requestScope['result']}" var="reader">
            <li>
                <c:out value="${reader.id}" /> <c:out value="${reader.fullName}" />
                '<c:out value="${reader.password}" />'
            </li>
        </c:forEach>
    </ul>
    
    </body>
</html>
