package com.bulut.controller;

import com.bulut.dao.BookDao;
import com.bulut.model.Book;
import com.bulut.ui.InputManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LoanController {
    private BookDao bookDao;
    private BookController bookController;

    public LoanController(BookDao bookDao, BookController bookController) {
        this.bookDao = bookDao;
        this.bookController = bookController;
    }

    public void loanBook() {
        System.out.println("\n--- KİTAP ÖDÜNÇ VERME ---");

        // Mevcut kitapları göster
        bookController.listAllBooks();

        if (bookDao.getAllBooks().isEmpty()) {
            System.out.println("📋 Ödünç verilebilecek kitap bulunmamaktadır.");
            return;
        }

        System.out.print("Ödünç verilecek kitabın ISBN'i: ");
        String isbn = InputManager.getValidStringInput();

        Book book = bookDao.searchBookIsbn(isbn);
        if (book == null) {
            System.out.println("❌ Bu ISBN'e sahip kitap bulunamadı!");
            return;
        }

        // Kitap zaten ödünçte mi kontrol et
        if (book.isBorrowed()) {
            System.out.println("❌ Bu kitap zaten ödünç verilmiş!");
            System.out.println("📅 Ödünç verilme tarihi: " +
                    book.getLoanDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            return;
        }

        // Ödünç verme işlemi
        book.setBorrowed(true);
        book.setLoanDate(LocalDate.now());

        System.out.println("✅ Kitap başarıyla ödünç verildi!");
        System.out.println("📖 Kitap: " + book.getTitle());
        System.out.println("✍️ Yazar: " + book.getAuthor().getName() + " " + book.getAuthor().getLastname());
        System.out.println("📅 Ödünç verme tarihi: " +
                book.getLoanDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    public void returnBook() {
        System.out.println("\n--- KİTAP İADE ALMA ---");

        // Ödünçte olan kitapları göster
        listLoanedBooks();

        List<Book> loanedBooks = bookDao.getAllBooks().stream()
                .filter(Book::isBorrowed)
                .toList();

        if (loanedBooks.isEmpty()) {
            System.out.println("📋 İade edilecek ödünçte kitap bulunmamaktadır.");
            return;
        }

        System.out.print("İade edilecek kitabın ISBN'i: ");
        String isbn = InputManager.getValidStringInput();

        Book book = bookDao.searchBookIsbn(isbn);
        if (book == null) {
            System.out.println("❌ Bu ISBN'e sahip kitap bulunamadı!");
            return;
        }

        // Kitap ödünçte mi kontrol et
        if (!book.isBorrowed()) {
            System.out.println("❌ Bu kitap zaten ödünçte değil!");
            return;
        }

        // İade işlemi
        LocalDate loanDate = book.getLoanDate();
        book.setBorrowed(false);
        book.setLoanDate(null);

        // Kaç gün ödünçte kaldığını hesapla
        long daysLoaned = java.time.temporal.ChronoUnit.DAYS.between(loanDate, LocalDate.now());

        System.out.println("✅ Kitap başarıyla iade alındı!");
        System.out.println("📖 Kitap: " + book.getTitle());
        System.out.println("✍️ Yazar: " + book.getAuthor().getName() + " " + book.getAuthor().getLastname());
        System.out.println("📅 Ödünç verilme tarihi: " +
                loanDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println("📅 İade tarihi: " +
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println("⏱️ Toplam ödünç süresi: " + daysLoaned + " gün");

        // Gecikme kontrolü (örnek olarak 14 gün limit)
        if (daysLoaned > 14) {
            System.out.println("⚠️ Kitap " + (daysLoaned - 14) + " gün geç iade edildi!");
        }
    }

    public void listLoanedBooks() {
        System.out.println("\n--- ÖDÜNÇTE OLAN KİTAPLAR ---");

        List<Book> loanedBooks = bookDao.getAllBooks().stream()
                .filter(Book::isBorrowed)
                .toList();

        if (loanedBooks.isEmpty()) {
            System.out.println("📋 Şu anda ödünçte hiç kitap bulunmamaktadır.");
            return;
        }

        System.out.println("\n" + "=".repeat(110));
        System.out.printf("%-15s | %-25s | %-20s | %-15s | %s%n",
                "ISBN", "BAŞLIK", "YAZAR", "ÖDÜNÇ TARİHİ", "DURUM");
        System.out.println("=".repeat(110));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (Book book : loanedBooks) {
            long daysLoaned = java.time.temporal.ChronoUnit.DAYS.between(
                    book.getLoanDate(), LocalDate.now());

            String overdueInfo;
            if (daysLoaned > 14) {
                overdueInfo = "⚠️ " + (daysLoaned - 14) + " gün gecikme";
            } else {
                long remainingDays = 14 - daysLoaned;
                overdueInfo = "✅ " + remainingDays + " gün kaldı";
            }

            System.out.printf("%-15s | %-25s | %-20s | %-15s | %s%n",
                    book.getIsbn(),
                    book.getTitle().length() > 25 ? book.getTitle().substring(0, 22) + "..." : book.getTitle(),
                    (book.getAuthor().getName() + " " + book.getAuthor().getLastname()).length() > 20 ?
                            (book.getAuthor().getName() + " " + book.getAuthor().getLastname()).substring(0, 17) + "..." :
                            (book.getAuthor().getName() + " " + book.getAuthor().getLastname()),
                    book.getLoanDate().format(formatter),
                    overdueInfo);
        }

        System.out.println("=".repeat(110));
        System.out.println("Toplam " + loanedBooks.size() + " kitap ödünçte bulunmaktadır.");

        // Geciken kitap sayısı
        long overdueCount = loanedBooks.stream()
                .mapToLong(book -> java.time.temporal.ChronoUnit.DAYS.between(book.getLoanDate(), LocalDate.now()))
                .filter(days -> days > 14)
                .count();

        if (overdueCount > 0) {
            System.out.println("⚠️ " + overdueCount + " kitap gecikme ile iade edilmeyi bekliyor.");
        }
    }

    public void checkBookStatus() {
        System.out.println("\n--- KİTAP DURUM SORGULAMA ---");

        System.out.print("Sorgulanacak kitabın ISBN'i: ");
        String isbn = InputManager.getValidStringInput();

        Book book = bookDao.searchBookIsbn(isbn);
        if (book == null) {
            System.out.println("❌ Bu ISBN'e sahip kitap bulunamadı!");
            return;
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("KİTAP BİLGİLERİ");
        System.out.println("=".repeat(50));
        System.out.println("📖 Başlık: " + book.getTitle());
        System.out.println("✍️ Yazar: " + book.getAuthor().getName() + " " + book.getAuthor().getLastname());
        System.out.println("📚 ISBN: " + book.getIsbn());

        if (book.isBorrowed()) {
            System.out.println("📊 Durum: ❌ ÖDÜNÇTE");
            System.out.println("📅 Ödünç verme tarihi: " +
                    book.getLoanDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

            long daysLoaned = java.time.temporal.ChronoUnit.DAYS.between(
                    book.getLoanDate(), LocalDate.now());
            System.out.println("⏱️ Ödünçte geçen süre: " + daysLoaned + " gün");

            if (daysLoaned > 14) {
                System.out.println("⚠️ GECİKME: " + (daysLoaned - 14) + " gün gecikmiş!");
            } else {
                System.out.println("✅ İade tarihi henüz geçmemiş (" + (14 - daysLoaned) + " gün kaldı)");
            }
        } else {
            System.out.println("📊 Durum: ✅ MEVCUT (Ödünç verilebilir)");
        }
        System.out.println("=".repeat(50));
    }
}