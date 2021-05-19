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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();

        Model model = ModelFactory.getModel();
        String chosenFile = request.getParameter("list");
        request.setAttribute("list", chosenFile);

        RequestDispatcher dispatch;
        try {
            model.loadFile(chosenFile);
            model.filterBy(request.getParameter("filter"));
            ArrayList<Block> blocks = model.getBlocks();
            request.setAttribute("blocks", blocks);
            dispatch = context.getRequestDispatcher("/displayListPage.jsp");
        } catch (FileNotFoundException e){
            dispatch = context.getRequestDispatcher("/fileNotFoundPage.jsp");
        }

        dispatch.forward(request, response);
    }
}