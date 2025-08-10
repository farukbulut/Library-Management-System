package com.bulut.ui;

import com.bulut.controller.AuthorController;
import com.bulut.controller.BookController;
import com.bulut.controller.LoanController;
import com.bulut.dao.AuthorDao;
import com.bulut.dao.BookDao;

public class MenuManager {
    private AuthorController authorController;
    private BookController bookController;
    private LoanController loanController;

    public MenuManager(AuthorDao authorDao, BookDao bookDao){
        this.authorController = new AuthorController(authorDao);
        this.bookController = new BookController(bookDao, authorDao, this.authorController);
        this.loanController = new LoanController(bookDao, this.bookController);
    }

    public int showMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("           ANA MENÜ");
        System.out.println("=".repeat(40));
        System.out.println("1. Yazar İşlemleri");
        System.out.println("2. Kitap İşlemleri");
        System.out.println("3. Ödünç İşlemleri");
        System.out.println("4. Çıkış");
        System.out.println("=".repeat(40));
        System.out.print("Seçiminiz: ");

        int choice = InputManager.getValidIntInput();
        return choice;
    }

    public void handleMainMenu(int choice) {
        switch (choice) {
            case 1:
                authorOperations();
                break;
            case 2:
                bookOperations();
                break;
            case 3:
                loanOperations();
                break;
            case 4:
                break;
            default:
                System.out.println("❌ Geçersiz seçim! Lütfen 1-4 arasında bir değer giriniz.");
        }
    }

    private void authorOperations() {
        int choice;
        do {
            choice = showAuthorMenu();
            handleAuthorMenu(choice);
        } while (choice != 9);
    }

    private static int showAuthorMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         YAZAR İŞLEMLERİ");
        System.out.println("=".repeat(40));
        System.out.println("1. Yazar Ekle");
        System.out.println("2. Yazar Güncelle");
        System.out.println("3. Yazar Sil");
        System.out.println("4. Yazarları Listele");
        System.out.println("5. Yazar Ara");
        System.out.println("9. Ana Menüye Dön");
        System.out.println("=".repeat(40));
        System.out.print("Seçiminiz: ");

        return InputManager.getValidIntInput();
    }

    private void handleAuthorMenu(int choice) {
        switch (choice) {
            case 1:
                this.authorController.addAuthor();
                break;
            case 2:
                this.authorController.updateAuthor();
                break;
            case 3:
                this.authorController.deleteAuthor();
                break;
            case 4:
                this.authorController.listAllAuthors();
                break;
            case 5:
                this.authorController.searchAuthors();
                break;
            case 9:
                System.out.println("Ana menüye dönülüyor...");
                break;
            default:
                System.out.println("❌ Geçersiz seçim! Lütfen menüden bir seçim yapınız.");
        }
    }

    private void bookOperations() {
        int choice;
        do {
            choice = showBookMenu();
            handleBookMenu(choice);
        } while (choice != 9);
    }

    private static int showBookMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         KİTAP İŞLEMLERİ");
        System.out.println("=".repeat(40));
        System.out.println("1. Kitap Ekle");
        System.out.println("2. Kitap Güncelle");
        System.out.println("3. Kitap Sil");
        System.out.println("4. Kitap Listele");
        System.out.println("5. Kitap Ara");
        System.out.println("9. Ana Menüye Dön");
        System.out.println("=".repeat(40));
        System.out.print("Seçiminiz: ");

        return InputManager.getValidIntInput();
    }

    private void handleBookMenu(int choice) {
        switch (choice) {
            case 1:
                this.bookController.addBook();
                break;
            case 2:
                this.bookController.updateBook();
                break;
            case 3:
                this.bookController.deleteBook();
                break;
            case 4:
                this.bookController.listAllBooks();
                break;
            case 5:
                this.bookController.searchBooks();
                break;
            case 9:
                System.out.println("Ana menüye dönülüyor...");
                break;
            default:
                System.out.println("❌ Geçersiz seçim! Lütfen menüden bir seçim yapınız.");
        }
    }

    private void loanOperations() {
        int choice;
        do {
            choice = showLoanMenu();
            handleLoanMenu(choice);
        } while (choice != 9);
    }

    private static int showLoanMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         ÖDÜNÇ İŞLEMLERİ");
        System.out.println("=".repeat(40));
        System.out.println("1. Kitap Ödünç Ver");
        System.out.println("2. Kitap İade Al");
        System.out.println("3. Ödünçte Olan Kitapları Listele");
        System.out.println("4. Kitap Durumu Sorgula");
        System.out.println("9. Ana Menüye Dön");
        System.out.println("=".repeat(40));
        System.out.print("Seçiminiz: ");

        return InputManager.getValidIntInput();
    }

    private void handleLoanMenu(int choice) {
        switch (choice) {
            case 1:
                this.loanController.loanBook();
                break;
            case 2:
                this.loanController.returnBook();
                break;
            case 3:
                this.loanController.listLoanedBooks();
                break;
            case 4:
                this.loanController.checkBookStatus();
                break;
            case 9:
                System.out.println("Ana menüye dönülüyor...");
                break;
            default:
                System.out.println("❌ Geçersiz seçim! Lütfen menüden bir seçim yapınız.");
        }
    }
}