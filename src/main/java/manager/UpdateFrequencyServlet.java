package manager;

import DBConnection.DBConnection;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(
        name = "UpdateFrequencyServlet",
        urlPatterns = "/updatefrequency"
)
public class UpdateFrequencyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        JSONObject jo=new JSONObject(jb.toString());
        String link=jo.getString("link");
        String wordID=jo.getString("wordID");
        try {
            updateFrequency(link,wordID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    private void updateFrequency(String data,String wordID) throws SQLException {
        if(data.endsWith("/"))
            data=data.substring(0,data.length()-1);
        ResultSet result=DBConnection.search("select pageID from page where url='"+data+"';");
        if(result.next()) {
            int pageID = result.getInt(1);
            DBConnection.updateDB("update page_word set frequency=frequency+1 " +
                    "where wordID='" + wordID + "' and pageID='" + pageID + "';");
        }
    }
}
