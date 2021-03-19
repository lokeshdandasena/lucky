package com.stackroute.searchservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Book {
    private String id;
    private String bookTitle;
    private String authorName;
    private String[] bookGenre;

    public Book() {

    }

    public Book(String id, String bookTitle, String authorName, String[] bookGenre) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.bookGenre = bookGenre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String[] getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String[] bookGenre) {
        this.bookGenre = bookGenre;
    }
}
