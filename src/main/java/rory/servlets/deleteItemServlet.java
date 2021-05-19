package rory.servlets;

import rory.model.Block;
import rory.model.Item;
import rory.model.Model;
import rory.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/deleteItem.html")
public class deleteItemServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Model model = ModelFactory.getModel();
        String item = request.getParameter("item");

        model.deleteItem(item);

        request.setAttribute("operation", "delete");

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editList.html");
        dispatch.forward(request, response);
    }
}