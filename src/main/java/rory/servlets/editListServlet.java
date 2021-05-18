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

@WebServlet("/editList.html")
public class editListServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        String chosenFile = request.getParameter("list");

        request.setAttribute("chosenFile", chosenFile);

        model.loadFile(chosenFile);

        ArrayList<Block> blocks = model.getBlocks();
        request.setAttribute("blocks", blocks);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/editListPage.jsp");
        dispatch.forward(request, response);
    }
}