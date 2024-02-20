package org.example.javasuitejavafx;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


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
        TextField nameTextField = new TextField();
        TextField emailTextField = new TextField();
        TextField phoneTextField = new TextField();
        CheckBox favoriteCheckBox = new CheckBox();
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
                    createContact(nameTextField, emailTextField, phoneTextField, favoriteCheckBox, config);
                    break;
                case 2:
                    updateContact(scanner, config);
                    break;
                case 3:
                    deleteContact(scanner, config);
                    break;
                case 4:
                    List<Contact> contacts = allContacts();
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
    public static List<Contact> allContacts() {
        Config config = new Config();
        List<Contact> contacts = new ArrayList<>();
        String sqlStatement = "SELECT * FROM contacts";

        try (Connection connection = config.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                boolean isFavorite = resultSet.getBoolean("is_favorite");

                Contact contact = new Contact(name, email, phone, isFavorite);
                contacts.add(contact);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving contacts: " + e.getMessage());
        }

        return contacts;
    }
    public static void createContact(TextField nameField, TextField emailField, TextField phoneField, CheckBox favoriteCheckBox, Config config) {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        boolean isFavorite = favoriteCheckBox.isSelected();

        Contact newContact = new Contact(name, email, phone, isFavorite);

        insertContact(newContact, config);
    }


    private static void insertContact(Contact contact, Config config) {
        String sqlStatement = "INSERT INTO contacts (name, email, phone, is_favorite) VALUES (?, ?, ?, ?);";

        try (Connection connection = config.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement);
             PrintWriter writer = new PrintWriter(new FileWriter("sql/schema.sql", true))) {

            statement.setString(1, contact.name);
            statement.setString(2, contact.email);
            statement.setString(3, contact.phone);
            statement.setBoolean(4, contact.isFavorite);

            int rowsAffected = statement.executeUpdate();

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
        scanner.nextLine();

        Contact updatedContact = new Contact(updatedName, updatedEmail, updatedPhone, updatedIsFavorite);

        updateContact(oldName, updatedContact, config);
    }

    public static void deleteContact(Scanner scanner, Config config) {
        System.out.print("Enter the name of the contact you want to delete: ");
        String name = scanner.nextLine();

        deleteContact(name, config);
    }

    private static void updateContact(String oldName, Contact updatedContact, Config config) {
        String sqlStatement = "UPDATE contacts SET name = ?, email = ?, phone = ?, is_favorite = ? WHERE name = ?;";

        try (Connection connection = config.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlStatement);
             PrintWriter writer = new PrintWriter(new FileWriter("sql/schema.sql", true))) {

            statement.setString(1, updatedContact.name);
            statement.setString(2, updatedContact.email);
            statement.setString(3, updatedContact.phone);
            statement.setBoolean(4, updatedContact.isFavorite);
            statement.setString(5, oldName); // Where clause for update

            int rowsAffected = statement.executeUpdate();

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

            statement.setString(1, name);

            int rowsAffected = statement.executeUpdate();

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
