<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache">
        <title>Reader</title>
    </head>
    <body>

    <h1>Reader</h1>
    
    <form action="reader" method="get">
        <input type="submit" name="action" value="Show borrowed" title="Show borrowed" />
        <br />
        <input type="submit" name="action" value="Show requested" title="Show requested" />
        <br />
        <input type="submit" name="action" value="Show reserved" title="Show reserved" />
        <br />
        Copy ids (comma separated): <input type="text" name="copyIds" /><br />
        <input type="submit" name="action" value="Request" title="Request" />
        <br />
        <input type="submit" name="action" value="Reserve" title="Reserve" />
        <br />
        <input type="submit" name="action" value="Cancel reservations" title="Cancel reservations" />
        <br /><br />
        <input type="submit" name="action" value="Logout" title="Logout" />
    </form>
    
    </body>
</html>
