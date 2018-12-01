package DBConnection;

import java.sql.Date;

public class Page {
    private int pageID;
    private String url;
    private String title;
    private String sentence;
    private Date lastModified;
    private int wordID;

    public int getPageID() {
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

    public int getWordID() {
        return wordID;
    }

    public Page(int pageID, String url, String title, String sentence, Date lastModified, int wordID) {
        this.pageID = pageID;
        this.url = url;
        this.title = title;
        this.sentence = sentence;
        this.lastModified = lastModified;
        this.wordID = wordID;
    }

    public Page(String url, String title, String sentence, int wordID) {
        this.url = url;
        this.title = title;
        this.sentence = sentence;
        this.wordID = wordID;
    }

    public Page(String url, String title, String sentence, Date lastModified, int wordID) {
        this.url = url;
        this.title = title;
        this.sentence = sentence;
        this.lastModified = lastModified;
        this.wordID = wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
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

    public Page(int p, String u, String t, String s, Date l){
        pageID=p;
        url=u;
        title=t;
        sentence=s;
        lastModified=l;
    }
}
