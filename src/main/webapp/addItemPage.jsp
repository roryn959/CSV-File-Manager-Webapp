<%--
  User: Rory
  Date: 19/05/2021
  Time: 17:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        padding: 5px;
        margin-left: auto;
        margin-right: auto;
    }
</style>

<head>
    <jsp:include page="header.jsp"/>
    <title>
        Add New Item
    </title>
</head>

<div style="padding: 15px; text-align: center">
    <body>

    <h1>
        New Item
    </h1>

    <h3>
        Please note you may only input up to 7 thousand characters.
    </h3>

    <form action="/processAddItem.html" method="post">

        <table>

            <tr>
                <th>Type:</th>
                <th>Content:</th>
            </tr>

            <tr>
                <th>
                    <select name="newType">
                        <option value="text">Text</option>
                        <option value="link">Link</option>
                        <option value="image">Image</option>
                        <option value="list">List</option>
                    </select>
                </th>

                <th>
                    <input type="text" name="newValue" maxlength="7000" required>
                </th>
            </tr>

        </table>

        <input type="hidden" name="block" value="<%=request.getParameter("block")%>">
        <input type="hidden" name="list" value="<%=request.getAttribute("list")%>">
        <br>
        <input type="submit" value="Submit">

    </form>

    </body>
</div>

<jsp:include page="footer.jsp"/>
</html>
