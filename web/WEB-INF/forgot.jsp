<%-- 
    Document   : forgot
    Created on : Nov 16, 2018, 11:02:06 AM
    Author     : 747787
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        <p>Please enter your email address to retrieve your password.</p>
        <form method="post" action="forgot">
        Email Address: <input type="email" name="email">
        <br>
        <input type="submit" value="Submit">
        </form>
        ${sent}
    </body>
</html>
