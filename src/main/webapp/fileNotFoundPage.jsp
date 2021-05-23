<%--
  User: Rory
  Date: 19/05/2021
  Time: 18:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<div style="padding: 15px; text-align: center">
    <head>
        <jsp:include page="header.jsp"/>
        <title>File not Found</title>
        <h2>File not found!</h2>
    </head>

    <body>
        Could not find the file "<%=request.getParameter("list")%>"
    </body>

    <jsp:include page="footer.jsp"/>
</div>

</html>
