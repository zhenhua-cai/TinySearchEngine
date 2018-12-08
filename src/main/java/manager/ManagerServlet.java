package manager;

import scraping.Scraping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;

@WebServlet(
        name = "ManagerServlet",
        urlPatterns = "/manageDB"
)
public class ManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        String url=request.getParameter("url");
        String message="";
        if(action.equals("start")){
            if(url.equals("")){
                message="URL input cannot be empty";
            }
            else{
                if(!Scraping.isRuning()){
                    message="Scraping is starting.";
                    Scraping.setStartingURL(url);
                    Scraping scraping=new Scraping();
                    scraping.start();
                }
                else{
                    message="Scraping is already running.";
                }
            }
        }
        else {
            if(!Scraping.isRuning()){
                message="Scraping hasn't been started!";
            }
            else {
                message="Scraping is stopping!";
                Scraping.stopScraping();
            }
        }
        request.setAttribute("message",message);
        request.getRequestDispatcher("manager.jsp").forward(request,response);

        response.setContentType("text/html");
        PrintWriter writer=response.getWriter();
        writer.write("<h1>Started successfully</h1>");
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
