package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for database queries related to customers objects
 */
public class customersDAO {

    /**
     * This static method returns a list of customers objects, joined with first level divisionID, division name,
     * countryID, and country name.
     * @return a list of customers objects
     */
    public static ObservableList<customers> getAllCustomers() {

        ObservableList<customers> customersList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM customers, first_level_divisions, countries " +
                    "WHERE customers.Division_ID = first_level_divisions.Division_ID " +
                    "AND first_level_divisions.Country_ID = countries.Country_ID " +
                    "ORDER BY customers.Customer_ID";

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

    /**
     * This method inserts a single customer into the database.
     * @param customerName
     * @param address
     * @param postal
     * @param phone
     * @param firstDivisionID
     */
    public static void addCustomer(String customerName, String address, String postal, String phone, int firstDivisionID) {
        try {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";
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

    /**
     * This method deletes a customer from the database, based on customeriD.
     * @param customerID
     */
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

    /**
     * This method updates a single customer record in the database, based on customerID.
     * @param customerID
     * @param customerName
     * @param customerAddress
     * @param customerPostalCode
     * @param customerPhone
     * @param divisionID
     */
    public static void updateCustomer(int customerID, String customerName, String customerAddress, String customerPostalCode,
                                      String customerPhone, int divisionID) {
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement psUPDATE = JDBC.JDBCconnection.prepareStatement(sql);
            psUPDATE.setString(1, customerName);
            psUPDATE.setString(2, customerAddress);
            psUPDATE.setString(3, customerPostalCode);
            psUPDATE.setString(4, customerPhone);
            psUPDATE.setInt(5, divisionID);
            psUPDATE.setInt(6, customerID);
            psUPDATE.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to set a value for a combo box in the customers screen and the appointments screen
     * @param divisionID
     * @return division name
     */
    public static String getDivisionName(int divisionID) {
        try {
            String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = " + divisionID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            String divisionName = rs.getString("Division");
            return divisionName;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to set a value for a combo box in the customers screen and the appointments screen
     * @param divisionID
     * @return countryID
     */
    public static int getCountryID(int divisionID) {
        try {
            String sql = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = " + divisionID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            return rs.getInt("Country_ID");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to set a value in the appointments screen combo box
     * @param customerID
     * @return returns customer name
     */
    public static String getCustomerName(int customerID) {
        try {
            String sql = "SELECT Customer_Name FROM customers WHERE Customer_ID = " + customerID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            return rs.getString("Customer_Name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to set a value in the appointments screen combo box
     * @param customerID
     * @return returns customer address
     */
    public static String getAddress(int customerID) {
        try {
            String sql = "SELECT Address FROM customers WHERE Customer_ID = " + customerID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            return rs.getString("Address");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to set a value in the appointments screen combo box
     * @param customerID
     * @return customer postal code
     */
    public static String getPostal(int customerID) {
        try {
            String sql = "SELECT Postal_Code FROM customers WHERE Customer_ID = " + customerID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            return rs.getString("Postal_Code");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to set a value in the appointments screen combo box
     * @param customerID
     * @return customer phone number
     */
    public static String getPhone(int customerID) {
        try {
            String sql = "SELECT Phone FROM customers WHERE Customer_ID = " + customerID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            return rs.getString("Phone");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to set a value in the appointments screen combo box
     * @param customerID
     * @return customer divisionID
     * @throws SQLException
     */
    public static int getDivisionID(int customerID) throws SQLException{
        String sql = "SELECT Division_ID FROM customers WHERE Customer_ID = " + customerID;
        PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
        ResultSet rs = psSELECT.executeQuery();
        rs.next();
        return rs.getInt("Division_ID");
    }


}
