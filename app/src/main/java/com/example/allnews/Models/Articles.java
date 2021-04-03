package com.example.allnews.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Articles {
    @SerializedName("source")
    @Expose
    private Source source;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String desp;

    @SerializedName("urlToImage")
    @Expose
    private String utom;

    @SerializedName("publishedAt")
    @Expose
    private String pub;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUtom() {
        return utom;
    }

    public void setUtom(String utom) {
        this.utom = utom;
    }
}
