<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="uj.edu.elms.messages" />
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Adding book</title>
    </head>
    <body>

    <h1>Adding book</h1>
    
    <form action="bookAdder" method="post">
            <table>
                <tr>
                    <td>Author:</td>
                    <td><input type="text" name="author" /></td>
                </tr>
                <tr>
                    <td>Title:</td><td><input type="text" name="title" /></td>
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
