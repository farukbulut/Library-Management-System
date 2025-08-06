package com.bulut.dao;

import com.bulut.model.Author;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

    private List<Author> authors = new ArrayList<>();
    public void addAuthor(String name, String lastname, LocalDate birthDate){
        Author author = new Author(name, lastname, birthDate);
        authors.add(author);
    }

    public List<Author> getAllAuthors(){
        return authors;
    }

    public Author searchAuthor(String name){
        return authors.stream()
                .filter(author -> author.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
