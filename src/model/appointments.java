package model;
import DAO.appointmentsDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.*;

public class appointments {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;

    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;

    private int customerID;
    private int userID;
    private int contactID;

    private String contactName;
    // private String userName;

    public appointments(int appointmentID, String title, String description, String location, String type,
                       LocalDateTime appointmentStart, LocalDateTime appointmentEnd,
                       int customerID, int userID, int contactID, String contactName) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;

        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;

        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;

        this.contactName = contactName;
        // this.userName = userName;
    }

    // Getters
    public int getAppointmentID() { return appointmentID; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getType() { return type; }
    public LocalDateTime getAppointmentStart() { return appointmentStart; }
    public LocalDateTime getAppointmentEnd() { return appointmentEnd; }
    public int getCustomerID() { return customerID; }
    public int getUserID() { return userID; }
    public int getContactID() { return contactID; }
    public String getContactName() { return contactName; }
    // public String getUserName() { return userName; }

    // Setters

    public void setAppointmentID(int appointmentID) { this.appointmentID = appointmentID; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
    public void setType(String type) { this.type = type; }
    public void setAppointmentStart(LocalDateTime appointmentStart) { this.appointmentStart = appointmentStart; }
    public void setAppointmentEnd(LocalDateTime appointmentEnd) { this.appointmentEnd = appointmentEnd; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setUserID(int userID) { this.userID = userID; }
    public void setContactID(int contactID) { this.contactID = contactID; }
    public void setContactName(String contactName) { this.contactName = contactName; }
    // public void setUserName(String userName) { this.userName = userName; }


    public static boolean addConflictCheck(int customerID, LocalDateTime JstartCheck, LocalDateTime JendCheck) {

        // get a list of all appointments from the database
        ObservableList<appointments> appointmentList = appointmentsDAO.getAllAppointments();

        // initialize two temporary fields to hold appointment starting and ending times from the list
        LocalDateTime MstartCheck;
        LocalDateTime MendCheck;

        // iterate over appointment list, checking for a schedule overlap
        for (appointments singleAppointment : appointmentList) {
            MstartCheck = singleAppointment.getAppointmentStart();
            MendCheck = singleAppointment.getAppointmentEnd();

            // when comparing appointments for overlap, check to see if it is the same customer with a potential overlapping appointment
            // if the customersIDs are different, then no error message is triggered here
            if (customerID == singleAppointment.getCustomerID()) {
                if ((MstartCheck.isAfter(JstartCheck) || MstartCheck.isEqual(JstartCheck)) && (MstartCheck.isBefore(JendCheck))){
                    Alert newAlert = new Alert(Alert.AlertType.ERROR);
                    newAlert.setTitle("Error");
                    newAlert.setContentText("At least one existing appointment start time is equal to or between the proposed appointment time");
                    newAlert.showAndWait();
                    return true;
                }
                else if ((MendCheck.isAfter(JstartCheck)) && (MendCheck.isBefore(JendCheck) || MendCheck.isEqual(JendCheck))) {
                    Alert newAlert = new Alert(Alert.AlertType.ERROR);
                    newAlert.setTitle("Error");
                    newAlert.setContentText("At least one existing appointment end time is equal to between the proposed appointment time");
                    newAlert.showAndWait();
                    return true;
                } else if ((MstartCheck.isBefore(JstartCheck) || MstartCheck.isEqual(JstartCheck)) && ((MendCheck.isAfter(JendCheck)) || MendCheck.isEqual(JendCheck))) {
                    Alert newAlert = new Alert(Alert.AlertType.ERROR);
                    newAlert.setTitle("Error");
                    newAlert.setContentText("At least one existing appointment equals or overlaps the proposed appointment time");
                    newAlert.showAndWait();
                    return true;
                }
            }
        }
        return false;
    }

    // public static boolean modifyConflictCheck(int appointmentID, int customerID, LocalDateTime parameterStartTime, LocalDateTime parameterEndTime)

    // updating an appointment should not cause an overlap conflict with itself, so we remove that appointment from our list of appointments being checked




}
