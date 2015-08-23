<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache" />
        <title>Find books</title>
    </head>
    <body>
    <%-- <fmt:setLocale value="pl_PL" /> --%>
    <fmt:setBundle basename="uj.edu.elms.messages" />
    <h1><fmt:message key="Find_books" /></h1>
        <form action="finder" method="get">
            <table>
                <tr>
                    <td><fmt:message key="Author" />:</td>
                    <td><input type="text" name="author" /></td>
                </tr>
                <tr>
                    <td><fmt:message key="Title" />:</td><td><input type="text" name="title" /></td>
                </tr>
                <tr>
                    <td>Publisher:</td><td><input type="text" name="publisher" /></td>
                </tr>
                <tr>
                    <td>Publish year:</td>
                    <td><input type="text" name="publishYear" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
