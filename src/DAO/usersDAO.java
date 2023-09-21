package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class supports data access for model user objects.  This includes getting a list of all users and user
 * login verification
 */
public class usersDAO {

    /**
     * This static method returns a list of all users in the database.
     * @return returns a list of all users
     */
    public static ObservableList<users> getUsersList() {
        ObservableList<users> allusersList = FXCollections.observableArrayList();
        try {
            // open database connection and select all users with SQL statement
            String sql = "SELECT * FROM users";
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();

            // load list of user id, username, and password from Users table
            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                String username = rs.getString("User_Name");
                String password = rs.getString("Password");

                users currentuser = new users(userID, username, password);
                allusersList.add(currentuser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allusersList;
    }

    /**
     * This static method takes in two parameters, user name and user password.  The method queries the database to
     * verify if the input parameters have a match in the database.  This method returns true for a match, and false
     * if there is no match.
     * @param User_Name user name
     * @param Password user password
     * @return returns a boolean, true for a match and false for no match
     */
    public static boolean usersLogin(String User_Name, String Password) {
        try {
            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = JDBC.JDBCconnection.prepareStatement(sql);
            ps.setString(1, User_Name);
            ps.setString(2, Password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String databaseUser_Name = rs.getString("User_Name");
                String databasePassword = rs.getString("Password");
                if (User_Name.equals(databaseUser_Name) && Password.equals(databasePassword)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method is used to set a value in the appointments screen combo box for user name
     * @param userID
     * @return returns a string of username based on userID
     */
    public static String getUserName(int userID) {
        try {
            String sql = "SELECT User_Name FROM users WHERE User_ID = " + userID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            return rs.getString("User_Name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method is used to set a value in the appointments screen combo box for user password
     * @param userID
     * @return returns a string of user password based on userID
     */
    public static String getPassword(int userID) {
        try {
            String sql = "SELECT Password FROM users WHERE User_ID = " + userID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            return rs.getString("Password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
