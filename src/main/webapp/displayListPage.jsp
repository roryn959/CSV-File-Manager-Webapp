<%@ page import="rory.model.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="rory.model.Block" %><%--
  Created by IntelliJ IDEA.
  User: Rory
  Date: 13/05/2021
  Time: 17:49
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

        <form action="/displayList.html" method="post">
            <label for="filter">Filter:</label>
            <select name="filter" id="filter">
                <option value="none">None</option>
                <option value="text">Text</option>
                <option value="link">Links</option>
                <option value="image">Images</option>
                <option value="list">Lists</option>
            </select>

            <input type="hidden" name="list" value="<%=request.getParameter("list")%>">
            <button type="submit">Refresh</button>
        </form>

        <table>
            <%ArrayList<Block> blocks = (ArrayList<Block>) request.getAttribute("blocks");%>
            <%int counter = 1;%>
            <%for (Block block : blocks){%>
                <tr>
                    <th>Row <%=counter%></th>
                    <%for (Item item : block.getItems()){%>
                        <%if (item.getType().equals("text")){%>
                            <td>
                                <%=item.getValue()%>
                            </td>
                        <%} else if (item.getType().equals("link")) {%>
                            <td>
                                <a href=<%=item.getValue()%>>
                                    <%=item.getValue()%>
                                </a>
                            </td>
                        <%} else if (item.getType().equals("image")) {%>
                            <td>
                                <img src=<%=item.getValue()%>>
                            </td>
                        <%} else if (item.getType().equals("list")) {%>
                            <td>
                                <form action="/displayList.html" method="post">
                                    <input type="hidden" name="list" value="<%=item.getValue()%>">
                                    <input type="submit" value="<%=item.getValue()%>"/>
                                </form>
                            </td>
                        <%}%>
                    <%}%>
                <%counter++;%>
                </tr>
            <%}%>
        </table>

    </body>
</div>

<jsp:include page="footer.jsp"/>
</html>