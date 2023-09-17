package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class customersDAO {

    public static ObservableList<customers> getAllCustomers() {

        ObservableList<customers> customersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM customers, first_level_divisions, countries " +
                    "WHERE customers.Division_ID = first_level_divisions.Division_ID " +
                    "AND first_level_divisions.Country_ID = countries.Country_ID";

            PreparedStatement ps = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rsGetAll = ps.executeQuery();

            // Loop through combined table rows to load an observable list with customer objects
            while (rsGetAll.next()) {
                int customerID = rsGetAll.getInt("Customer_ID");
                String customerName = rsGetAll.getString("Customer_Name");
                String address = rsGetAll.getString("Address");
                String postal = rsGetAll.getString("Postal_Code");
                String phone = rsGetAll.getString("Phone");
                int firstDivisionID = rsGetAll.getInt("Division_ID");
                String firstDivisionName = rsGetAll.getString("Division");
                int countryID = rsGetAll.getInt("Country_ID");
                String countryName = rsGetAll.getString("Country");

                customers currentcustomer = new customers(customerID, customerName, address,
                        postal, phone, firstDivisionID, firstDivisionName, countryID, countryName);

                customersList.add(currentcustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersList;
    }

    public static void addCustomer(String customerName, String address, String postal, String phone, int firstDivisionID) {
        try {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Last_Update, Division_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psINSERT = JDBC.JDBCconnection.prepareStatement(sql);
            psINSERT.setString(1, customerName);
            psINSERT.setString(2, address);
            psINSERT.setString(3, postal);
            psINSERT.setString(4, phone);
            psINSERT.setInt(5, firstDivisionID);
            psINSERT.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(int customerID) {
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement psDELETE = JDBC.JDBCconnection.prepareStatement(sql);
            psDELETE.setInt(1, customerID);
            psDELETE.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
