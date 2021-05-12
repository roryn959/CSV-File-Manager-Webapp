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
                <input type="Submit" value="Create a new list"/>
            </form>

            <form method="post" action="/search.html">
                <input type="Submit" value="Display an existing list"/>
            </form>
        </body>
    </div>

    <jsp:include page="footer.jsp"/>
</html>
