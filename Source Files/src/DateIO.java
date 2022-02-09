/**
 Controls and manage the inputting and outputting of date and time, as well as the conversion from String to date-time and vice versa.
 @ author Foo Jun Ming
 @ version 1.0
 @ since 2021-11-13
*/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DateIO {
  /**
 * Handles the inputting of the date and time from the user in a specified format.
*/
    public static LocalDateTime dateInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter date of reservation (in dd/MM/yy): ");
            String date_in = sc.next();
            System.out.println("Enter 24-hour format time of reservation (in hh:mm)");
            String time_in = sc.next();

            date_in = date_in + " " + time_in;
            LocalDateTime reserved_date = stringToDateTime(date_in);

            if (reserved_date == null) {
                System.out.println("An error occured. Please retry.");
                continue;
            }
            else if (reserved_date.isBefore(LocalDateTime.now().minusMinutes(15))) {
                System.out.println("Invalid date-time input! Input is in the past! Please re-enter.");
                continue;
            }
            else if (reserved_date.isAfter(LocalDateTime.now().plusYears(1))) {
                System.out.println("Booking can only be made up to 1 year in advanced!");
                continue;
            }

            System.out.println("Your date-time input: " + dateToString(reserved_date) + "\nEnter Y to confirm: ");
            char confirmation = sc.next().toUpperCase().charAt(0);
            if (confirmation == 'Y') {
                return reserved_date;
            }
        }
    }
 /**
 Converts a string into the date and time in a specified format.
 * @param date_in The string format of the date and time.
 * @return The date and time in a specified format.
*/
    public static LocalDateTime stringToDateTime(String date_in) {
        final DateTimeFormatter dateInFormat = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        try {
            LocalDateTime date = LocalDateTime.parse(date_in, dateInFormat);
            return date;

        } catch (DateTimeParseException e) {
            return null;
        }
    }
  /**
 Converts the date and time in a specified format into a string.
 * @param The date and time in a specified format.
 * @return date_in The string format of the date and time.
*/
    public static String dateToString(LocalDateTime date) {
        final DateTimeFormatter dateOutFormat = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a");
        return date.format(dateOutFormat);
    }
}
