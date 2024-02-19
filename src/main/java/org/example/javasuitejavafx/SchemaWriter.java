package org.example.javasuitejavafx;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaWriter {
    private Config config;

    public SchemaWriter(Config config) {
        this.config = config;
    }

    public void writeToSchemaFile(String sqlStatement) {
        try (Connection connection = config.getConnection();
             Statement statement = connection.createStatement();
             PrintWriter writer = new PrintWriter(new FileWriter("schema.sql", true))) {

            // Execute the SQL statement
            statement.executeUpdate(sqlStatement);

            // Write the SQL statement to schema.sql file
            writer.println(sqlStatement);
            System.out.println("SQL statement written to schema.sql successfully!");
        } catch (SQLException | IOException e) {
            System.err.println("Error writing to schema.sql file: " + e.getMessage());
        }
    }
}
