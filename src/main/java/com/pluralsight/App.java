
package com.pluralsight;

import java.sql.*;

public class App {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String username = args[0];
        String password = args[1];

        //1. open a connection to the database
        Connection connection = DriverManager.getConnection(url, username, password);


        // define your query
        String query = """
                        SELECT title, description, release_year, length
                        FROM film
                        WHERE title LIKE ?
                        """;

        // create statement
        // the statement is tied to the open connection
        PreparedStatement statement = connection.prepareStatement(query);

        String searchTerm = "%AIR%";
        //set parameters
        statement.setString(1,  searchTerm);

        // 2. Execute your query
        ResultSet results = statement.executeQuery();

        // process the results
        while (results.next()) {
            String title = results.getString("title");
            String description = results.getString("description");
            int releaseYear = results.getInt("release_year");
            int length = results.getInt("length");

            System.out.println(title);
            System.out.println(description);
            System.out.println(releaseYear);
            System.out.println(length);
            System.out.println("-----------------------");
        }

        results.close();
        statement.close();
        connection.close();

    }
}
