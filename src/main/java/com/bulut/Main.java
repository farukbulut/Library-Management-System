package com.bulut;

import com.bulut.dao.AuthorDao;
import com.bulut.dao.BookDao;
import com.bulut.model.Author;
import com.bulut.model.Book;
import com.bulut.ui.MenuManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== KÜTÜPHANE YÖNETİM SİSTEMİ ===");
        MenuManager menu = new MenuManager();
        int choice;
        do {
            choice = menu.showMainMenu();
            menu.handleMainMenu(choice);
        } while (choice != 4);

        System.out.println("Sistem kapatılıyor... İyi günler!");
    }
}