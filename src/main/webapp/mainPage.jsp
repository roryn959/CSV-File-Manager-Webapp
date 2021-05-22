<%--
  Created by IntelliJ IDEA.
  User: Rory
  Date: 12/05/2021
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="header.jsp"/>
        <title>
            List Maker
        </title>
    </head>

    <div style="padding: 15px">
        <body>

            <form method="post" action="/newList.html">
                <input type="submit" value="Create a new list"/>
            </form>

            <form method="post" action="/editListPicker.html">
                <input type="submit" value="Edit a list"/>
            </form>

            <form method="post" action="/displayListPicker.html">
                <input type="submit" value="Display an existing list"/>
            </form>

            <form method="post" action="/processSearch.html">
                <input type="submit" value="Search for an item in a list"/>
            </form>
        </body>
    </div>
</html>
