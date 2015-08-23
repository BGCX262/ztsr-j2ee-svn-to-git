<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reader login</title>
    </head>
    <body>

    <h1>Reader login</h1>
    
    <c:if test="${param['error'] != null}">
        <p><strong><c:out value="${param['error']}" /></strong>
    </c:if>
    
    <form action="readerLogin" method="post">
        <table>
            <tr>
                <td>Login:</td>
                <td><input name="login" type="text" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input name="password" type="password" /></td>
            </tr>
        </table>
        <input type="submit" />
    </form>
    
    </body>
</html>
