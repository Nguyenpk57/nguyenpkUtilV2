package core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

public interface DateTimeUtil {

    interface DateTimePattern {
        String MMYYYY = "MM/yyyy";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYY = "yyyy";
        String YYYYMMDD = "yyyyMMdd";
        String DDMMYYYY = "dd/MM/yyyy";
        String MMDDYYYY = "MM/dd/yyyy";
        String HHMMSS = "HH:mm:ss";
        String DDMMYYYYHHMMSS = "dd/MM/yyyy HH:mm:ss";
        String DDMMYYYYHHMM = "dd/MM/yyyy HH:mm";
        String DDMMYYYYHH24MISS = "dd/MM/yyyy HH24:mi:ss";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSZ = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        String MMMM = "MMMM";
    }

    String format(Date value, String pattern);

    Date toDate(String value, String pattern) throws ParseException;

    Date getFirstDayOfMonth(Date value);

    Date getLastDayOfMonth(Date value);

    Date getFirstDayOfPreviousMonth(Date value);

    Date getLastDayOfPreviousMonth(Date value);

    Date addSecond(Date value, int second);

    Date addHour(Date value, int hour);

    Date addDay(Date value, int day);

    Date addMonth(Date value, int month);

    Timestamp toTimestamp(Date value);

    Double getDayBetween(Date fromDate, Date toDate);

    boolean equals(Date a, Date b);

    Date atStartOfDay(Date value);

}
