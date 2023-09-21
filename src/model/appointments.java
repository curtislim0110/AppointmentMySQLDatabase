package model;
import DAO.appointmentsDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.time.*;

/**
 * class for appointments, all appointments must have at least 1 customer
 */
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

    /**
     * constructor
     * @param appointmentID appointment ID
     * @param title title of appointment
     * @param description description of appointment
     * @param location appointment location
     * @param type appointment type
     * @param appointmentStart appointment starting date and time
     * @param appointmentEnd appointment ending date and time
     * @param customerID appointment associated customer
     * @param userID appointment associated user
     * @param contactID appointment associated contactID
     * @param contactName appointment associated contact name
     */
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

    /**
     * getter
     * @return
     */
    public int getAppointmentID() { return appointmentID; }

    /**
     * getter
     * @return
     */
    public String getTitle() { return title; }

    /**
     * getter
     * @return
     */
    public String getDescription() { return description; }

    /**
     * getter
     * @return
     */
    public String getLocation() { return location; }

    /**
     * getter
     * @return
     */
    public String getType() { return type; }

    /**
     * getter
     * @return
     */
    public LocalDateTime getAppointmentStart() { return appointmentStart; }

    /**
     * getter
     * @return
     */
    public LocalDateTime getAppointmentEnd() { return appointmentEnd; }
    public int getCustomerID() { return customerID; }

    /**
     * getter
     * @return
     */
    public int getUserID() { return userID; }

    /**
     * getter
     * @return
     */
    public int getContactID() { return contactID; }

    /**
     * getter
     * @return
     */
    public String getContactName() { return contactName; }

    /**
     * getter
     * @param appointmentID
     */
    // public String getUserName() { return userName; }

    /**
     * setter
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) { this.appointmentID = appointmentID; }

    /**
     * setter
     * @param title
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * setter
     * @param description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * setter
     * @param location
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * setter
     * @param type
     */
    public void setType(String type) { this.type = type; }

    /**
     * setter
     * @param appointmentStart
     */
    public void setAppointmentStart(LocalDateTime appointmentStart) { this.appointmentStart = appointmentStart; }

    /**
     * setter
     * @param appointmentEnd
     */
    public void setAppointmentEnd(LocalDateTime appointmentEnd) { this.appointmentEnd = appointmentEnd; }

    /**
     * setter
     * @param customerID
     */
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    /**
     * setter
     * @param userID
     */
    public void setUserID(int userID) { this.userID = userID; }

    /**
     * setter
     * @param contactID
     */
    public void setContactID(int contactID) { this.contactID = contactID; }

    /**
     * setter
     * @param contactName
     */
    public void setContactName(String contactName) { this.contactName = contactName; }

    /**
     * setter
     * @param customerID
     * @param JstartCheck
     * @param JendCheck
     * @return
     */
    // public void setUserName(String userName) { this.userName = userName; }

    /**
     * Method for determining if two appointments have an overlap conflict when adding a new appointment.  Error
     * messages are displayed within this method with a description of the type of error
     * @param customerID customerID
     * @param JstartCheck starting date and time
     * @param JendCheck ending date and time
     * @return
     */
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

    /**
     * Method for determining if two appointments have an overlap conflict when updating a existing appointment.  Error
     * messages are displayed within this method with a description of the type of error.  Compared to the
     * addConflictCheck method, modifyConflictCheck takes appointmentID as an additional parameter.  appointmentID is
     * used so that the appointment overlap check is not used on the same appointment.  In this way, changing the
     * type or description of the appointment does not result in an appointment overlap error.
     * @param customerID
     * @param JstartCheck
     * @param JendCheck
     * @param appointmentID
     * @return
     */
    public static boolean modifyConflictCheck(int customerID, LocalDateTime JstartCheck, LocalDateTime JendCheck, int appointmentID) {

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
            // this modifyConflictCheck method differs from the addConflictCheck method because the parameter appointmentID is used to check
            // if the appointment is modifying itself, which should not cause an appointment overlap error
            if ((customerID == singleAppointment.getCustomerID()) && (appointmentID != singleAppointment.getAppointmentID())) {
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

}
