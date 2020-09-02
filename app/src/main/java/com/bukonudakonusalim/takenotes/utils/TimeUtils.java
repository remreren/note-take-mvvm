package com.bukonudakonusalim.takenotes.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class TimeUtils {

    public static String getTodayLong() {
        return DateTimeFormat.forPattern("EEEE").print(DateTime.now());
    }
}
