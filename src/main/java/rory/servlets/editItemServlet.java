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

@WebServlet("/editItem.html")
public class editItemServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Model model = ModelFactory.getModel();
        String list = request.getParameter("list");
        String item = request.getParameter("item");

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editListPage.jsp");
        dispatch.forward(request, response);
    }
}