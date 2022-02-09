/**
 Stores the various details pertaining to the customer.
 @ author Foo Jun Ming
 @ version 1.0
 @ since 2021-11-13
*/
import java.time.LocalDateTime;

public class ReservationDetails implements Comparable<ReservationDetails>{
    /**
    * Customer's name.
    */  
    private String cust_name;
    /**
    * Customer's reservation id.
    */  
    private int cust_id;
    /**
    * Customer's membership status.
    */  
    private boolean member;
    /**
    * The number of pax the customer is reserving a table for.
    */  
    private int no_of_pax;
    /**
    * Customer's contact number.
    */  
    private String contact;
    /**
    * The start timing of the customer's reservation.
    */  
    private LocalDateTime booking_time;
    /**
    * Customer's reserved table number.
    */  
    private int table_no;
    /**
    * Creates the ReservationDetails object based on the customer's details input.
    * @param table The table number assigned to this Customer.
    * @param id The Customer's id.
    * @param name The Customer's name.
    * @param mem The Customer's membership status.
    * @param pax The number of pax the customer is reserving a table for.
    * @param phone The contact number of the customer.
    * @param start The start timing of the customer's reservation.
    */  
    public ReservationDetails(int table, int id, String name, boolean mem, int pax, String phone, LocalDateTime start) {
        cust_id = id;
        cust_name = name;
        member = mem;
        no_of_pax = pax;
        contact = phone;
        booking_time = start;
        table_no = table;
    }
    /**
    * Get customer's membership status.
    * @return member The status of customer's membership
    */  
    public boolean getMembership() {
        return member;
    }
    /**
    * Get number of pax the customer is reserving a table for.
    * @return no_of_pax The number of pax the table is reserved for.
    */  
    public int getNoOfPax() {
        return no_of_pax;
    }
    /**
    * Get contact number of the customer.
    * @return contact The contact number of the customer.
    */  
    public String getContact() {
        return contact;
    }
    /**
    * Get start timing of the customer's reservation.
    * @return booking_time The start timing of the customer's reservation.
    */  
    public LocalDateTime getBookingTime() {
        return booking_time;
    }
    /**
    * Get customer's name.
    * @return cust_name The name of the customer.
    */  
    public String getCustName() {
        return cust_name;
    }
    /**
    * Get customer's id.
    * @return cust_id The customer's reservation id.
    */  
    public int getCust_id() {
        return cust_id;
    }
    /**
    * Get customer's reserved table number.
    * @return table_no The customer's reserved table number
    */  
    public int getTableNo() {
        return table_no;
    }
    /**
    * Method to compare which ReservationDetails of different customer have an earlier or later booking. This is used to sort the ReservationDetails list.
    * A return Integer of 1 means the current ReservationDetails object is later, while return of -1 means it is earlier than the one being compared to.
    * @param o The ReservationDetails object
    * @return An integer stating whether the current ReservationDetails object is earlier or later than the one being compared.
    */  
    public int compareTo(ReservationDetails o) {
        int compare = this.getBookingTime().compareTo(o.getBookingTime());
        if (compare > 0) {
            return 1;
        }
        else if (compare < 0) {
            return -1;
        }
        else
            return 0;
    }
}
