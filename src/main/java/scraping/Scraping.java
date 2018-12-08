package scraping;

import DBConnection.DBConnection;
import DBConnection.NameValuePair;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Scraping extends Thread{
    private static String startingURL="https://www.cbsnews.com/";
    private static String USER_AGENT="User-Agent";
    private static String USER_AGENT_VALUE="Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0";
    private static boolean isScrapying=false;
    static {
        Unirest.setDefaultHeader(USER_AGENT,USER_AGENT_VALUE);
    }
    public static void setStartingURL(String url){
        startingURL=url;
    }
    public static void stopScraping(){
        isScrapying=false;
    }
    public static void changeStatus(){
        isScrapying=isScrapying^true;
    }
    public static void startScraping() {
        isScrapying=true;
        crawlingURL(startingURL);
    }
    public static boolean isRuning(){return isScrapying;}
    private static void crawlingURL(String startingURL){
        if(!isScrapying) return;
        int pageID,wordID;
        try {
            ResultSet page=DBConnection.search("page","url='"+startingURL+"'","pageID");

            if(page.next()) {
                page.close();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        HttpResponse<String> response;
        try {
            response=Unirest.get(startingURL).asString();
            if(response.getStatus()!=200) return;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return;
        }
        Document dom= Jsoup.parse(response.getBody());
        Elements es=dom.head().getElementsByAttributeValue("name", "keywords");
        String title;
        try {
             title= dom.head().getElementsByTag("title").get(0).text();
             if(title.length()>50) title=title.substring(0,50);
        }catch(Exception ex){
            ex.printStackTrace();
            return;
        }
        if(es.size()==0) return;
        Element e=es.get(0);
        String body=dom.body().text();
        String content=e.attr("content");
        if(content.equals("")) {
            return;
        }
        Date date= Date.valueOf(LocalDate.now());
        try {
            DBConnection.insert("page",
                    new NameValuePair("url",startingURL),
                    new NameValuePair("title",title),
                    new NameValuePair("last_modified",date)
            );
            ResultSet page=DBConnection.search("page","url='"+startingURL+"'","pageID");
            if(page.next()) {
                pageID = page.getInt(1);
            }
            else{
                page.close();
                return;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            return;
        }

        String[] keywords=content.split(",");

        for(String word:keywords) {
            try {
                word=word.trim();
                ResultSet result=DBConnection.search("word","word='"+word+"'","wordID");
                if(result.next()){
                    wordID=result.getInt(1);
                }
                else{
                    DBConnection.insert("word",new NameValuePair("word",word));
                   ResultSet newResult=DBConnection.search("word","word ='"+word+"'","wordID");
                    //ResultSet newResult=DBConnection.search("select wordID from word where word='"+word+"' limit 0, 10");
                    if(newResult.next())
                        wordID=newResult.getInt(1);
                    else{
                        newResult.close();
                        continue;
                    }
                }
                String description=AnalyzeString.getPara(body,word);

                DBConnection.insert("page_word",
                        new NameValuePair("pageID",pageID),
                        new NameValuePair("wordID",wordID),
                        new NameValuePair("description",description),
                        new NameValuePair("frequency",0)
                );
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        Elements links=dom.body().getElementsByTag("a");
        for(Element link:links) {
            String url=link.attr("href").trim();
            if(!url.startsWith("http")) continue;
            if(url.length()>255) continue;
            try {
                ResultSet r=DBConnection.search("page","url='"+url+"'","pageID");
                if(!r.next()) {
                    r.close();
                    crawlingURL(url);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    public void run(){
        startScraping();
    }
}
