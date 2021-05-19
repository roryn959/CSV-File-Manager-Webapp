<%--
  Created by IntelliJ IDEA.
  User: Rory
  Date: 12/05/2021
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="header.jsp"/>
        <title>New List</title>
    </head>

    <body>
        <div style="padding: 15px">
            <form action="/processNewList.html">
                <label for="newListName">Name of new list:</label><br>
                <input type="text" id="newListName" name="list" required>
                <input type="submit" value="Create">
            </form>
        </div>
    </body>

    <jsp:include page="footer.jsp"/>
</html>
