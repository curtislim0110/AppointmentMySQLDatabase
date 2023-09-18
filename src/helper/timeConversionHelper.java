package helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class timeConversionHelper {
    public static ZonedDateTime loginactivityUTC(LocalDateTime timelocal) {
        ZonedDateTime timeUTC = timelocal.atZone(ZoneId.of("Pacific/Fiji"));
        return timeUTC;
    }
}
