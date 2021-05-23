package rory.servlets;

import org.apache.coyote.Request;
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        Model model = ModelFactory.getModel();

        String chosenFile = request.getParameter("list");
        request.setAttribute("list", chosenFile);

        RequestDispatcher dispatch;
        try {
            model.loadFile(chosenFile);
            ArrayList<Block> blocks = model.getBlocks();
            request.setAttribute("blocks", blocks);
            dispatch = context.getRequestDispatcher("/editListPage.jsp");
        } catch (FileNotFoundException e){
            dispatch = context.getRequestDispatcher("/fileNotFoundPage.jsp");
        }

        dispatch.forward(request, response);
    }
}