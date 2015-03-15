package com.apps.wow.nawdr5min;

/**
 * Created by Alexandr on 23.02.2015.
 */
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alexandr on 13.02.2015.
 */
public class PostItem {
    private String logo;
    private String title;
    private String link;
    private String description;
    private Date date;
    private String author;
    private ArrayList<String> categories;
    private String img;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public String getImg() {
        return img;

    }

    public void setImg(String img) {
        this.img = img;
    }

}