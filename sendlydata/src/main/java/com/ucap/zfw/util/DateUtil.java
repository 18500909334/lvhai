package com.ucap.zfw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 * @author lvh
 *
 */
public class DateUtil {
    public final static String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss.SSS";

    public static void main(String[] args) {
      /*  String today= today();
        String yestoday = yestoday();
        System.out.println(today);
        System.out.println(yestoday);
        String todayFormat = todayFormat();
        System.out.println(todayFormat);*/
        String s="20200423104300";
        Date parse = DateUtil.parse(s, new SimpleDateFormat("yyyyMMddHHmmss"));
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse);
        System.out.println(format);
    }
    public static final String  formatData(String time) {
    	Date parse = DateUtil.parse(time, new SimpleDateFormat("yyyyMMddHHmmss"));
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse);
        return format;
   }
    public static final String yestoday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date date = cal.getTime();
        return format(date,"yyyy-MM-dd");
    }

    public static final String today() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        return format(date,"yyyy-MM-dd");
    }
    public static final String todayFormat() {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	Date date = cal.getTime();
    	return format(date,"yyyyMMdd");
    }

    public static final String tomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date date = cal.getTime();
        return format(date,"yyyy-MM-dd");
    }

    public static final Date thisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static final Date nextMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    public static final Date parse(String dateString) {
        DateFormat formator = new SimpleDateFormat("yyyy-MM-dd");
        return parse(dateString, formator);
    }

    public static final Date parse(String dateString, DateFormat formator) {
        if ((dateString == null) || (dateString.length() <= 0)) {
            return null;
        }
        if (formator == null)
            throw new IllegalArgumentException("Argument 'formator' is null");
        try {
            return formator.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String format(Date dateValue) {
        DateFormat sfdate = new SimpleDateFormat(FORMAT_DATETIME);
        return sfdate.format(dateValue);
    }

    public static String format(Date dateValue, String pattern) {
        DateFormat sfdate = new SimpleDateFormat(pattern);
        return sfdate.format(dateValue);
    }
}