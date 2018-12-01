package manager;

import scraping.Scraping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "ManagerServlet",
        urlPatterns = "/manageDB"
)
public class ManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Scraping scraping=new Scraping();
        scraping.start();

        response.setContentType("text/html");
        PrintWriter writer=response.getWriter();
        writer.write("<h1>Started successfully</h1>");
        writer.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
