package org.example.javasuitejavafx;
import java.sql.*;

public class Config{
    private String url; // "jdbc:postgresql://localhost:5432/javasuite";
    private String user; // "jomiad";
    private String password; // "JavaSuite";
    public Config(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
