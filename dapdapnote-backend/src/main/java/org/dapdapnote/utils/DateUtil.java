package org.dapdapnote.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("a h:mm");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toDateStr(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.toLocalDate().toString();
    }

    public static String toDateTimeStr(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.format(dateTimeFormatter);
    }


    public static String toTimeStr(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.format(timeFormatter);
    }
}
