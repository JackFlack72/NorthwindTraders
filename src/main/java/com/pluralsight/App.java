
package com.pluralsight;

import java.sql.*;

public class App {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = args[0];
        String password = args[1];

        displayProducts(url, username, password);
        displayCustomers(url, username, password);

    }

    private static void displayProducts(String url, String username, String password) {
        String query = """
                SELECT productid, productname, unitprice, unitsinstock
                FROM products
                WHERE productname LIKE ?
                """;

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            String searchTerm = "a%";
            statement.setString(1, searchTerm);

            try (ResultSet results = statement.executeQuery()) {

                while (results.next()) {
                    String productId = results.getString("productid");
                    String name = results.getString("productname");
                    double price = results.getDouble("unitprice");
                    int stock = results.getInt("unitsinstock");

                    System.out.println("Product Id: " + productId);
                    System.out.println("Name: " + name);
                    System.out.println("Price: $" + price);
                    System.out.println("Stock: " + stock);
                    System.out.println("-----------------------");
                }
            }

        } catch (SQLException e) {
            System.out.println("There was an error retrieving the data. Please try again.");
            e.printStackTrace();
        }
    }

    private static void displayCustomers(String url, String username, String password) {
        String query = """
                SELECT CustomerID, CompanyName, ContactName, ContactTitle
                FROM customers
                WHERE ContactName LIKE ?
                """;

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            String searchTerm = "a%";
            statement.setString(1, searchTerm);

            try (ResultSet results = statement.executeQuery()) {

                while (results.next()) {
                    String customerId = results.getString("CustomerID");
                    String companyName = results.getString("CompanyName");
                    String contactName = results.getString("ContactName");
                    String contactTitle = results.getString("ContactTitle");

                    System.out.println("Customer Id: " + customerId);
                    System.out.println("Company Name: " + companyName);
                    System.out.println("Customer Name: " + contactName);
                    System.out.println("Customer Title: " + contactTitle);
                    System.out.println("-----------------------");
                }
            }

        } catch (SQLException e) {
            System.out.println("There was an error retrieving the data. Please try again.");
            e.printStackTrace();
        }
    }

}
