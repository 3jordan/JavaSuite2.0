package org.example.javasuitejavafx;

import java.util.ArrayList;

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

    public static ArrayList<String> contactList() {
        // returns an array of contacts
        ArrayList list = new ArrayList<>();

        // querys go here



        return list;
    }



}

// delete

// update



