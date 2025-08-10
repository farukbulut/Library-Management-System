package com.bulut;

import com.bulut.dao.AuthorDao;
import com.bulut.dao.BookDao;
import com.bulut.model.Author;
import com.bulut.model.Book;
import com.bulut.ui.MenuManager;

import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {
        System.out.println("=== KÜTÜPHANE YÖNETİM SİSTEMİ ===");

        AuthorDao authorDao = new AuthorDao();
        BookDao bookDao = new BookDao();
        loadSampleData(authorDao, bookDao);
        MenuManager menu = new MenuManager(authorDao, bookDao);
        int choice;
        do {
            choice = menu.showMainMenu();
            menu.handleMainMenu(choice);
        } while (choice != 4);

        System.out.println("Sistem kapatılıyor... İyi günler!");
    }

    private static void loadSampleData(AuthorDao authorDao, BookDao bookDao) {
        System.out.println("📝 Örnek veriler yükleniyor...");

        try {
            // Sample Authors
            Author author1 = new Author(1, "Orhan", "Pamuk", LocalDate.of(1952, 6, 7));
            Author author2 = new Author(2, "Yaşar", "Kemal", LocalDate.of(1923, 10, 6));
            Author author3 = new Author(3, "Sabahattin", "Ali", LocalDate.of(1907, 2, 25));
            Author author4 = new Author(4, "Nazim", "Hikmet", LocalDate.of(1902, 1, 15));
            Author author5 = new Author(5, "Aziz", "Nesin", LocalDate.of(1915, 12, 20));

            authorDao.addAuthor(author1);
            authorDao.addAuthor(author2);
            authorDao.addAuthor(author3);
            authorDao.addAuthor(author4);
            authorDao.addAuthor(author5);

            // Sample Books
            Book book1 = new Book("Kar", "978-975-13-0123-4", author1);
            Book book2 = new Book("Benim Adım Kırmızı", "978-975-13-0124-5", author1);
            Book book3 = new Book("İnce Memed", "978-975-13-0125-6", author2);
            Book book4 = new Book("Kürk Mantolu Madonna", "978-975-13-0126-7", author3);
            Book book5 = new Book("Şu Çılgın Türkler", "978-975-13-0127-8", author4);
            Book book6 = new Book("Zübük", "978-975-13-0128-9", author5);

            bookDao.addBook(book1);
            bookDao.addBook(book2);
            bookDao.addBook(book3);
            bookDao.addBook(book4);
            bookDao.addBook(book5);
            bookDao.addBook(book6);

            System.out.println("✅ Örnek veriler başarıyla yüklendi!");
            System.out.println("📊 Yüklenen veriler:");
            System.out.println("   • " + authorDao.count() + " yazar");
            System.out.println("   • " + bookDao.count() + " kitap");
            System.out.println();

        } catch (Exception e) {
            System.out.println("⚠️ Örnek veriler yüklenirken bir hata oluştu: " + e.getMessage());
            System.out.println("💡 Sistem boş verilerle başlatılacak.\n");
        }
    }
}