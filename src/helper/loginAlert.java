package helper;

import DAO.appointmentsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.appointments;

import java.time.LocalDateTime;

/**
 * This class contains a method for displaing alerts related to user login
 */

public class loginAlert {

    /**
     *
     * This method is called after a successful user login. Appointments that begin within 15 minutes of a successful
     * login are listed in information popups.  Each appointment that is within 15 minutes will display a custom
     * login message with the appointment ID and starting date and time of the appointment.  Alternatively, if there
     * are no appointments within 15 minutes, a different popup message will inform the user about this.
     */

    public static void loginAppointmentAlert() {

        // Get all appointments in a list, and initialize a second empty list to contain appointments up to 15 minutes away in the future
        ObservableList<appointments> allAppointmentsList = appointmentsDAO.getAllAppointments();
        ObservableList<appointments> immediateList = FXCollections.observableArrayList();

        // Find the time 15 minutes from the current local time
        LocalDateTime immediateCalendar = LocalDateTime.now().plusMinutes(15);
        int appointmentCount = 0;

        // Find all appointment starting times within 15 minutes of the current time
        for (appointments singleAppointment : allAppointmentsList) {
            if (singleAppointment.getAppointmentStart().isBefore(immediateCalendar) && singleAppointment.getAppointmentStart().isAfter(LocalDateTime.now())) {
                immediateList.add(singleAppointment);
                appointmentCount++;
            }
        }

        // if there are no appointments, display an appropriate message
        if (appointmentCount == 0) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("Appointment");
            newAlert.setContentText("No appointments starting within 15 minutes of login.");
            newAlert.showAndWait();
        }

        // if there is at least 1 appointment, loop through alarm list and display an alert message for each alarm
        else if (appointmentCount > 0) {

            for (appointments singleAppointment : immediateList) {
                Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                newAlert.setTitle("Appointment");
                newAlert.setContentText("Within 15 minutes, appointment #" + singleAppointment.getAppointmentID() + " starts at " + singleAppointment.getAppointmentStart());
                newAlert.showAndWait();
            }
        }
    }
}
