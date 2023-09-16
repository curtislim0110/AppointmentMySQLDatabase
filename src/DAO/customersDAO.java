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
            ResultSet rs = ps.executeQuery();

            // Loop through combined table rows to load an observable list with customer objects
            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postal = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int firstDivisionID = rs.getInt("Division_ID");
                String firstDivisionName = rs.getString("Division");

                customers currentcustomer = new customers(customerID, customerName, address,
                        postal, phone, firstDivisionID, firstDivisionName);

                customersList.add(currentcustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersList;
    }
}
