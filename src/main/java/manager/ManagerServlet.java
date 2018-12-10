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
        int status=0;
        if(action.equals("start")){
            if(url.equals("")){
                message="URL input cannot be empty";
                status=2;
            }
            else{
                if(!Scraping.isRuning()){
                    message="Scraping is starting.";
                    Scraping.setStartingURL(url);
                    Scraping scraping=new Scraping();
                    scraping.start();
                    status=0;
                }
                else{
                    message="Scraping is already running.";
                    status=1;
                }
            }
        }
        else {
            if(!Scraping.isRuning()){
                message="Scraping hasn't been started!";

                status=1;
            }
            else {
                message="Scraping is stopping!";
                Scraping.stopScraping();
                status=0;
            }
        }
        request.setAttribute("message",message);
        request.setAttribute("status",status);
        System.out.print(Scraping.isRuning());
        request.setAttribute("dbstatus",Scraping.isRuning());
        request.getRequestDispatcher("manager.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
