package rory.servlets;

import rory.model.Model;
import rory.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/editItem.html")
public class editItemServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chosenFile = request.getParameter("list");
        request.setAttribute("list", chosenFile);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/getEditPage.jsp");
        dispatch.forward(request, response);
    }
}