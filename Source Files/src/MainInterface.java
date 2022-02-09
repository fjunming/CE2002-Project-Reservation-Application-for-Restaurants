/**
The main interface of the program. Handles user input for initial startup and Main Menu.
@authors Benedict Ee, Foo Jun Ming, Nah Zheng Rong, Yeong Ka Shing Linus
@version 1.0\
@since 13-11-2021
*/
import java.util.*;

public class MainInterface {
/**
  * To be executed by the main program to display the main menu.
  */
  public static void run() {
    Scanner sc = new Scanner(System.in);
    MainController control = new MainController();
    Login login = new Login();
    String name = null;
    int id = 0;
    int choice = 0;
    boolean flag = true;
    do {
      System.out.println("Enter your staff ID:");
      if (sc.hasNextInt()) {
        id = sc.nextInt();
      }
      name = login.read(id);
      if (name == null) {
        System.out.println("ID not found!");
      }
      else {
        flag = false;
      }
      sc.nextLine();
    } while (flag);
    
    
    System.out.println("Login Successful! Welcome " + name);
    String output = 
              "=================================\r\n" + 
              "         Welcome to RRPSS!	\r\n" + 
              "=================================\r\n" +
              "(1) - Customers Check-in\r\n" +
              "(2) - Customers Check-out\r\n" +
              "(3) - Reservation Menu\r\n" +
              "(4) - Customer Menu\r\n" +
              "(5) - Food Menu\r\n" +
              "(6) - Staff Menu\r\n" +
              "(7) - Sales Report\r\n" +
              "(8) - Exit\r\n\n" +
              "Enter your choice: ";

    do{
      System.out.println(output);

      do {
          if (sc.hasNextInt()) {
              choice = sc.nextInt();
              if (choice <= 0 || choice > 8){
                  System.out.println("Please input values between 1 to 8 only!");
              }
              else{
                  break;
              }
          }
          else {
              System.out.println("Please input only integers!");
              sc.next();
          }
      }while (choice <= 0 || choice > 8);        

      if (choice < 8){
        control.callInterface(choice, id, name);  
      }      
    
    } while (choice < 8);
    System.out.println("Exiting RRPSS! Have a nice day!");
    System.exit(0);
  }
}