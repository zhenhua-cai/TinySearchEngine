package search;

import DBConnection.DBConnection;
import DBConnection.Page;
import scraping.Scraping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@WebServlet(
        name = "SearchResponseServlet",
        urlPatterns = "/search"
)
public class SearchResponseServlet extends HttpServlet {

    static{
        Scraping scraping=new Scraping();
        scraping.start();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get the input query string.
        String queryString=request.getParameter("search");
        try {
            List<Page> results=searchDB(queryString,0);
            //forward request to result.jsp.
            request.setAttribute("results",results);
            request.setAttribute("keyword",queryString);
            request.setAttribute("startingIndex",0);
            request.getRequestDispatcher("result.jsp").forward(request,response);
        }
        catch(Exception ex){
            //if there are exception, prints to console.
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
    private List<Page> searchDB(String queryString,int startingIndex) throws SQLException, ClassNotFoundException {
        //split query string into keywords.
        String[] keywords=getKeywords(queryString);
        List<Page> results=new LinkedList<>();
        Set<Integer> ids=new HashSet<>();
        for(String keyword:keywords) {
            //search each keyword.
            ResultSet wordIDs = DBConnection.search("word", "word like '%" + keyword+"%'", "wordID");
            while (wordIDs.next()) {
                int wordID=wordIDs.getInt(1);
                ResultSet pageIDs=DBConnection.search("select pageID,description from page_word where wordID="+wordID+" limit "+startingIndex+", 10");

                while (pageIDs.next()) {
                    Integer pageID=pageIDs.getInt(1);
                    if(ids.contains(pageID)){
                        continue;
                    }
                    ids.add(pageID);
                    String description=pageIDs.getString(2);
                    ResultSet pages = DBConnection.search("page", "pageID=" + pageID,"*");
                    //Create a page object.
                    if (pages.next()) {
                        Page p = new Page(
                                pages.getInt(1),
                                pages.getString(2),
                                pages.getString(3),
                                pages.getDate(4),
                                description,
                                wordID
                        );
                        results.add(p);
                    }

                }
            }
        }
        Collections.sort(results,(p1,p2)->{
            return p1.getLastModified().compareTo(p2.getLastModified());
        });
        return results;
    }
}
