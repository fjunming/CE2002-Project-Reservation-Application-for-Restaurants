/**
 Class to handle the user input from the Reservation Menu.
 @ author Foo Jun Ming
 @ version 1.0
 @ since 2021-11-13
*/

import java.time.LocalDateTime;
import java.util.*;

public class ReservationInterface {
  /**
  * Method to print Reservation Menu and handle user input from it.
  * @param reserve Reference to the reservation object.
  */  
  public static void mainMenu(Reservation reserve) {
    String output = "=================================\r\n" +
            "         Reservation Menu	\r\n" +
            "=================================\r\n" +
            "(1) - New Reservation\r\n" +
            "(2) - Check Table Availability\r\n" +
            "(3) - Check Current Reservations and Table Occupancy\r\n" +
            "(4) - Find Customer's Reservation\r\n" +
            "(5) - Remove a Reservation\n" +
            "(6) - Back to Main Menu\r\n\n" +
            "Enter your choice: ";

    Scanner sc = new Scanner(System.in);

    while(true) {
      int choice, cust_id;
      System.out.println(output);

      try {
        choice = sc.nextInt();
        switch (choice) {
          case 1:
            System.out.println("Enter number of pax: ");
            int num_pax = sc.nextInt();
            LocalDateTime reserved_date = DateIO.dateInput();
            int table_no = reserve.checkForTable(num_pax, reserved_date);

            if (table_no != -1) {
              System.out.println("Enter customer's name: ");
              String cust_name = sc.next();

              System.out.println("Is the customer a member? (Y/N)");
              char member_input = sc.next().toUpperCase().charAt(0);

              System.out.println("Enter contact number");
              String contact = sc.next();

              System.out.println(reserve.newReservation(table_no, num_pax, cust_name, member_input, contact, reserved_date)); //int table_no, int num_pax, String cust_name, char member_input, String cust_contact, LocalDateTime reserved_date
            } else {
              System.out.println("Sorry, there are no available tables for pax of " + num_pax + "\n");
            }
            break;

          case 2:
            System.out.println(reserve.tablesAvaliable());
            System.out.println("Enter any key to continue...");
            sc.next();
            break;

          case 3:
            reserve.currentTableOccupency();
            reserve.currentReservations();
            System.out.println("Enter any key to continue...");
            sc.next();
            break;

          case 4:
            if (!reserve.currentReservations()) {
              break;
            }
            System.out.println("Enter customer's reservation id:");
            cust_id = sc.nextInt();
            System.out.println(reserve.custReservationDetails(cust_id, false));
            break;

          case 5:
            if (!reserve.currentReservations()) {
              break;
            }
            System.out.println("Enter customer's reservation id to remove:");
            cust_id = sc.nextInt();
            if (reserve.removeReservation(cust_id)) {
              System.out.println("Reservation removed successfully.\n");
            } else {
              System.out.println("Fail to remove reservation. Either customer is still dining or reservation not found!\n");
            }
            break;

          case 6:
            return;

          default:
            System.out.println("Invalid Input!");
            break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid Input! Enter an integer!");
        sc.next();
      }
    }
  }

  /**
  * Prints the output to the user based on the String passed to it.
  * @param output String object to be printed.
  */  
  public static void printOutput(String output) {
    System.out.println(output);
  }
  /**
  * Prints the check in interface when check-in option is selected. Handles the user input for check-in interface.
  * @param reserve Reference to the reservation object.
  * @param staff_id The id of the staff using the app.
  * @param staff_name THe name of the staff using the app.
  */  
  public static void CheckInInterface(Reservation reserve, int staff_id, String staff_name) {
    Scanner sc = new Scanner(System.in);
    reserve.currentTableOccupency();
    if (!reserve.currentReservations()) {
      return;
    }
    System.out.println("Enter reservation id to check in: ");
    int cust_id = sc.nextInt();
    String output = reserve.checkInReservation(cust_id, staff_name);
    System.out.println(output);
  }
  /**
  * Prints the check out interface when check-out option is selected. Handles the user input for check-out interface.
  * @param reserve Reference to the reservation object.
  */  
  public static void CheckOutInterface(Reservation reserve) {
    Scanner sc = new Scanner(System.in);
    if (!reserve.currentTableOccupency()) {
      return;
    }
    System.out.println("Select reservation id to check out: ");
    int cust_id = sc.nextInt();
    String cust_name = reserve.custReservationDetails(cust_id, true);

    if (cust_name == "Reservation not found!\n") {
      System.out.println("Invalid id!");
    }
    else {
      System.out.println("Confirm to check out customer #" + Integer.toString(cust_id) + " " + cust_name + "?");
      System.out.println("Enter Y to confirm.");
      char confirm = sc.next().toUpperCase().charAt(0);

      if (reserve.checkOutReservation(cust_id, confirm)) {
        System.out.println("Reservation removed successfully.");
      }
      else {
        System.out.println("Reservation not removed!");
      }
    }
  }
}