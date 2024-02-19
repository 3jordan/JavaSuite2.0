package org.example.javasuitejavafx;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Contact {
    String name;
    String email;
    String phone;
    boolean isFavorite;

    public Contact(String name, String email, String phone, boolean isFavorite) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isFavorite = isFavorite;
    }

    public static void main(String[] args) {
        Config config = new Config();
        Scanner scanner = new Scanner(System.in);

        createContact(scanner, config);

        scanner.close();
    }

    public static void createContact(Scanner scanner, Config config) {
        System.out.println("Enter contact details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Is favorite? (true/false): ");
        boolean isFavorite = scanner.nextBoolean();
        scanner.nextLine();

        Contact newContact = new Contact(name, email, phone, isFavorite);

        insertContactIntoDatabase(newContact, config);
    }

    private static void insertContactIntoDatabase(Contact contact, Config config) {
        String sqlStatement = "INSERT INTO contacts (name, email, phone, is_favorite) VALUES (?, ?, ?, ?);";

        try (Connection connection = config.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement);
             PrintWriter writer = new PrintWriter(new FileWriter("sql/schema.sql", true))) {

            statement.setString(1, contact.name);
            statement.setString(2, contact.email);
            statement.setString(3, contact.phone);
            statement.setBoolean(4, contact.isFavorite);

            int rowsAffected = statement.executeUpdate();

            String executedSqlStatement = statement.toString();
            writer.println(executedSqlStatement);
            System.out.println("SQL statement written to schema.sql successfully!");

            if (rowsAffected > 0) {
                System.out.println("Contact added successfully!");
            } else {
                System.err.println("Failed to add contact.");
            }
        } catch (SQLException | IOException e) {
            System.err.println("Error adding contact: " + e.getMessage());
        }
    }
}
