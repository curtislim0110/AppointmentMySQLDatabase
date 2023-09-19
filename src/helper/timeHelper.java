package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;

public class timeHelper {

    // This function is used to display potential appointment times in a user's local time zone, based on EST business hours of 8:00 to 22:00.

    public static ObservableList<LocalTime> appointmentHoursEST() {

        ObservableList<LocalTime> timeList = FXCollections.observableArrayList();
        ZonedDateTime businessZDT = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York"));
        ZonedDateTime localZDT = ZonedDateTime.ofInstant(businessZDT.toInstant(), ZoneId.systemDefault());
        int localStartingHour = localZDT.getHour();
        int totalHours = localStartingHour + 13;
        int midnightOrGreater = 0;

        for(int i = localStartingHour; i <= totalHours; i++) {
            if(i<24) {
                timeList.add(LocalTime.of(i,0));
            }
            if(i>23) {
                timeList.add(LocalTime.of(midnightOrGreater,0));
                midnightOrGreater += 1;
            }
        }
        return timeList;
    }
}


