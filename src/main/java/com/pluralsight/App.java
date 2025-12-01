package com.pluralsight;

import java.sql.*;

public class App {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "yearup";

        //1. open a connection to the database
        Connection connection = DriverManager.getConnection(url, username, password);

        // create statement
        // the statement is tied to the open connection
        Statement statement = connection.createStatement();

        // define your query
        String query = "SELECT ProductName FROM products";

        // 2. Execute your query
        ResultSet results = statement.executeQuery(query);

        // process the results
        while (results.next()) {
            String products = results.getString("ProductName");
            System.out.println(products);
        }

        // 3. Close the connection
        connection.close();

    }
}
