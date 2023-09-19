package model;
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
}
