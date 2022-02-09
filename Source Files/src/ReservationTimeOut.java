/**
 A Runnable class to remove reservations and dining in that exceeds their timings.
 @ author Foo Jun Ming
 @ version 1.0
 @ since 2021-11-13
*/


public class ReservationTimeOut implements Runnable{
    /**
    * Stores the customer's id.
    */  
    private int cust_id;
    /**
    * Status on whether the customer is currently dining in or has yet to dine it. This will determine which method in the Reservation Class to execute.
    */  
    private boolean checked_in;
    /**
    * Creates the runnable task object for the scheduler in ReservationScheduler to execute. Every task is different based on their customer's id and status.
    @param id The customer's id.
    @param status The status of the customer's reservation.
    */     
    public ReservationTimeOut(int id, boolean status) {
        cust_id = id;
        checked_in = status;
    }
    /**
    * Method to run the task.
    */  
    public void run() {
        if (checked_in) {
            if (Reservation.checkOutReservation(cust_id, 'Y')) {
                System.out.println("Customer #" + cust_id + "have been automatically checked-out due to dine-in time limit");
            }
            else {
                System.out.println("Error in executing dine-in time limit task.");
            }
        }
        else if (Reservation.removeReservation(cust_id)) {
            System.out.println("Removed #" + cust_id + " reservation due to 15 minutes timeout.");
        }
        else {
            System.out.println("Fail to remove reservation #" + cust_id + " for timeout.");
        }
    }
}
