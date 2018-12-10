package showdatabase;

import DBConnection.DBConnection;
import scraping.Scraping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(
        name = "ShowDatabaseServlet",
        urlPatterns = "/database"
)
public class ShowDatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String table=request.getParameter("database");
        if(table==null){
            request.getRequestDispatcher("manager.jsp").forward(request,response);
        }
        List<List<String>> result= searchTable(table);
        request.setAttribute("result",result);
        request.setAttribute("table",table);
        request.setAttribute("dbstatus", Scraping.isRuning());
        request.getRequestDispatcher("manager.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    private List<List<String>> searchTable(String table){
        List<List<String>> result=new LinkedList<>();
        try {
            ResultSet  resultSet;
            if(!table.equals("page_word"))
                resultSet = DBConnection.search("select * from "+table+" limit 50;");
            else
                resultSet = DBConnection.search("select * from "+table+" where description !='null'  order by frequency desc  limit 50;");
            while(resultSet.next()){
                List<String> row = new LinkedList<>();
                switch(table) {
                    case "page":
                        row.add(String.valueOf(resultSet.getInt(1)));
                        String str=resultSet.getString(2);
                        if(str.length()>50)
                            str=str.substring(0,50)+"...";
                        row.add(str);
                        str=resultSet.getString(3);
                        if(str.length()>50)
                            str=str.substring(0,50)+"...";
                        row.add(str);
                        str=resultSet.getString(4);
                        if(str.length()>50)
                            str=str.substring(0,50)+"...";
                        row.add(str);
                        break;
                    case "word":

                        row.add(String.valueOf(resultSet.getInt(1)));
                        String str2=resultSet.getString(2);
                        if(str2.length()>50)
                            str2=str2.substring(0,50)+"...";
                        row.add(str2);
                        break;
                    case "page_word":
                        row.add(String.valueOf(resultSet.getInt(1)));
                        row.add(String.valueOf(resultSet.getInt(2)));
                        row.add(String.valueOf(resultSet.getInt(3)));
                        String str3=resultSet.getString(4);
                        if(str3.length()>100)
                            str3=str3.substring(0,100)+"...";
                        row.add(str3);

                        break;
                    case "pageneedscraping":
                        row.add(String.valueOf(resultSet.getInt(1)));
                        String str4=resultSet.getString(2);
                        if(str4.length()>150)
                            str4=str4.substring(0,100)+"...";
                        row.add(str4);
                        break;
                }
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
