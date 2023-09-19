package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class appointmentsDAO {
    public static ObservableList<appointments> getAllAppointments() {
        ObservableList<appointments> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments " +
                    "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                    "INNER JOIN users ON appointments.User_ID = users.User_ID " +
                    "ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rsGetAll = ps.executeQuery();
            while (rsGetAll.next()) {
                int appointmentID = rsGetAll.getInt("Appointment_ID");
                String title = rsGetAll.getString("Title");
                String description = rsGetAll.getString("Description");
                String location = rsGetAll.getString("Location");
                String type = rsGetAll.getString("Type");

                LocalDateTime appointmentStart = rsGetAll.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appointmentEnd = rsGetAll.getTimestamp("End").toLocalDateTime();

                int customerID = rsGetAll.getInt("Customer_ID");
                int userID = rsGetAll.getInt("User_ID");
                int contactID = rsGetAll.getInt("Contact_ID");

                String contactName = rsGetAll.getString("Contact_Name");
                String userName = rsGetAll.getString("User_Name");

                appointments currentappointment = new appointments(appointmentID, title, description, location, type,
                        appointmentStart, appointmentEnd, customerID, userID, contactID, contactName, userName);
                appointmentList.add(currentappointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    public static void deleteAppointment(int appointmentID) {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement deleteAppoint = JDBC.JDBCconnection.prepareStatement(sql);
            deleteAppoint.setInt(1, appointmentID);
            deleteAppoint.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addAppointment(String title, String description, String location, String type, LocalDateTime starttime,
                                      LocalDateTime endtime, int customerID, int userID, int contactID) {
        try {
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psINSERT = JDBC.JDBCconnection.prepareStatement(sql);

            psINSERT.setString(1, title);
            psINSERT.setString(2, description);
            psINSERT.setString(3, location);
            psINSERT.setString(4, type);
            psINSERT.setTimestamp(5, Timestamp.valueOf(starttime));
            psINSERT.setTimestamp(6, Timestamp.valueOf(endtime));
            psINSERT.setInt(7, customerID);
            psINSERT.setInt(8, userID);
            psINSERT.setInt(9, contactID);
            psINSERT.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();;
        }

    }

}
