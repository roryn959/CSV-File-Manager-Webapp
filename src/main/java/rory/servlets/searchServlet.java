package rory.servlets;

import rory.model.Model;
import rory.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/search.html")
public class searchServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Model model = ModelFactory.getModel();
        String[] dataFileNames = model.getDataFileNames();
        request.setAttribute("dataFileNames", dataFileNames);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/searchPage.jsp");
        dispatch.forward(request, response);
    }
}