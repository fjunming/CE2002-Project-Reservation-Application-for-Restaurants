/**
 The Reservation controller for the reservation menu.
 @ author Foo Jun Ming
 @ version 1.0
 @ since 2021-11-13
*/
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Reservation {
  /**
  * Total number of tables in the restaurant.
  */
    private static int NO_OF_TABLES;
    /**
  * Total number of tables occupied in the restaurant.
  */
    private static int no_occupied_tables;
  /**
  * Total number of successful reservations
  */
    private static int no_reservation_successful_attempts;
  /**
  * Scheduler for reservations.
  */
    private static ReservationScheduler scheduler;
  /**
  * List of Customers who have checked in.
  */
    private static Customer cust_arr[];
    /**
  * Number of Customers who have checked in.
  */
    private static int cust_arr_size;
    /**
    * Amount of time customer have to check-in after their appointment in minutes.
    */
    private static final long RESERVATION_TIMEOUT_MINUTES = 15;
    /**
    * Amount of time customer have to dine in hours.
    */
    private static final long DINE_IN_DURATION_HOUR = 2;
    /**
    * Object Array to simulate table objects in restaurant.
    */
    private static Table[] tables;
    /**
    * Creates a new reservation controller.
    * @param array_no_of_seats The list of tables with each entry containing the number of seats in that table.
    */
    public Reservation(int[] array_no_of_seats) {
        no_occupied_tables = 0;
        NO_OF_TABLES = array_no_of_seats.length;
        cust_arr_size = 0;
        tables = new Table[NO_OF_TABLES];
        cust_arr = new Customer[NO_OF_TABLES];
        scheduler = new ReservationScheduler();

        for (int i = 0; i < NO_OF_TABLES; i++) {
            tables[i] = new Table(array_no_of_seats[i]);
        }
    }
    /**
    * Checks for table avaliability for reservation.
    * @param num_pax The number of customers to be allocated to a table.
    * @param start_booking The start time of the reservation.
    * @return The index of the table if available, -1 if table is not available.
    */
    public static int checkForTable(int num_pax, LocalDateTime start_booking) {
        if (num_pax<1){
            return -1;
        }
        for (int i = 0; i < NO_OF_TABLES; i++) {
            if (!tables[i].isOccupied() & tables[i].noOfSeats() >= num_pax & tables[i].checkAvaliability(start_booking)) {
                return i;
            }
        }
        return -1;
    }
    /**
    * Creates a new reservation, schedule the timeout and output to user that a new reservation is successfully made.
    * @param table_no The table number of the table to be allocated for reservation
    * @param num_pax The number of customers to be allocated to a table.
    * @param cust_name The name of the customer making the reservation.
    * @param member_input If True, customer is a member.
    * @param cust_contact The contact number of the customer.
    * @param reserved_date The date and time of the reservation.
    * @return The output string to acknowledge successful reservation.
    */
    public static String newReservation(int table_no, int num_pax, String cust_name, char member_input, String cust_contact, LocalDateTime reserved_date) {
        LocalDateTime reserved_date_timeout = reserved_date.plusMinutes(RESERVATION_TIMEOUT_MINUTES);
        Duration schedule_delay = Duration.between(LocalDateTime.now(), reserved_date_timeout);

        no_reservation_successful_attempts++;
        int cust_id = no_reservation_successful_attempts + 1000 + LocalDate.now().getDayOfMonth() * 100;

        boolean mem;
        if (member_input == 'Y') {
            mem = true;
        }
        else {
            mem = false;
        }
        scheduler.insertSchedule(cust_id, schedule_delay);
        tables[table_no].bookTable(table_no, cust_id, cust_name, mem, num_pax, cust_contact, reserved_date);

        String output = "Reservation id #" + Integer.toString(cust_id) + " at " + DateIO.dateToString(reserved_date) + " for Table "
                + Integer.toString(table_no) + " of " + Integer.toString(num_pax) + " pax created.\r\n" + "Time now is " + DateIO.dateToString(LocalDateTime.now()) + "\n";
        return output;
    }
    /**
    * Prints entire list of available table.
    * @return The output string of the entire list of available tables.
    */
    public static String tablesAvaliable() {
        if (no_occupied_tables == NO_OF_TABLES) {
            return "No table avaliable! Restaurant full!\r\n";
        }
        String output = "Tables available to reserve for dining hours as for now:\r\n";
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < tables.length; i++) {
            if (!tables[i].isOccupied() & tables[i].checkAvaliability(now)) {
                output += ("Table " + String.valueOf(i) + " with " +
                        String.valueOf(tables[i].noOfSeats()) + " seats\r\n");
            }
        }
        return output;
    }
    /**
    * Check for occupied tables.
    * @return True if there are any tables currently occupied.
    */
    public static boolean currentTableOccupency() {
        if (no_occupied_tables == 0) {
            ReservationInterface.printOutput("No tables currently occupied.");
            return false;
        }
        else {
            ReservationInterface.printOutput("No of tables currently occupied: " + String.valueOf(no_occupied_tables));
            return true;
        }
    }
    /**
    * Check for existing reservations.
    * @return True if there are any existing reservations.
    */
    public static boolean currentReservations() {
        String output_reserve;
        DateTimeFormatter dateOutFormat = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a");

        output_reserve = "Current Reservations:\n";
        for (int i = 0; i < tables.length; i++) {
            ArrayList table_bookings = tables[i].getBookings();

            if (table_bookings.size() == 0) {
                continue;
            }
            output_reserve += "Table " + Integer.toString(i) + "\n";
            for (int j = 0; j < table_bookings.size(); j++) {
                ReservationDetails reserve_detail = tables[i].getReservationDetails(j);
                output_reserve += ("#" + reserve_detail.getCust_id() + " " + reserve_detail.getCustName() + "'s booking at " +
                        reserve_detail.getBookingTime().format(dateOutFormat) + "\n");
            }
        }

        if (output_reserve == "Current Reservations:\n") {
            output_reserve += "No current reservations available!";
            ReservationInterface.printOutput(output_reserve);
            return false;
        }

        ReservationInterface.printOutput(output_reserve);
        return true;
    }
    /**
    * Check for customer reservation details given reservation ID.
    * @param cust_id The ID of the reservation.
    * @param get_name_only Only return name if this is True.
    * @return The reservation details given reservation ID.
    */
    public static String custReservationDetails(int cust_id, boolean get_name_only) {
        String output;
        ReservationDetails cust_details = findReservation(cust_id);

        if (cust_details == null) {
            return "Reservation not found!\n";
        }
        else {
            if (get_name_only) {
                return cust_details.getCustName();
            }
            else {
                output =
                        "\nReservation Details:" +
                                "\nCustomer's Name: " + cust_details.getCustName() +
                                "\nNumber of Pax: " + cust_details.getNoOfPax() +
                                "\nCustomer's Contact: " + cust_details.getContact() +
                                "\nMembership: " + cust_details.getMembership() +
                                "\nReserved Timeslot: " + DateIO.dateToString(cust_details.getBookingTime()) + "\n";
            }
            return output;
        }
    }
    /**
    * Find customer reservation details given reservation ID.
    * @param cust_id The ID of the reservation.
    * @return The reservation details given reservation ID and return null if not found.
    */
    public static ReservationDetails findReservation(int cust_id) {
        int index;
        for (int i = 0; i < NO_OF_TABLES; i++) {
            index = tables[i].findBooking(cust_id);
            if (index == -1) {
                continue;
            }
            else {
                return tables[i].getReservationDetails(index);
            }
        }
        return null;
    }
    /**
    * Remove reservation given reservation ID
    * @param cust_id The ID of the reservation.
    * @return True if removal is successful. False if ID not found or customer has already checked into the restaurant.
    */
    public static boolean removeReservation(int cust_id) {
        int index;
        for (int i = 0; i < NO_OF_TABLES; i++) {
            index = tables[i].findBooking(cust_id);
            if (index == -1) {
                continue;
            } else {
                if (tables[i].isOccupied()) {
                    return false;
                }
                else {
                    tables[i].removeBooking(index);
                    scheduler.removeSchedule(cust_id);
                    return true;
                }
            }
        }
        return false;
    }
    /**
    * Checks in customer given reservation ID.
    * @param cust_id The ID of the reservation.
    * @param staff_name The name of the staff who checked in the customer.
    * @return The acknowledgement string if checking in is successful. Else, return string to show unsuccessful check in.
    */
    public static String checkInReservation(int cust_id, String staff_name) {
        String output;
        ReservationDetails cust_details = findReservation(cust_id);
        if (cust_details == null) {
            return "Invalid id!";
        }
        int table_no = cust_details.getTableNo();

        if (!tables[table_no].isOccupied()) {
            LocalDateTime reserved_date = cust_details.getBookingTime();
            if (reserved_date.isAfter(LocalDateTime.now().minusMinutes(15)) &&
                    reserved_date.isBefore(LocalDateTime.now().plusMinutes(RESERVATION_TIMEOUT_MINUTES))) {
                //Benedict's class
                cust_arr[cust_arr_size] = new Customer(cust_details.getMembership(), 1000, staff_name, cust_id,
                        DateIO.dateToString(cust_details.getBookingTime()), cust_details.getNoOfPax(), cust_details.getCustName());
                cust_arr_size++;

                no_occupied_tables++;
                tables[table_no].custCheckIn(cust_id);

                scheduler.removeSchedule(cust_id);
                Duration schedule_delay = Duration.between(LocalDateTime.now(), cust_details.getBookingTime().plusHours(DINE_IN_DURATION_HOUR));
                scheduler.insertSchedule(cust_id, schedule_delay);

                output = "Customer " + cust_details.getCustName() + " checked in successfully\n";
            }
            else {
                output = "Check-in fail. Check-in is only allowed 15 minutes prior to reservation.\n" +
                        "Current time now is " + DateIO.dateToString(LocalDateTime.now());
            }
        }
        else {
            output = "Check-in fail, table is still not available for check-in!\n";
        }
        return output;
    }
    /**
    * Checks out customer given reservation ID.
    * @param cust_id The ID of the reservation.
    * @param confirmation The character to confirm to check out.
    * @return True if checking out is successful.
    */
    public static boolean checkOutReservation(int cust_id, char confirmation) {
        if (confirmation != 'Y') {
            return false;
        }
        else {
            for (int i = 0; i < cust_arr_size; i++) {
                if (cust_arr[i].getId() == cust_id) {
                    cust_arr[i].PrintInvoice();
                    cust_arr[i] = null;
                    if (i != cust_arr_size-1) {
                        cust_arr[i] = cust_arr[cust_arr_size-1];
                        cust_arr[cust_arr_size-1] = null;
                    }
                    cust_arr_size--;

                    int table_no = findReservation(cust_id).getTableNo();
                    tables[table_no].endOrder();
                    no_occupied_tables--;

                    scheduler.removeSchedule(cust_id);
                    return true;
                }
            }
            return false;
        }
    }
    /**
    * Reset successful reservation counts to 0.
    */
    public static void resetCustID() {
        no_reservation_successful_attempts = 0;
    }

    /**
    * Gets the number of customers who have checked in.
    * @return The number of customers checked in.
    */
    public static int getSize(){
        return cust_arr_size;
    }
    /**
    * Gets the array of customers who have checked in.
    * @return The array of customers checked in.
    */
    public static Customer[] getArr(){
        return cust_arr;
    }

}