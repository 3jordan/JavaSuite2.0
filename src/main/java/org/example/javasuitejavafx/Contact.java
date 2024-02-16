package org.example.javasuitejavafx;

public class Contact {
    String name;
    String email;
    String phone;
    boolean isFavorite;
    Contact(String name, String email, String phone, boolean isFavorite) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isFavorite = isFavorite;
    }

    public static void createContact(String name, String email, String phone, boolean isFavorite) {
        Contact newContact = new Contact(name, email, phone, isFavorite);
        // find a way to add this to PostgreSQL table
    }

    public static Object[] contactList() {
        // returns an array of contacts
        return null;
    }
}

