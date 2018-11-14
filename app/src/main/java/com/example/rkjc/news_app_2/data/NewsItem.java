package com.example.rkjc.news_app_2.data;

/*
Add these annotations
@Entity(tableName = "word_table")
   - Each @Entity class represents an entity in a table. Annotate your class declaration to indicate that it's an entity. Specify the name of the table if you want it to be different from the name of the class.
@PrimaryKey
   - Every entity needs a primary key. To keep things simple, each word acts as its own primary key.
@NonNull
   - Denotes that a parameter, field, or method return value can never be null.
@ColumnInfo(name = "word")
   - Specify the name of the column in the table if you want it to be different from the name of the member variable.
   - Every field that's stored in the database needs to be either public or have a "getter" method. This sample provides a getWord() method.
* */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "news_item")
public class NewsItem {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "urlToImage")
    private String urlToImage;

    @ColumnInfo(name = "publishedAt")
    private String publishedAt;

    public NewsItem(@NonNull int id, String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    @Ignore
    public NewsItem(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    //
//public class NewsItem {
//    private String author;
//    private String title;
//    private String description;
//    private String url;
//    private String urlToImage;
//    private String publishedAt;
//
//    public NewsItem(String author, String title, String description, String url, String urlToImage, String publishedAt) {
//        this.author = author;
//        this.title = title;
//        this.description = description;
//        this.url = url;
//        this.urlToImage = urlToImage;
//        this.publishedAt = publishedAt;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getUrlImage() {
//        return urlToImage;
//    }
//
//    public void setUrlImage(String urlToImage) {
//        this.urlToImage = urlToImage;
//    }
//
//    public String getPublishedAt() {
//        return publishedAt;
//    }
//
//    public void setPublishedAt(String publishedAt) {
//        this.publishedAt = publishedAt;
//    }
}
