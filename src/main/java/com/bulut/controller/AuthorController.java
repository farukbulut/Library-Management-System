package com.bulut.controller;

import com.bulut.dao.AuthorDao;
import com.bulut.model.Author;
import com.bulut.ui.InputManager;

import java.time.LocalDate;
import java.util.List;

public class AuthorController {
    private final AuthorDao authorDao;

    // Constructor - Dependency Injection
    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public void addAuthor() {
        System.out.println("\n--- YENİ YAZAR EKLEME ---");

        Author author = authorInput();
        try {
            this.authorDao.addAuthor(author);
            System.out.println("✅ Yazar başarıyla eklendi!");
        } catch (Exception e) {
            System.out.println("❌ Yazar eklenirken hata oluştu: " + e.getMessage());
        }
    }

    public void updateAuthor() {
        System.out.println("\n--- YAZAR GÜNCELLEME ---");
        System.out.print("Güncellenecek Yazar ID: ");
        int id = InputManager.getValidIntInput();

        // Mevcut yazarı göster
        Author existingAuthor = authorDao.searchAuthorById(id);
        if (existingAuthor == null) {
            System.out.println("❌ Yazar bulunamadı!");
            return;
        }

        System.out.println("Mevcut bilgiler: " + existingAuthor.getName() + " " + existingAuthor.getLastname());

        // YENİ BİLGİLERİ AL (ID hariç)
        System.out.print("Yeni Adı: ");
        String name = InputManager.getValidStringInput();

        System.out.print("Yeni Soyadı: ");
        String lastname = InputManager.getValidStringInput();

        LocalDate birthdate = InputManager.getValidDateInput();

        // ID'yi eski tut, diğerlerini güncelle
        Author updatedAuthor = new Author(id, name, lastname, birthdate);
        authorDao.update(updatedAuthor);
    }

    public void deleteAuthor() {
        System.out.println("\n--- YAZAR SİLME ---");
        System.out.print("Güncellenecek Yazar ID: ");
        int id = InputManager.getValidIntInput();
        authorDao.delete(id);
    }

    public void listAllAuthors() {
        System.out.println("\n--- YAZAR LİSTESİ ---");

        List<Author> authors = authorDao.getAllAuthors();

        if (authors.isEmpty()) {
            System.out.println("📋 Henüz hiç yazar eklenmemiş.");
            return;
        }

        System.out.println("\n" + "=".repeat(50));
        for (int i = 0; i < authors.size(); i++) {
            Author author = authors.get(i);
            System.out.printf("%-3d | %-20s | %s%n",
                    (author.getId()),
                    author.getName() + " " + author.getLastname(),
                    author.getBirthDate());
        }
        System.out.println("=".repeat(50));
        System.out.println("Toplam " + authors.size() + " yazar bulundu.");
    }

    public Author authorInput(){
        System.out.print("Yazar ID: ");
        int id = InputManager.getValidIntInput();

        System.out.print("Yazar Adı: ");
        String name = InputManager.getValidStringInput();

        System.out.print("Yazar Soyadı: ");
        String lastname = InputManager.getValidStringInput();

        LocalDate birthdate = InputManager.getValidDateInput();

        return  new Author(id, name, lastname, birthdate);
    }

    public void searchAuthors() {
        System.out.println("\n--- YAZAR ARAMA ---");
        System.out.print("Aranacak yazar adı/soyadı (boş bırakırsanız tüm yazarlar listelenir): ");
        String searchTerm = InputManager.getValidStringInput();

        List<Author> authors = this.authorDao.findByNameContaining(searchTerm);

        if (authors.isEmpty()) {
            System.out.println("📋 Arama kriterlerine uygun yazar bulunamadı.");
            return;
        }

        System.out.println("\nArama Sonuçları:");
        System.out.println("=".repeat(70));
        System.out.printf("%-5s | %-20s | %-20s | %s%n", "ID", "AD", "SOYAD", "DOĞUM TARİHİ");
        System.out.println("=".repeat(70));

        for (Author author : authors) {
            System.out.printf("%-5d | %-20s | %-20s | %s%n",
                    author.getId(),
                    author.getName(),
                    author.getLastname(),
                    author.getBirthDate());
        }

        System.out.println("=".repeat(70));
        System.out.println("Toplam " + authors.size() + " yazar bulundu.");
    }
}
