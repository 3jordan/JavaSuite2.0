package org.example.javasuitejavafx;

import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        scanner.nextLine(); // Consume newline left-over

        Contact newContact = new Contact(name, email, phone, isFavorite);

        // Write SQL statement to schema.sql
        String sqlStatement = "INSERT INTO contacts (name, email, phone, is_favorite) VALUES ('" +
                newContact.name + "', '" + newContact.email + "', '" +
                newContact.phone + "', " + newContact.isFavorite + ")";
        writeToSchemaFile(sqlStatement, config);
    }

    public static ArrayList<String> contactList() {
        // returns an array of contacts
        ArrayList<String> list = new ArrayList<>();

        // queries go here

        return list;
    }

    private static void writeToSchemaFile(String sqlStatement, Config config) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("schema.sql", true));
             Connection connection = config.getConnection();
             Statement statement = connection.createStatement()) {

            // Execute the SQL statement
            statement.executeUpdate(sqlStatement);

            // Write the SQL statement to schema.sql file
            writer.println(sqlStatement);
            System.out.println("Contact added successfully!");
        } catch (SQLException | IOException e) {
            System.err.println("Error adding contact: " + e.getMessage());
        }
    }
}
