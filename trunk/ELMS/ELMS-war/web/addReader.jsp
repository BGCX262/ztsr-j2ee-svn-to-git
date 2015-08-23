<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adding user</title>
    </head>
    <body>

    <h1>Adding user</h1>
    
    <form action="readerAdder" method="get">
        <table>
            <tr>
                <td>Full name:</td>
                <td><input name="fullName" type="text" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input name="password" type="text" /></td>
            </tr>
        </table>
        <input type="submit" />
    </form>
    
    </body>
</html>
