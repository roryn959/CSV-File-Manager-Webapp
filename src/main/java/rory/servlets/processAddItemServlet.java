package rory.servlets;

import rory.model.Model;
import rory.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/processAddItem.html")
public class processAddItemServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        String blockHash = request.getParameter("block");
        String newType = request.getParameter("newType");
        String newValue = request.getParameter("newValue");

        model.addToBlock(blockHash, newType, newValue);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editList.html");
        dispatch.forward(request, response);
    }
}