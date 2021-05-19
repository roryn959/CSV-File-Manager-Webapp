package rory.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/addItem.html")
public class addItemServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chosenFile = request.getParameter("list");
        request.setAttribute("list", chosenFile);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/addItemPage.jsp");
        dispatch.forward(request, response);
    }
}