<%-- 
    Document   : note
    Created on : Feb 25, 2020, 3:35:10 PM
    Author     : lpeters
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>DB Demo</h1>
        <form method="POST">
            <input type="text" name="fldNote" />
            <input type="submit" name="btnSubmit" value="Post Note" />
        </form>
        <ul>
            <c:forEach var="note" items="${notes}">
                <li>${note}</li>
            </c:forEach>
        </ul>
    </body>
</html>
