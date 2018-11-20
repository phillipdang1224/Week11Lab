<%-- 
    Document   : resetnewpassword
    Created on : Nov 16, 2018, 4:00:18 PM
    Author     : 747787
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Enter a new Password</h1>
        <form method="post" action="reset">
            <input type="password" name="password">
            <input type="hidden" name="uuid" value="${uuid}">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
