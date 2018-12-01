package DBConnection;

import java.sql.Date;

public class Page {
    private String pageID;
    private String url;
    private String title;
    private String sentence;
    private Date lastModified;

    public String getPageID() {
        return pageID;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getSentence() {
        return sentence;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Page(String url, String title, String sentence) {
        this.url = url;
        this.title = title;
        this.sentence = sentence;
    }

    public Page(String url, String title, String sentence, Date lastModified) {
        this.url = url;
        this.title = title;
        this.sentence = sentence;
        this.lastModified = lastModified;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Page(String p, String u, String t, String s, Date l){
        pageID=p;
        url=u;
        title=t;
        sentence=s;
        lastModified=l;
    }
}
