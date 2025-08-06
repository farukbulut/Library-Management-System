package com.bulut;

import com.bulut.dao.AuthorDao;
import com.bulut.dao.BookDao;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AuthorDao authorDao = new AuthorDao();
        authorDao.addAuthor("Omer", "Bulut", LocalDate.of(1997, 4, 9));
        authorDao.addAuthor("Faruk", "Bulut", LocalDate.of(1997, 4, 9));
        BookDao bookDao = new BookDao();
        bookDao.addBook("Test", authorDao.searchAuthor("Faruk"), "sadasd");
        bookDao.addBook("Test", authorDao.searchAuthor("Faruk"), "sadasd");
    }
}