<%@ page import="rory.model.Result" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Rory
  Date: 22/05/2021
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        padding: 5px;
    }
</style>

    <head>
        <jsp:include page="header.jsp"/>
        <title>
            Search
        </title>


    <div style="padding: 15px">
        <h2>Search</h2>

            <form action="/processSearch.html" method="post">
                <!--List name checkboxes-->
                <%String[] listNames = (String[]) request.getAttribute("listNames");%>
                <label>Search in particular lists:</label>
                <%for (String name : listNames){%>
                    <br>
                    <input type="checkbox" id="<%=name%>" name="listsChosen" value="<%=name%>">
                    <label for="<%=name%>"><%=name%></label>
                <%}%>

                <br><br>
                <!--Filter-->
                <label for="filter">Filter:</label>
                <select name="filter" id="filter">
                    <option value="none">None</option>
                    <option value="text">Text</option>
                    <option value="link">Links</option>
                    <option value="image">Images</option>
                    <option value="list">Lists</option>
                </select>

                <br><br>

                <!-- Search bar-->
                <input type="text" name="query" required>
                <input type="submit" value="Go">
            </form>
    </div>

    </head>

    <body>

    <div style="padding-left: 15px">

    <%ArrayList<Result> results = (ArrayList<Result>) request.getAttribute("results");
      ArrayList<String> listsFound = (ArrayList<String>) request.getAttribute("listsFound");%>

    <!-- If they have actually searched yet -->
    <%if (results != null) { %>

        Showing results for the search "<%=request.getParameter("query")%>"<br><br>

        <%if (listsFound.size() == 0) {%>
            No lists found...<br><br>
        <%} else {%>
            Found <%=listsFound.size()%> list name(s) similar to your search:

            <table>
                <%for (String list : listsFound){%>
                    <tr>
                        <th>
                            <form method="post" action="/displayList.html">
                                <input type="hidden" name="list" value="list">
                                <input type="submit" value="<%=list%>">
                            </form>
                        </th>
                    </tr>
                <%}%>
            </table>
        <%}%>

        <%if (results.size() == 0) {%>
            No entries found...
        <%} else {%>
            Found <%=results.size()%> entries in your lists:

            <table>
                <tr>
                    <th>List</th>
                    <th>Type</th>
                    <th>Position (Row, Column)</th>
                    <th>Content</th>
                </tr>

            <%for (Result r : results) {%>
                <tr>
                    <td>
                        <form action="/displayList.html" method="post">
                            <input type="hidden" name="list" value="<%=r.getList()%>">
                            <input type="submit" value="<%=r.getList()%>">
                        </form>
                    </td>

                    <td>
                        <%=r.getType()%>
                    </td>

                    <td>
                        <%=r.getRow()%>, <%=r.getColumn()%>
                    </td>

                    <td>
                        <%=r.getText()%>
                    </td>
                </tr>
                <%}%>
            </table>
        <%}%>
    <%}%>

    </div>

    </body>
    <jsp:include page="footer.jsp"/>
</html>

