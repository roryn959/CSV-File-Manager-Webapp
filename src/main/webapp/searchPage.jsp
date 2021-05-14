<%--
  Created by IntelliJ IDEA.
  User: Rory
  Date: 12/05/2021
  Time: 19:10
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

            <form action="/displaySearchResults.html">

                <%String[] dataFileNames = (String[]) request.getAttribute("dataFileNames");%>
                <label for="list">Detected <%=dataFileNames.length%> lists. Please pick one:</label>

                <select name="list" id="list">
                    <%for (String name : dataFileNames){%>
                        <option value=<%=name%>><%=name%></option>
                    <%}%>
                </select>

                <input type="submit" value="Select">
            </form>

        </body>
    </div>

    <jsp:include page="footer.jsp"/>
</html>
