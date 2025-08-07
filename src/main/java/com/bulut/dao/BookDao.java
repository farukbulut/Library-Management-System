package com.bulut.dao;

import com.bulut.model.Author;
import com.bulut.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        if (searchBookIsbn(book.getIsbn()) != null){
            System.out.println("Daha Önce Eklendi");
            return;
        }

        books.add(book);
        System.out.println("Kitap başarılı bir şekilde eklendi");
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
