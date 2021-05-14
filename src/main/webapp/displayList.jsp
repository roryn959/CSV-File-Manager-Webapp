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
            <%=request.getAttribute("chosenFile")%>
        </h1>

        <table>
            <%ArrayList<Block> blocks = (ArrayList<Block>) request.getAttribute("blocks");%>
            <%for (Block block : blocks){%>
                <tr>
                    <%for (Item item : block.getItems()){%>
                        <th>
                            <%=item.getValue()%>
                        </th>
                    <%}%>
                </tr>
            <%}%>
        </table>

    </body>
</div>

<jsp:include page="footer.jsp"/>
</html>