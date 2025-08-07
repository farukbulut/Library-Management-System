package com.bulut.controller;

import com.bulut.dao.BookDao;
import com.bulut.model.Author;
import com.bulut.model.Book;
import com.bulut.ui.InputManager;

import java.time.LocalDate;

public class BookController {
    private BookDao bookDao;

    public BookController(BookDao bookDao){
        this.bookDao = bookDao;
    }

    public void addBook() {
        System.out.println("\n--- YENİ Kitap EKLEME ---");

        Book book = bookInput();
        try {
            this.bookDao .addBook(book);
            System.out.println("✅ Kitap başarıyla eklendi!");
        } catch (Exception e) {
            System.out.println("❌ Kitap eklenirken hata oluştu: " + e.getMessage());
        }
    }

    private Book bookInput(){
        System.out.print("Yazar ID: ");
        int id = InputManager.getValidIntInput();

        System.out.print("Kitap Adı: ");
        String title = InputManager.getValidStringInput();

        System.out.print("Kitap İsbn: ");
        String isbn = InputManager.getValidStringInput();

        Author author = new Author(34,"sa", "ssdf", LocalDate.of(2011,9, 4));
        return  new Book(title, isbn, author);
    }
}
