<%--
  Created by IntelliJ IDEA.
  User: Rory
  Date: 18/05/2021
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
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
        Edit
    </title>
</head>

<div style="padding: 15px; text-align: center">
    <body>

    <h1>
        <%=request.getParameter("list")%>
    </h1>

    <form action="/processEdit.html">

        <table>
            <tr>
                <th>

                </th>

                <th>
                    Original content:
                </th>

                <th>
                    New content:
                </th>

            </tr>

            <tr>
                <th>
                    Item Type
                </th>

                <th>
                    <%=request.getParameter("itemType")%>
                </th>

                <th>

                    <select name="newType">
                        <option value="text">Text</option>
                        <option value="link">Link</option>
                        <option value="image">Image</option>
                        <option value="list">List</option>
                    </select>

                </th>
            </tr>

            <tr>
                <th>
                    Item contents
                </th>

                <th>
                    <%=request.getParameter("itemValue")%>
                </th>

                <th>
                    <input type="text" name="newValue">
                </th>
            </tr>

        </table>

        <input type="hidden" name="item" value="<%=request.getParameter("item")%>">
        <input type="hidden" name="list" value="<%=request.getAttribute("list")%>">
        <br>
        <input type="submit" value="Submit">

    </form>

    </body>
</div>

<jsp:include page="footer.jsp"/>
</html>
