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

    private int CustomerID;
    private int UserID;
    private int contactID;

    private String contactName;

    public appointments(int appointmentID, String title, String description, String location, String type,
                       LocalDateTime appointmentStart, LocalDateTime appointmentEnd,
                       int CustomerID, int UserID, int contactID, String contactName) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;

        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;

        this.CustomerID = CustomerID;
        this.UserID = UserID;
        this.contactID = contactID;

        this.contactName = contactName;
    }
}
