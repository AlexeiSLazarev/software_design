import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateExample {

    public static void main(String[] args) {
        String dateString = "2024-05-13 14:30:00";

        LocalDateTime localDateTime = parseDateTime(dateString, "yyyy-MM-dd HH:mm:ss");
        if (localDateTime != null) {
            System.out.println("Parsed LocalDateTime: " + localDateTime);

            Date date = convertToDate(localDateTime);
            System.out.println("Converted Date: " + date);
        } else {
            System.err.println("Failed to parse the date.");
        }
    }

    public static LocalDateTime parseDateTime(String dateString, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format: " + e.getMessage());
            return null;
        }
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
