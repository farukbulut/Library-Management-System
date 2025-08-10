package com.bulut.dao;

import com.bulut.model.Author;
import com.bulut.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        if (searchBookIsbn(book.getIsbn()) != null){
            System.out.println("❌ Hata ! Kayıt Eklenemedi" + book.getIsbn() + " nolu isbn ekli!");
        }else {
            books.add(book);
            System.out.println("✅ Kitap başarıyla eklendi!");
        }

    }

    public List<Book> getAllBooks(){
        return new ArrayList<>(books);
    }

    public Book searchBookIsbn(String isbn){
        return books.stream()
                .filter(books -> books.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElse(null);
    }

    public void delete(String isbn){
        if (searchBookIsbn(isbn) == null){
            System.out.println("❌ Kitap bulunamadı!");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equalsIgnoreCase(isbn)) {
                String title = books.get(i).getTitle();
                books.remove(i);
                System.out.println("✅ " + title + " isimli litap silindi!");
                return;
            }
        }
    }

    public void update(Book book) {
        if (searchBookIsbn(book.getIsbn()) == null){
            System.out.println("❌ Kitap bulunamadı!");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equalsIgnoreCase(book.getIsbn())) {
                books.set(i, book); // Eski author yerine yeni author'u koy
                System.out.println("✅ " + book.getTitle() + " isimli kitap güncellendi!");
                return;
            }
        }
    }

    public int count(){
        return this.books.size();
    }

    public List<Book> findByNameContaining(String searchTerm) {
        return books.stream()
                .filter(book ->
                        book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
                .toList();
    }
}
