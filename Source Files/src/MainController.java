/**
The main controller of the program. Handles program flow and initializes classes during startup.
@authors Benedict Ee, Foo Jun Ming, Nah Zheng Rong, Yeong Ka Shing Linus
@version 1.0\
@since 13-11-2021
*/
import java.util.*;
import java.io.*;

public class MainController {
  /**
  * The controller for food menu.
  */
  private MenuControl mc;
  /**
  * The controller for reservation menu.
  */
  private Reservation reserve;
  /**
  * The interface for staff menu.
  */
  private StaffInterface staffInterface;
  /**
  * The filepath to access the number of different sized tables stored in the file.
  */
  //private static String path = System.getProperty("user.dir") + "/data/"; //for replit
  private static String path = System.getProperty("user.dir") + "\\data\\";
  /**
  * Creates the menu controller
  */
  public MainController() {
    
    int[] arr = importTables();
    mc = new MenuControl();
    reserve = new Reservation(arr);
    Scanner sc = new Scanner(System.in);
    staffInterface = new StaffInterface();
  }

  /**
  * Calls the respectively interfaces based on the user input from the main interface.
  * @param input The number used to determine which interface to be called to execute.
  * @param id The staff id of the staff who logged into the system.
  * @param name The staff name of the staff who logged into the system.
  */
  public void callInterface(int input, int id, String name){
    switch(input) {
      case 1:
        ReservationInterface.CheckInInterface(reserve, id, name);
        break;
      case 2:
        ReservationInterface.CheckOutInterface(reserve);
        break;
      case 3:
        ReservationInterface.mainMenu(reserve);
        break;
      case 4:
        OrderInterface.menuOutput(reserve.getSize(), reserve.getArr(), mc);
        break;
      case 5:
        MenuInterface.menuOutput();
        break;
      case 6:
        staffInterface.menuOutput(id);
        break;
      case 7:
        SalesReport.print();
        break;
      case 8:
        System.exit(0);
      default:
        break;
    }
  }

  /**
  * Construct and return an array of integers with information regarding the number of tables and its size.
  * This information is obtained from reading the tables.txt file stored in the data folder.
  * @return An array of integers, each integer representing number of seats and the size of the array is the number of tables.
  */
  public int[] importTables() {
    try {
      File tables_import = new File(path + "tables.txt");
      Scanner fileReader = new Scanner(tables_import);
      int count = 2;
      ArrayList<Integer> tables_list = new ArrayList<>();

      while (fileReader.hasNextLine()) {
        String input = fileReader.nextLine();
        if (input.charAt(0) == '#') {
          continue;
        }
        else {
          int i = Integer.parseInt(input);
          for (;i > 0; i--) {
            tables_list.add(count);
          }
          count += 2;
        }
      }
      fileReader.close();

      int[] tables_arr = new int[tables_list.size()];
      int j = 0;
      while (tables_list.size() > 0) {
        tables_arr[j] = tables_list.remove(0);
        j++;
      }

      return tables_arr;
    } catch (IOException e) {
      System.out.println("An error occurred while reading file: tables.txt");
      e.printStackTrace();
      System.exit(0);
      return null;
    }
  }
}