package search;

import DBConnection.DBConnection;
import DBConnection.Page;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(
        name = "SearchResponseServlet",
        urlPatterns = "/search"
)
public class SearchResponseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryString=request.getParameter("search");

        try {
            List<Page> results=searchDB(queryString);
            request.setAttribute("results",results);
            request.getRequestDispatcher("result.jsp").forward(request,response);

        }
        catch(Exception ex){
            ex.printStackTrace();
            PrintWriter writer=response.getWriter();
            writer.write("System error!\nCannot get the search results. Please try later.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * split query string into keywords.
     * @param queryString query string.
     * @return string array.
     */
    private String[] getKeywords(String queryString){
        return queryString.split(" ");
    }

    /**
     * using keywords to search DB.
     * @param queryString
     * @return List<Page>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private List<Page> searchDB(String queryString) throws SQLException, ClassNotFoundException {
        String[] keywords=getKeywords(queryString);
        List<Page> results=new LinkedList<>();
        for(String keyword:keywords) {
            ResultSet wordIDs = DBConnection.search("word", "word = '" + keyword+"'", "wordID");
            int index = 1;
            while (wordIDs.next()) {
                ResultSet pageIDs = DBConnection.search("page_word", "wordID=" + wordIDs.getInt(index++), "pageID");
                int j = 1;
                while (pageIDs.next()) {
                    ResultSet pages = DBConnection.search("page", "pageID=" + pageIDs.getInt(j++));
                    if (pages.next()) {
                        Page p = new Page(pages.getString(1),
                                pages.getString(2),
                                pages.getString(3),
                                pages.getString(4),
                                pages.getDate(5)
                        );
                        results.add(p);
                    }
                }
            }
        }
        return results;
    }
}
