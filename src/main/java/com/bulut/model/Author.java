package com.bulut.model;

import java.time.LocalDate;

public class Author {
    private String name;
    private String lastname;
    private LocalDate birthDate;

    public Author(String name, String lastname, LocalDate birthDate) {
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
