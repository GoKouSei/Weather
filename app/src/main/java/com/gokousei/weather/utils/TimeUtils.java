package com.gokousei.weather.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static String getWeek(String time) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(time);

        return new SimpleDateFormat("EEEE").format(date);
    }

    public static String getDayAndWeek(String time) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time));
        return calendar.get(Calendar.DAY_OF_MONTH)+"日 "+"("+getWeek(time)+")";
    }
}
