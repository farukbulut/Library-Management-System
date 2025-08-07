package com.bulut.dao;

import com.bulut.model.Author;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

    private List<Author> authors = new ArrayList<>();
    public void addAuthor(Author author){
        if (searchAuthorById(author.getId()) == null){
            authors.add(author);
            System.out.println(author.getName() + " isimli yazar eklenmiştir");
        }else {
            System.out.println("❌ Hata ! Kayıt Eklenemedi " + author.getId() + " li yazar zaten kayıtlı");
        }
    }

    public List<Author> getAllAuthors(){
        return authors;
    }

    public Author searchAuthorById(int id){
        return authors.stream()
                .filter(author -> author.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void update(Author updatedAuthor, int old_id) {
        if (searchAuthorById(old_id) == null){
            System.out.println("❌ Yazar bulunamadı!");
            return;
        }
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId() == old_id) {
                authors.set(i, updatedAuthor); // Eski author yerine yeni author'u koy
                System.out.println("✅ " + updatedAuthor.getName() + " isimli yazar güncellendi!");
                return;
            }
        }
    }

    public void delete(int id){
        if (searchAuthorById(id) == null){
            System.out.println("❌ Yazar bulunamadı!");
            return;
        }
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId() == id) {
                String name = authors.get(i).getName();
                authors.remove(i); // Eski author yerine yeni author'u koy
                System.out.println("✅ " + name + " isimli yazar silindi!");
                return;
            }
        }
    }
}
