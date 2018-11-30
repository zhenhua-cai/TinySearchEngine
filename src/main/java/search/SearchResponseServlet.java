package search;

import scraping.Scraping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "SearchResponseServlet",
        urlPatterns = "/search"
)
public class SearchResponseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Scraping scraping=new Scraping();
        try {
            scraping.scraping();
            PrintWriter writer = response.getWriter();
            response.setContentType("text/html");
            writer.write(scraping.getBody());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
