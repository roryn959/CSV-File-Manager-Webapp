<%--
  Created by IntelliJ IDEA.
  User: Rory
  Date: 13/05/2021
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <jsp:include page="header.jsp"/>
    <title>
        Search
    </title>
</head>

<div style="padding: 15px">
    <body>
        <h1>
            <%=request.getAttribute("chosenFile")%>
        </h1>
    </body>
</div>

<jsp:include page="footer.jsp"/>
</html>