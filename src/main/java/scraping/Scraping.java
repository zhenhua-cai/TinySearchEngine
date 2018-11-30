package scraping;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Scraping {
    private static String startingURL="https://www.nytimes.com/";
    private static String USER_AGENT="User-Agent";
    private static String USER_AGENT_VALUE="Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:64.0) Gecko/20100101 Firefox/64.0";
    private StringBuilder str=new StringBuilder();


    public void scraping() throws UnirestException {
        Unirest.setDefaultHeader(USER_AGENT,USER_AGENT_VALUE);
        HttpResponse<String> response=Unirest.get(startingURL).asString();
        str.append(response.getBody());
    }
    public String getBody(){
        return str.toString();
    }
}
