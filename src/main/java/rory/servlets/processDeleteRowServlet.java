package rory.servlets;

import rory.model.Model;
import rory.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/processDeleteRow.html")
public class processDeleteRowServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        String blockHashCode = request.getParameter("block");
        model.deleteBlock(blockHashCode);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editList.html");
        dispatch.forward(request, response);
    }
}