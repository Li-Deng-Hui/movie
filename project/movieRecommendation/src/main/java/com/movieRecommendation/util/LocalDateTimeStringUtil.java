package com.movieRecommendation.util;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * User: LiDenghui
 * Date: 2021/4/17
 * Time: 22:33
 **/
//日期转换
public class LocalDateTimeStringUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String NULL_VALUE = "NULL";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime parserString2LocalDateTime(final String dateTime) {

        if (StringUtils.isBlank(dateTime) || NULL_VALUE.equalsIgnoreCase(dateTime)) {
            return null;
        }
        if(dateTime.length() == 10){
            return convertDate2LocalDateTime(convertLocalDate2Date(parserString2LocalDate(dateTime)));
        }
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    public static Date convertLocalDate2Date(LocalDate localDate) {

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static LocalDate parserString2LocalDate(final String date) {

        if (StringUtils.isBlank(date) || NULL_VALUE.equalsIgnoreCase(date)) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static LocalDateTime convertDate2LocalDateTime(Date date) {

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }
    public static String parserLocalDateTimeString(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern(DATE_FORMAT).format(localDateTime);
    }
    public static String parserLocalDateTimeStringTime(LocalDateTime localDateTime){
        return DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(localDateTime);
    }
}
