package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class usersDAO {

    public static ObservableList<users> getUsersList() {
        ObservableList<users> allusersList = FXCollections.observableArrayList();
        try {
            // open database connection and select all users with SQL statement
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

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

    public static users usersLogin(String User_Name, String Password) {
        try {
            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = JDBC.JDBCconnection.prepareStatement(sql);
            ps.setString(1, User_Name);
            ps.setString(2, Password);
            ResultSet rs = ps.executeQuery();

            users currentuser = null;

            if (rs.next()) {
                int userID = rs.getInt("User_ID");
                User_Name = rs.getString("User_Name");
                Password = rs.getString("Password");
                currentuser = new users(userID, User_Name, Password);
            }
            return currentuser;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
