package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for database queries related to contacts objects
 */
public class contactsDAO {

    /**
     * This static method returns of list of all contacts in the database
     * @return list of contacts objects
     */
    public static ObservableList<contacts> getAllContacts() {
        ObservableList<contacts> currentcontactlist = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Contact_ID, Contact_Name, Email FROM contacts";
            PreparedStatement contacts = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = contacts.executeQuery();

            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                contacts currentcontact = new contacts(contactID, contactName, contactEmail);
                currentcontactlist.add(currentcontact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentcontactlist;
    }

    /**
     * This method returns a single contact name based on contact ID
     * @param contactID
     * @return contact name
     */
    public static String getContactName(int contactID) {
        try {
            String sql = "SELECT Contact_Name FROM contacts WHERE Contact_ID = " + contactID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            return rs.getString("Contact_Name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method returns a single contact email based on contact ID
     * @param contactID
     * @return contact ID
     */
    public static String getEmail(int contactID) {
        try {
            String sql = "SELECT Email FROM contacts WHERE Contact_ID = " + contactID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            return rs.getString("Email");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
