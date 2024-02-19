package org.example.javasuitejavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private String url;
    private String user;
    private String password;

    public Config() {
        this.url = "jdbc:postgresql://localhost:5432/javasuite";
        this.user = "jomiad";
        this.password = "JavaSuite";
    }

    public Config(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Get methods for url, user, and password
    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
