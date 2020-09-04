package com.bukonudakonusalim.takenotes.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class TimeUtils {

    public static DateTime timeStringToDateTime(String datetime) {
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(datetime);
    }

    public static String dateTimeToString(DateTime datetime) {
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(datetime);
    }

    public static String getTodayLong() {
        return DateTimeFormat.forPattern("EEEE").print(DateTime.now());
    }
}
