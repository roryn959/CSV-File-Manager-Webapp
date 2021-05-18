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

@WebServlet("/displayList.html")
public class displayListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Model model = ModelFactory.getModel();
        String chosenFile = request.getParameter("list");
        request.setAttribute("chosenFile", chosenFile);

        model.loadFile(chosenFile);

        ArrayList<Block> blocks = model.getBlocks();
        request.setAttribute("blocks", blocks);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/displayListPage.jsp");
        dispatch.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();

        String chosenFile = request.getParameter("list");
        String filter = request.getParameter("filter");

        request.setAttribute("chosenFile", chosenFile);

        model.loadFile(chosenFile);
        model.filterBy(filter);

        ArrayList<Block> blocks = model.getBlocks();
        request.setAttribute("blocks", blocks);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/displayListPage.jsp");
        dispatch.forward(request, response);
    }
}