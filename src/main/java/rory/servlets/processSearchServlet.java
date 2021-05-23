package rory.servlets;

import rory.model.Model;
import rory.model.ModelFactory;
import rory.model.Result;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

@WebServlet("/processSearch.html")
public class processSearchServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        String query = request.getParameter("query");
        String[] listNames = model.getDataFileNames();
        request.setAttribute("listNames", listNames);

        //If query is null, user has just opened page and not searched yet.
        if (query != null) {
            //Get the values of the checked boxes. Important to only do so after the form is submitted
            String[] chosenLists = request.getParameterValues("listsChosen");
            String filter = request.getParameter("filter");
            ArrayList<String> listsFound = model.searchListNames(query);

            //If no boxes were checked, search in every list
            ArrayList<Result> results;
            if (chosenLists == null) {
                 results = model.search(null, filter, query);
            } else {
                results = new ArrayList<>();
                for (String listName : chosenLists){ //For every checked list
                    ArrayList<Result> listResults = model.search(listName, filter, query);
                    //Add all the results for this particular list search to the overall search results.
                    for (Result r : listResults){
                        results.add(r);
                    }
                }
            }

            request.setAttribute("listsFound", listsFound);
            request.setAttribute("results", results);
        } else{
            request.setAttribute("results", null);
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/searchPage.jsp");
        dispatch.forward(request, response);
    }
}