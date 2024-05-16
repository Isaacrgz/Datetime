package datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.temporal.TemporalAdjusters.previous;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Date & Time working in Java");
        System.out.println("Format: day/mounth/year");
        System.out.println("Get date: " + getTodayDate());
        System.out.println("Get datetime: " + getTodayDateTime());
        System.out.println("Get millis from datetime: " + getDateTimeToMillis());
        System.out.println("Get datetime from millis: " + getMillisToDateTime(getDateTimeToMillis()));
        System.out.println("Get millis from String: " + getDatetimeFromStringToMillis("14/05/2024") + ", reverse: "
                + getMillisToDateTime(getDatetimeFromStringToMillis("14/05/2024")));
        System.out.println("Get millis from String: " + getDatetimeFromStringToMillis2("2024-05-14") + ", reverse: "
                + getMillisToDateTime(getDatetimeFromStringToMillis2("2024-05-14")));
        System.out.println("Get last friday: " + getLastFriday());
    }

    public static String getTodayDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        String sDate = formatter.format(today);
        return sDate;
    }

    public static String getTodayDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime today = LocalDateTime.now();
        String sDate = formatter.format(today);
        return sDate;
    }

    public static Long getDateTimeToMillis() {
        LocalDateTime today = LocalDateTime.now();
        Instant instant = today.toInstant(ZoneOffset.UTC);
        Long millis = instant.toEpochMilli();
        return millis;
    }

    public static String getMillisToDateTime(Long millis) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                .withZone(ZoneId.of("America/Mexico_City"));
        Instant instant = Instant.ofEpochMilli(millis);
        String sDate = formatter.format(instant);
        return sDate;
    }

    public static Long getDatetimeFromStringToMillis(String sDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                .withZone(ZoneId.of("America/Mexico_City"));
        LocalDateTime date = LocalDateTime.parse(sDate + " 00:00:00", formatter);
        Instant instant = date.toInstant(ZoneOffset.of("-06:00"));
        Long millis = instant.toEpochMilli();

        return millis;
    }

    public static Long getDatetimeFromStringToMillis2(String sDate) {
        return LocalDateTime.parse(sDate + "T00:00:00").atZone(ZoneId.of("America/Mexico_City")).toInstant()
                .toEpochMilli();
    }

    public static String getLastFriday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate friday = LocalDate.now().with(previous(FRIDAY));
        return friday.format(formatter);
    }
}
