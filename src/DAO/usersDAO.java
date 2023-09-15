package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.users;

import java.sql.PreparedStatement;


public class usersDAO (int user_ID, String user_Name, String password) {
    public static ObservableList<users> getUserList() {
        ObservableList<users> userList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");

                User u = new User(userId, userName);
                userList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;

    }
