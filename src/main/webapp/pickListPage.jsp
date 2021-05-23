<%--
  User: Rory
  Date: 12/05/2021
  Time: 19:10
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

            <%String nextPage = (String) request.getAttribute("nextPage");%>

            <form action="<%=nextPage%>" method="post">

                <%String[] dataFileNames = (String[]) request.getAttribute("dataFileNames");%>

                <%if (dataFileNames.length==0){%>
                    <h3>Didn't find any lists!</h3>
                <%} else {%>

                <label for="list">Detected <%=dataFileNames.length%> lists. Please pick one:</label>

                <select name="list" id="list">
                    <%for (String name : dataFileNames){%>
                        <option value=<%=name%>><%=name%></option>
                    <%}%>
                </select>

                <input type="submit" value="Select">
            </form>
            <%}%>
        </body>
    </div>

    <jsp:include page="footer.jsp"/>
</html>
