package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Class for database queries related to appointments objects
 */
public class appointmentsDAO {

    /**
     * This static method returns a list of all appointments objects
     * @return list of appointments
     */
    public static ObservableList<appointments> getAllAppointments() {
        ObservableList<appointments> appointmentList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM appointments " +
                    "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                    // "INNER JOIN users ON appointments.User_ID = users.User_ID " +
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
                // String userName = rsGetAll.getString("User_Name");

                appointments currentappointment = new appointments(appointmentID, title, description, location, type,
                        appointmentStart, appointmentEnd, customerID, userID, contactID, contactName);
                appointmentList.add(currentappointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }

    /**
     * Deletes an appointment from the database based on appointmentiD
     * @param appointmentID
     */
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

    /**
     * Adds a new appointment to the database based on input parameters.  Checks for appointment overlap are performed
     * outside of this method, in appointments.addConflictCheck.
     * @param title
     * @param description
     * @param location
     * @param type
     * @param starttime
     * @param endtime
     * @param customerID
     * @param userID
     * @param contactID
     */
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
            psINSERT.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing appointment in the database based on input parameters.  Checks for appointment overlap
     * while updating are performed outside of this method, in appointments.modifyConflictCheck.
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param starttime
     * @param endtime
     * @param customerID
     * @param userID
     * @param contactID
     */
    public static void updateAppointment(int appointmentID, String title, String description, String location,
                                         String type, LocalDateTime starttime, LocalDateTime endtime,
                                         int customerID, int userID, int contactID) {
        try {
            String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, " +
                    "Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement psUPDATE = JDBC.JDBCconnection.prepareStatement(sql);
            psUPDATE.setString(1, title);
            psUPDATE.setString(2, description);
            psUPDATE.setString(3, location);
            psUPDATE.setString(4, type);
            psUPDATE.setTimestamp(5, Timestamp.valueOf(starttime));
            psUPDATE.setTimestamp(6, Timestamp.valueOf(endtime));
            psUPDATE.setInt(7, customerID);
            psUPDATE.setInt(8, userID);
            psUPDATE.setInt(9, contactID);
            psUPDATE.setInt(10, appointmentID);
            psUPDATE.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get a list of appointments related to a single contact, for a report
     * @param contactID
     * @return filtered list of appointments by contact
     */
    public static ObservableList<appointments> getApptByContact(int contactID) {
        ObservableList<appointments> contactappointmentList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments " +
                    "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                    "WHERE appointments.Contact_ID=? " +
                    "ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.JDBCconnection.prepareStatement(sql);
            ps.setInt(1, contactID);

            ResultSet rsGetAll = ps.executeQuery();
            while (rsGetAll.next()) {
                int appointmentID = rsGetAll.getInt("Appointment_ID");
                String title = rsGetAll.getString("Title");
                String description = rsGetAll.getString("Description");
                String location = rsGetAll.getString("Location");
                String type = rsGetAll.getString("Type");
                LocalDateTime starttime = rsGetAll.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endtime = rsGetAll.getTimestamp("End").toLocalDateTime();
                int customerID = rsGetAll.getInt("Customer_ID");
                int userID = rsGetAll.getInt("User_ID");
                contactID = rsGetAll.getInt("Contact_ID");
                String contactName = rsGetAll.getString("Contact_Name");

                appointments currentappointment = new appointments(appointmentID, title, description,
                        location, type, starttime, endtime, customerID, userID, contactID, contactName);
                contactappointmentList.add(currentappointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactappointmentList;
    }

    /**
     * This method is used to get a list of appointments related to a single user, for a report
     * @param userID
     * @return filtered list of appointments by user
     */
    public static ObservableList<appointments> getApptByUser(int userID) {
        ObservableList<appointments> userappointmentList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments " +
                    "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                    "WHERE appointments.User_ID=? " +
                    "ORDER BY appointments.Appointment_ID";
            PreparedStatement ps = JDBC.JDBCconnection.prepareStatement(sql);
            ps.setInt(1, userID);

            ResultSet rsGetAll = ps.executeQuery();
            while (rsGetAll.next()) {
                int appointmentID = rsGetAll.getInt("Appointment_ID");
                String title = rsGetAll.getString("Title");
                String description = rsGetAll.getString("Description");
                String location = rsGetAll.getString("Location");
                String type = rsGetAll.getString("Type");
                LocalDateTime starttime = rsGetAll.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endtime = rsGetAll.getTimestamp("End").toLocalDateTime();
                int customerID = rsGetAll.getInt("Customer_ID");
                userID = rsGetAll.getInt("User_ID");
                int contactID = rsGetAll.getInt("Contact_ID");
                String contactName = rsGetAll.getString("Contact_Name");

                appointments currentappointment = new appointments(appointmentID, title, description,
                        location, type, starttime, endtime, customerID, userID, contactID, contactName);
                userappointmentList.add(currentappointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userappointmentList;
    }

}
