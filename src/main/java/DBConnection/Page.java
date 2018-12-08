package DBConnection;

import java.sql.Date;

public class Page {
    private int pageID;
    private String url;
    private String title;
    private Date lastModified;

    public void setFrenquence(int frenquence) {
        this.frenquence = frenquence;
    }

    public int getFrenquence() {
        return frenquence;
    }

    private String description;
    private int frenquence;

    public String getDescription() {
        return description;
    }
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

    public int getWordID() {
        return wordID;
    }

    public Page(int pageID, String url, String title, Date lastModified, int wordID) {
        this.pageID = pageID;
        this.url = url;
        this.title = title;
        this.lastModified = lastModified;
        this.wordID = wordID;
    }

    public Page(String url, String title, Date lastModified, int wordID) {
        this.url = url;
        this.title = title;
        this.lastModified = lastModified;
        this.wordID = wordID;
    }

    public Page(int pageID, String url, String title, Date lastModified, String description, int wordID) {
        this.pageID = pageID;
        this.url = url;
        this.title = title;
        this.lastModified = lastModified;
        this.description = description;
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

    public Page(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

}
