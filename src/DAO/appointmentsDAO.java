package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class appointmentsDAO {
    public static ObservableList<appointments> getAppointmentList() {
        ObservableList<appointments> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments" +
                    "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
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

                appointments currentappointment = new appointments(appointmentID, title, description, location, type,
                        appointmentStart, appointmentEnd, customerID, userID, contactID, contactName);
                appointmentList.add(currentappointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }
}
