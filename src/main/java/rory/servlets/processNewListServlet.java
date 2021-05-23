package rory.servlets;

import rory.model.Model;
import rory.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/processNewList.html")
public class processNewListServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        String newListName = request.getParameter("list");

        ServletContext context = getServletContext();
        //List names cannot contain a space or there will be an error
        if (!newListName.contains(" ")) {
            model.createNewList(newListName);
            RequestDispatcher dispatch = context.getRequestDispatcher("/editList.html");
            dispatch.forward(request, response);
        } else {
            RequestDispatcher dispatch = context.getRequestDispatcher("/newList.html");
            dispatch.forward(request, response);
        }
    }
}