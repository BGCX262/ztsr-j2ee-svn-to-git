<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache">
        <title>Add Book Copy</title>
    </head>
    <body>

    <h1>Add Book Copy</h1>
    
    <form action="bookCopyAdder" method="post">
        <label for="bookId">Book id:</label><input type="text" id="bookId" name="bookId" /><br />
        <label for="state">Copy state:</label><input type="text" id="state" name="state" />
        <input type="submit" />
    </form>
    
    </body>
</html>
