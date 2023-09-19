package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;

public class timeHelper {

    // This function is used to display potential appointment times in a user's local time zone, based on EST business hours of 8:00 to 22:00.
    public static ObservableList<LocalTime> appointmentHoursEST(LocalTime businessHourStart) {

        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();

        // Convert starting business hours of 8am EST into a ZonedDateTime object
        ZonedDateTime businesszonetime = ZonedDateTime.of(LocalDate.now(), businessHourStart, ZoneId.of("America/New_York"));
        System.out.println(businesszonetime);
        // Convert business hours ZonedDateTime discovered above into the system default time zone
        ZonedDateTime localzonetime = businesszonetime.withZoneSameInstant(ZoneId.systemDefault());
        System.out.println(localzonetime);

        // find the first hour to start the list of appointment times
        int firstHour = localzonetime.getHour();

        // find the final hour in which to stop listing appointment times, based on 30 minute appointment times and a 14 hour workday
        int finalhour = firstHour + 27;

        // iterate over time objects and add them to timeList for combo box display
        for(int i = firstHour; i <= finalhour; i++) {
            LocalTime newLocalTime = localzonetime.toLocalTime();
            timeList.add(newLocalTime);
            localzonetime = localzonetime.plusMinutes(30);
        }
        return timeList;
    }
}


