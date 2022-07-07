package core.util.impl;

import core.module.ModuleFactory;
import core.util.DateTimeUtil;
import core.util.StringUtil;
import core.util.format.Formatter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtilImpl implements DateTimeUtil {

    private final StringUtil stringUtil = ModuleFactory.getFactory().stringUtil();
    private final Formatter<SimpleDateFormat> simpleDateFormatter = ModuleFactory.getFactory().simpleDateFormatter();


    @Override
    public String format(Date value, String pattern) {
        pattern = stringUtil.nvl(pattern);
        if (value == null || stringUtil.isEmpty(pattern)) {
            return stringUtil.BLANK;
        }
        SimpleDateFormat sdf = simpleDateFormatter.get(pattern);
        return sdf == null ? stringUtil.BLANK : sdf.format(pattern);
    }

    @Override
    public Date toDate(String value, String pattern) throws ParseException {
        value = stringUtil.trim(value);
        pattern = stringUtil.trim(value);
        if (stringUtil.isEmpty(value) || stringUtil.isEmpty(pattern)) {
            return null;
        }
        SimpleDateFormat sdf = simpleDateFormatter.get(pattern);
        return sdf == null ? null : sdf.parse(value);
    }

    @Override
    public Date getFirstDayOfMonth(Date value) {
        Date result = value;
        if (value != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(value);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            result = cal.getTime();
        }
        return result;
    }

    @Override
    public Date getLastDayOfMonth(Date value) {
        Date result = value;
        if (value != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(value);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            result = cal.getTime();
        }
        return result;
    }

    @Override
    public Date getFirstDayOfPreviousMonth(Date value) {
        Date firstDayOfMonth = getFirstDayOfMonth(value);
        Date result = addMonth(firstDayOfMonth, -1);
        return result;
    }

    @Override
    public Date getLastDayOfPreviousMonth(Date value) {
        Date firstDayOfMonth = getFirstDayOfMonth(value);
        Date result = addDay(firstDayOfMonth, -1);
        return result;
    }

    @Override
    public Date addSecond(Date value, int second) {
        Date result = value;
        if (value != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(value);
            cal.add(Calendar.SECOND, second);
            result = cal.getTime();
        }
        return result;
    }

    @Override
    public Date addHour(Date value, int hour) {
        Date result = value;
        if (value != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(value);
            cal.add(Calendar.HOUR_OF_DAY, hour);
            result = cal.getTime();
        }
        return result;
    }

    @Override
    public Date addDay(Date value, int day) {
        Date result = value;
        if (value != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(value);
            cal.add(Calendar.DAY_OF_MONTH, day);
            result = cal.getTime();
        }
        return result;
    }

    @Override
    public Date addMonth(Date value, int month) {
        Date result = value;
        if (value != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(value);
            cal.add(Calendar.MONTH, month);
            result = cal.getTime();
        }
        return result;
    }

    @Override
    public Timestamp toTimestamp(Date value) {
        return value == null ? null : new Timestamp(value.getTime());
    }

    @Override
    public Double getDayBetween(Date fromDate, Date toDate) {
        if (fromDate == null || toDate == null) {
            return 0D;
        }
        double days = (toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24);
        return days;
    }

    @Override
    public boolean equals(Date a, Date b) {
        return (a == null && b == null) || (a != null && a.equals(b)) || (b != null && b.equals(a));
    }

    @Override
    public Date atStartOfDay(Date value) {
        Date result = value;
        if (value != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(value);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            result = cal.getTime();
        }
        return result;
    }
}
