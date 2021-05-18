package rory.servlets;

import rory.model.Model;
import rory.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/displayListPicker.html")
public class displayListPickerServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("nextPage", "displayList.html");

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/pickList.html");
        dispatch.forward(request, response);
    }
}