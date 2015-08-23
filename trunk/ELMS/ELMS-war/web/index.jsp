<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache" />
        <title>Welcome to ELMS</title>
    </head>
    <body>

    <h1>Welcome</h1>
    
    <%--
    This example uses JSTL, uncomment the taglib directive above.
    To test, display the page like this: index.jsp?sayHello=true&name=Murphy
    --%>
    <c:if test="${param.sayHello}">
        <!-- Let's welcome the user ${param.name} -->
        Hello ${param.name}!
    </c:if>
    
    <ol>
        <li><a href="findBooks.jsp">find books</a></li>
        <li><a href="addBook.jsp">add book</a></li>
        <li><a href="addBookCopy.jsp">add book copy</a></li>
        <li><a href="addReader.jsp">add reader</a></li>
        <li><a href="login.jsp">reader login</a></li>
        <li><a href="readers">all readers</a></li>
    </ol>
    
    </body>
</html>
