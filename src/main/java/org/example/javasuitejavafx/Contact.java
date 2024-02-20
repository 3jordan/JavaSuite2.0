package org.example.javasuitejavafx;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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

        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Create contact");
            System.out.println("2. Update contact");
            System.out.println("3. Delete contact");
            System.out.println("4. Show all contacts");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createContact(scanner, config);
                    break;
                case 2:
                    updateContact(scanner, config);
                    break;
                case 3:
                    deleteContact(scanner, config);
                    break;
                case 4:
                    allContacts(config);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                    break;
            }
        }
        scanner.close();
    }
    public static void allContacts(Config config) {
        String sqlStatement = "SELECT * FROM contacts";

        try (Connection connection = config.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("All contacts:");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                boolean isFavorite = resultSet.getBoolean("is_favorite");

                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                System.out.println("Is Favorite: " + isFavorite);
                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving contacts: " + e.getMessage());
        }
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

        insertContact(newContact, config);
    }

    private static void insertContact(Contact contact, Config config) {
        String sqlStatement = "INSERT INTO contacts (name, email, phone, is_favorite) VALUES (?, ?, ?, ?);";

        try (Connection connection = config.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement);
             PrintWriter writer = new PrintWriter(new FileWriter("sql/schema.sql", true))) {

            // Set values for parameters in the prepared statement
            statement.setString(1, contact.name);
            statement.setString(2, contact.email);
            statement.setString(3, contact.phone);
            statement.setBoolean(4, contact.isFavorite);

            // Execute the prepared statement
            int rowsAffected = statement.executeUpdate();

            // Write the executed SQL statement to schema.sql file
            writer.println(statement.toString());

            if (rowsAffected > 0) {
                System.out.println("Contact added successfully!");
            } else {
                System.err.println("Failed to add contact.");
            }
        } catch (SQLException | IOException e) {
            System.err.println("Error adding contact: " + e.getMessage());
        }
    }

    public static void updateContact(Scanner scanner, Config config) {
        System.out.print("Enter the name of the contact you want to update: ");
        String oldName = scanner.nextLine();

        System.out.println("Enter updated contact details:");
        System.out.print("Name: ");
        String updatedName = scanner.nextLine();
        System.out.print("Email: ");
        String updatedEmail = scanner.nextLine();
        System.out.print("Phone: ");
        String updatedPhone = scanner.nextLine();
        System.out.print("Is favorite? (true/false): ");
        boolean updatedIsFavorite = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        // Create a Contact object with updated details
        Contact updatedContact = new Contact(updatedName, updatedEmail, updatedPhone, updatedIsFavorite);

        // Now call the updateContact method with the old name and the updated contact object
        updateContact(oldName, updatedContact, config);
    }

    public static void deleteContact(Scanner scanner, Config config) {
        System.out.print("Enter the name of the contact you want to delete: ");
        String name = scanner.nextLine();

        // Now call the deleteContact method with the name of the contact to be deleted
        deleteContact(name, config);
    }

    private static void updateContact(String oldName, Contact updatedContact, Config config) {
        String sqlStatement = "UPDATE contacts SET name = ?, email = ?, phone = ?, is_favorite = ? WHERE name = ?;";

        try (Connection connection = config.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement);
             PrintWriter writer = new PrintWriter(new FileWriter("sql/schema.sql", true))) {

            // Set values for parameters in the prepared statement
            statement.setString(1, updatedContact.name);
            statement.setString(2, updatedContact.email);
            statement.setString(3, updatedContact.phone);
            statement.setBoolean(4, updatedContact.isFavorite);
            statement.setString(5, oldName); // Where clause for update

            // Execute the prepared statement
            int rowsAffected = statement.executeUpdate();

            // Write the executed SQL statement to schema.sql file
            writer.println(statement.toString());

            if (rowsAffected > 0) {
                System.out.println("Contact updated successfully!");
            } else {
                System.out.println("No contact found with the specified name.");
            }
        } catch (SQLException | IOException e) {
            System.err.println("Error updating contact: " + e.getMessage());
        }
    }

    private static void deleteContact(String name, Config config) {
        String sqlStatement = "DELETE FROM contacts WHERE name = ?;";

        try (Connection connection = config.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement);
             PrintWriter writer = new PrintWriter(new FileWriter("sql/schema.sql", true))) {

            // Set value for parameter in the prepared statement
            statement.setString(1, name);

            // Execute the prepared statement
            int rowsAffected = statement.executeUpdate();

            // Write the executed SQL statement to schema.sql file
            writer.println(statement.toString());

            if (rowsAffected > 0) {
                System.out.println("Contact deleted successfully!");
            } else {
                System.out.println("No contact found with the specified name.");
            }
        } catch (SQLException | IOException e) {
            System.err.println("Error deleting contact: " + e.getMessage());
        }
    }
}
