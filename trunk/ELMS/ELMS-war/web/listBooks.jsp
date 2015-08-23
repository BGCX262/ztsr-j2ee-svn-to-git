<%
    if(request.getAttribute("fromServlet") == null)
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
%>
<%@page import="java.util.Collection,edu.uj.elms.beans.*"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="Cache-Control" content="no-cache">
        <title>Search results</title>
    </head>
    <body>
        
    <c:if test="${param['message'] != null}">
        <c:out value="${param['message']}" />
    </c:if>

    <h1>Books list</h1>
    
        <%
        Collection<Book> result = (Collection<Book>)request.getAttribute("result");
        %>
        <p>All: <%= result.size() %></p>
        <table border="1" cellspacing="0" cellpadding="5">
            <c:forEach var="book" items="${result}">
            <tr>
                <td><c:out value="${book.author}" /></td>
                <td><c:out value="${book.title}" /></td>
                <td><c:out value="${book.publisher}" /></td>
                <td><c:out value="${book.publishYear}" /></td>
                <td><c:out value="${book.hasAvailableCopies}" /></td>
                <td><a href="bookCopies?bookId=<c:out value="${book.bookId}" />">show copies</a></td>
            </tr>
            </c:forEach>
        </table>
    
    </body>
</html>
