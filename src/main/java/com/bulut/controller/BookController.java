package com.bulut.controller;

import com.bulut.dao.AuthorDao;
import com.bulut.dao.BookDao;
import com.bulut.model.Author;
import com.bulut.model.Book;
import com.bulut.ui.InputManager;

import java.time.LocalDate;
import java.util.List;

public class BookController {
    private BookDao bookDao;
    private AuthorDao authorDao;
    private AuthorController authorController;

    public BookController(BookDao bookDao, AuthorDao authorDao, AuthorController authorController){
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.authorController = authorController;
    }

    public void addBook() {
        System.out.println("\n--- YENİ Kitap EKLEME ---");

        authorController.listAllAuthors();
        Author author = null;

        while (author == null){
            author = authorInput();
            if (author == null) {
                System.out.println("❌ Yazar bulunamadı! Lütfen geçerli bir yazar ID'si girin.");
            }
        }

        Book book = bookInput(author);

        try {
            this.bookDao.addBook(book);
        } catch (Exception e) {
            System.out.println("❌ Kitap eklenirken hata oluştu: " + e.getMessage());
        }
    }

    private Book bookInput(Author author){
        System.out.print("Kitap Adı: ");
        String title = InputManager.getValidStringInput();

        System.out.print("Kitap İsbn: ");
        String isbn = InputManager.getValidStringInput();

        return new Book(title, isbn, author);
    }

    private Author authorInput(){
        System.out.print("Yazar ID: ");
        int id = InputManager.getValidIntInput();
        Author author = this.authorDao.searchAuthorById(id);

        if (author != null) {
            System.out.println("✅ Yazar bulundu: " + author.getName());
        }

        return author;
    }

    public void listAllBooks() {
        System.out.println("\n--- Kitap LİSTESİ ---");

        List<Book> books = bookDao.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("📋 Henüz hiç bir kitap eklenmemiş.");
            return;
        }

        System.out.println("\n" + "=".repeat(85));
        System.out.printf("%-15s | %-25s | %-25s | %s%n", "ISBN", "BAŞLIK", "YAZAR", "DURUM");
        System.out.println("=".repeat(85));

        for (Book book : books) {
            String status = book.isBorrowed() ? "❌ Ödünçte" : "✅ Mevcut";
            System.out.printf("%-15s | %-25s | %-25s | %s%n",
                    book.getIsbn(),
                    book.getTitle().length() > 25 ? book.getTitle().substring(0, 22) + "..." : book.getTitle(),
                    (book.getAuthor().getName() + " " + book.getAuthor().getLastname()).length() > 25 ?
                            (book.getAuthor().getName() + " " + book.getAuthor().getLastname()).substring(0, 22) + "..." :
                            book.getAuthor().getName() + " " + book.getAuthor().getLastname(),
                    status);
        }
        System.out.println("=".repeat(85));
        System.out.println("Toplam " + books.size() + " kitap bulundu.");

        // Ödünç istatistikleri
        long loanedCount = books.stream().filter(Book::isBorrowed).count();
        long availableCount = books.size() - loanedCount;
        System.out.println("📊 Mevcut: " + availableCount + " | Ödünçte: " + loanedCount);
    }

    public void deleteBook() {
        listAllBooks();
        System.out.println("\n--- Kitap SİLME ---");
        System.out.print("Silinecek Kitap ISBN: ");
        String isbn = InputManager.getValidStringInput();

        // Ödünçte olan kitap silinmesin
        Book book = bookDao.searchBookIsbn(isbn);
        if (book != null && book.isBorrowed()) {
            System.out.println("❌ Ödünçte olan kitap silinemez! Önce iade alınmalıdır.");
            return;
        }

        bookDao.delete(isbn);
    }

    public void updateBook() {
        System.out.println("\n--- KİTAP GÜNCELLEME ---");
        System.out.print("Güncellenecek Kitap ISBN: ");
        String isbn = InputManager.getValidStringInput();

        // Mevcut kitabı göster
        Book existingBook = bookDao.searchBookIsbn(isbn);
        if (existingBook == null) {
            System.out.println("❌ Kitap bulunamadı!");
            return;
        }

        // Ödünçte olan kitap güncellenmesin (isteğe bağlı)
        if (existingBook.isBorrowed()) {
            System.out.println("⚠️ Bu kitap şu anda ödünçte. Yine de güncellemek istiyor musunuz? (E/H)");
            String confirm = InputManager.getValidStringInput().toUpperCase();
            if (!confirm.equals("E") && !confirm.equals("EVET")) {
                System.out.println("Güncelleme iptal edildi.");
                return;
            }
        }

        System.out.println("Mevcut bilgiler: " + existingBook.getTitle());

        // YENİ BİLGİLERİ AL (ISBN hariç)
        System.out.print("Yeni Başlık: ");
        String title = InputManager.getValidStringInput();

        Book book = new Book(title, existingBook.getIsbn(), existingBook.getAuthor());
        // Ödünç durumunu koru
        book.setBorrowed(existingBook.isBorrowed());
        book.setLoanDate(existingBook.getLoanDate());

        bookDao.update(book);
    }

    public void searchBooks() {
        System.out.println("\n--- KİTAP ARAMA ---");
        System.out.print("Aranacak kitap başlığı (boş bırakırsanız tüm kitaplar listelenir): ");
        String searchTerm = InputManager.getValidStringInput();

        List<Book> books = bookDao.findByNameContaining(searchTerm);

        if (books.isEmpty()) {
            System.out.println("📋 Arama kriterlerine uygun kitap bulunamadı.");
            return;
        }

        System.out.println("\nArama Sonuçları:");
        System.out.println("=".repeat(100));
        System.out.printf("%-15s | %-30s | %-25s | %s%n",
                "ISBN", "BAŞLIK", "YAZAR", "DURUM");
        System.out.println("=".repeat(100));

        for (Book book : books) {
            String status = book.isBorrowed() ? "❌ Ödünçte" : "✅ Mevcut";
            System.out.printf("%-15s | %-30s | %-25s | %s%n",
                    book.getIsbn(),
                    book.getTitle().length() > 30 ? book.getTitle().substring(0, 27) + "..." : book.getTitle(),
                    (book.getAuthor().getName() + " " + book.getAuthor().getLastname()).length() > 25 ?
                            (book.getAuthor().getName() + " " + book.getAuthor().getLastname()).substring(0, 22) + "..." :
                            book.getAuthor().getName() + " " + book.getAuthor().getLastname(),
                    status);
        }

        System.out.println("=".repeat(100));
        System.out.println("Toplam " + books.size() + " kitap bulundu.");
    }
}