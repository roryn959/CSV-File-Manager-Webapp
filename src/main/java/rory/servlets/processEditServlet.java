package rory.servlets;

import rory.model.Model;
import rory.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/processEdit.html")
public class processEditServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Model model = ModelFactory.getModel();

        String itemHash = request.getParameter("item");
        String newType = request.getParameter("newType");
        String newValue = request.getParameter("newValue");

        //Save which list we were editing so we can return to editing that same list
        String chosenFile = request.getParameter("list");
        System.out.println("*** list: " + chosenFile);

        model.editItem(itemHash, newType, newValue);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editList.html");
        dispatch.forward(request, response);
    }
}