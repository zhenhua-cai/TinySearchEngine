package showdatabase;

import DBConnection.DBConnection;

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
        List<List<String>> result= searchTable(table);
        request.setAttribute("result",result);
        request.setAttribute("table",table);
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
                resultSet = DBConnection.search("select * from "+table+" limit 20;");
            else
                resultSet = DBConnection.search("select * from "+table+" where description !='null' limit 20;");
            while(resultSet.next()){
                switch(table) {
                    case "page":
                        List<String> row1 = new LinkedList<>();
                        row1.add(String.valueOf(resultSet.getInt(1)));
                        String str=resultSet.getString(2);
                        if(str.length()>50)
                            str=str.substring(0,50)+"...";
                        row1.add(str);
                        str=resultSet.getString(3);
                        if(str.length()>50)
                            str=str.substring(0,50)+"...";
                        row1.add(str);
                        str=resultSet.getString(4);
                        if(str.length()>50)
                            str=str.substring(0,50)+"...";
                        row1.add(str);
                        result.add(row1);
                        break;
                    case "word":
                        List<String> row2 = new LinkedList<>();
                        row2.add(String.valueOf(resultSet.getInt(1)));
                        String str2=resultSet.getString(2);
                        if(str2.length()>50)
                            str2=str2.substring(0,50)+"...";
                        row2.add(str2);
                        result.add(row2);
                        break;
                    case "page_word":
                        List<String> row3 = new LinkedList<>();
                        row3.add(String.valueOf(resultSet.getInt(1)));
                        row3.add(String.valueOf(resultSet.getInt(2)));
                        row3.add(String.valueOf(resultSet.getInt(3)));
                        String str3=resultSet.getString(4);
                        if(str3.length()>50)
                            str3=str3.substring(0,50)+"...";
                        row3.add(str3);
                        result.add(row3);
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
