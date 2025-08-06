package com.bulut.dao;

import com.bulut.model.Author;
import com.bulut.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private List<Book> books = new ArrayList<>();

    public void addBook(String title, Author author, String isbn){
        Book book = searchBookIsbn(isbn);
        if (book != null){
            System.out.println("Daha Önce Eklendi");
        }else {
            Book book1 = new Book(title, author, isbn);
            books.add(book1);
            System.out.println("Kitap başarılı bir şekilde eklendi");
        }
    }

    public List<Book> getAllBooks(){

        return books;
    }

    public Book searchBookIsbn(String isbn){
        return books.stream()
                .filter(books -> books.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElse(null);
    }
}
