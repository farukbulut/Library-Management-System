package com.bulut.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputManager {

    private static final Scanner scanner = new Scanner(System.in);
    // Yardımcı metodlar

    public static int getValidIntInput() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Buffer temizle
                return input;
            } catch (Exception e) {
                System.out.print("❌ Geçersiz giriş! Lütfen bir sayı giriniz: ");
                scanner.nextLine(); // Hatalı girişi temizle
            }
        }
    }

    public static String getValidStringInput() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.print("❌ Boş bırakılamaz! Lütfen tekrar giriniz: ");
        }
    }

    public static LocalDate getValidDateInput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.print("Doğum Tarihi (dd-MM-yyyy): ");
            String dateInput = scanner.nextLine().trim();

            try {
                LocalDate date = LocalDate.parse(dateInput, formatter);

                // Gelecek tarih kontrolü
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("❌ Doğum tarihi gelecekte olamaz!");
                    continue;
                }

                // Çok eski tarih kontrolü (örn: 1800'den önce)
                if (date.isBefore(LocalDate.of(1800, 1, 1))) {
                    System.out.println("❌ Geçersiz doğum tarihi!");
                    continue;
                }

                return date;

            } catch (DateTimeParseException e) {
                System.out.println("❌ Geçersiz tarih formatı! Lütfen dd-MM-yyyy formatında giriniz. (Örnek: 25-12-1990)");
            }
        }
    }
}
