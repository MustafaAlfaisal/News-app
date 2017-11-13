package com.example.musta.newsapp;

/**
 * Created by mustafa on 11/12/2017.
 */

public class Response {

    private String webTitle;
    private String sectionName;
    private String author;
    private String webUrl;
    private String webPublicationDate;

    public Response(String webTitle, String sectionName, String author, String webUrl, String webPublicationDate) {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
        this.author = author;
        this.webUrl = webUrl;
        this.webPublicationDate = webPublicationDate;
    }


    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }


    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

}

