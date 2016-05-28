package ua.warko.yalantistask1.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Warko on 15.05.2016.
 */
public class DateFormatter {
    private static final String DATE_PATTERN = "MMM d, yyyy";


    public static String getNormalDate(long millis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN, new Locale("uk", "UA"));
        String string = simpleDateFormat.format(new Date(millis * 1000));
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static String getDaysLeft(long deadline) {
        long now = System.currentTimeMillis() / 1000;
        long timeDifference = deadline - now;
        long days = TimeUnit.SECONDS.toDays(timeDifference);
        String daysLeft;
        if (days == 1) {
            daysLeft = days + " " + Constants.FORMATTER_VAL_1;
        } else if (days > 1 && days < 5) {
            daysLeft = days + " " + Constants.FORMATTER_VAL_2;
        } else
            daysLeft = days + " " + Constants.FORMATTER_VAL_3;
        return daysLeft;
    }
}
