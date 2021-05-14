package rory.servlets;

import rory.model.Block;
import rory.model.Model;
import rory.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

@WebServlet("/displaySearchResults.html")
public class displayListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Model model = ModelFactory.getModel();
        String chosenFile = request.getParameter("list");
        request.setAttribute("chosenFile", chosenFile);

        model.loadFile(chosenFile);

        ArrayList<Block> blocks = model.getNumberOfNextBlocks(5);
        request.setAttribute("blocks", blocks);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/displayList.jsp");
        dispatch.forward(request, response);
    }
}