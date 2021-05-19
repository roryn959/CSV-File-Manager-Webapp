<%@ page import="rory.model.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="rory.model.Block" %>
<%--
  Created by IntelliJ IDEA.
  User: Rory
  Date: 18/05/2021
  Time: 14:07
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
        Search
    </title>
</head>

<div style="padding: 15px; text-align: center;">
    <body>

    <h1>
        <%=request.getParameter("list")%>
    </h1>

    <table>
        <%ArrayList<Block> blocks = (ArrayList<Block>) request.getAttribute("blocks");%>
        <%for (Block block : blocks){%>
        <tr>

            <%for (Item item : block.getItems()){%>

            <th>
            <form action="/deleteItem.html" method="post">
                <input type="hidden" name="list" value="<%=request.getParameter("list")%>">
                <input type="hidden" name="item" value="<%=item.toString()%>">
                <button type="submit">Delete</button>
            </form>
            </th>

            <th>
            <form action="/editItem.html" method="post">
                <input type="hidden" name="list" value="<%=request.getParameter("list")%>">
                <input type="hidden" name="itemValue" value="<%=item.getValue()%>">
                <input type="hidden" name="itemType" value="<%=item.getType()%>">
                <input type="hidden" name="item" value="<%=item.toString()%>">
                <button type="submit">Edit</button>
            </form>
            </th>

            <%if (item.getType().equals("text")){%>
            <th>
                <%=item.getValue()%>
            </th>
            <%} else if (item.getType().equals("link")) {%>
            <th>
                <a href=<%=item.getValue()%>>
                    <%=item.getValue()%>
                </a>
            </th>
            <%} else if (item.getType().equals("image")) {%>
            <th>
                <img src=<%=item.getValue()%>>
            </th>
            <%} else if (item.getType().equals("list")) {%>
            <th>
                <form action="/displayList.html" method="post">
                    <input type="hidden" name="list" value="<%=item.getValue()%>">
                    <input type="submit" value="<%=item.getValue()%>"/>
                </form>
            </th>
            <%}%>
            <%}%>

            <th>
                <form action="/addItem.html" method="post">
                    <input type="hidden" name="list" value="<%=request.getParameter("list")%>">
                    <input type="hidden" name="block" value="<%=block.toString()%>">
                    <input type="submit" value="+"/>
                </form>
            </th>

        </tr>
        <%}%>

        <tr>
            <th>
                <form action="/processNewRow.html" method="post">
                    <input type="hidden" name="list" value="<%=request.getParameter("list")%>">
                    <input type="submit" value="+"/>
                </form>
            </th>
        </tr>
    </table>

    </body>
</div>

<jsp:include page="footer.jsp"/>
</html>