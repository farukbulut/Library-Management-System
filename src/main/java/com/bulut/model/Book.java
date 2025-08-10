package com.bulut.model;

import java.time.LocalDate;

public class Book {
    private String title;
    private String isbn;
    private Author author;
    private LocalDate loanDate;
    private boolean borrowed;
    public Book(String title, String isbn, Author author) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Book(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author.getName() + " " + author.getLastname() +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
