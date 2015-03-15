package com.apps.wow.nawdr5min;

/**
 * Created by Alexandr on 23.02.2015.
 */
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexandr on 13.02.2015.
 */
public class PostItem {

    private String   title;
    private String   link;
    private String   description;
    private Date     date;
    //private String   author;
    private ArrayList<String> categories;
    private String   img;


    public String getTitle() {
        return title;
    }

    public PostItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLink() {
        return link;
    }

    public PostItem setLink(String link) {
        this.link = link;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PostItem setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public PostItem setDate(Date date) {
        this.date = date;
        return this;
    }
    public PostItem setDate(String input){
        String[] dateParts = input.split(" ");

        Date idate = new Date(Integer.parseInt(dateParts[2]),      //year
                                  convertMonth(dateParts[1]),      //month
                              Integer.parseInt(dateParts[0]));     //day

        this.date = idate;
        return this;
    }
    private int convertMonth(String month)
    {
        List<String> monthes = Arrays.asList("Янв", "Фев", "Мар", "Апр", "Май", "Июн", "Июл", "Авг", "Сен", "Окт", "Ноя", "Дек");
        return monthes.indexOf(month)+1; // from 1 to 12, not 0 to 11

    }
    /* public String getAuthor() {
        return author;
    }

    public PostItem setAuthor(String author) {
        this.author = author;
        return this;
    }
    */
    public ArrayList<String> getCategories() {

        return categories;
    }

    public PostItem setCategories(ArrayList<String> categories) {
        this.categories = categories;
        return this;
    }
    public PostItem setCategories(Elements iCategories)
    {
        ArrayList<String> temp = new ArrayList<>();
        for (Element cat: iCategories)
        {
            temp.add(cat.text());
        }
        this.categories = temp;
        return this;
    }

    public String getImg() { return img;}

    public PostItem setImg(String img) {
        this.img = img;
        return this;
    }

}