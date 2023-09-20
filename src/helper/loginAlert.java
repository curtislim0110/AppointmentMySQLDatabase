package helper;

import DAO.appointmentsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.appointments;

import java.time.LocalDateTime;

public class loginAlert {

    public static void loginAppointmentAlert() {
        // Get all appointments in a list, and initialize a second empty list to contain appointments up to 15 minutes away in the future
        ObservableList<appointments> allAppointmentsList = appointmentsDAO.getAllAppointments();
        ObservableList<appointments> immediateList = FXCollections.observableArrayList();

        // Find the date 1 week away from the current local time
        LocalDateTime immediateCalendar = LocalDateTime.now().plusMinutes(360);
        int appointmentCount = 0;

        // Find all appointment starting times within 1 week of the current time
        for (appointments singleAppointment : allAppointmentsList) {
            if (singleAppointment.getAppointmentStart().isBefore(immediateCalendar) && singleAppointment.getAppointmentStart().isAfter(LocalDateTime.now())) {
                immediateList.add(singleAppointment);
                appointmentCount++;
            }
        }

        if (appointmentCount == 0) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setTitle("Appointment");
            newAlert.setContentText("No appointments starting within 15 minutes of login.");
            newAlert.showAndWait();
        }
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
