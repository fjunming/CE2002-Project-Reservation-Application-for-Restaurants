/**
 The controller for logging in a staff at the main menu.
 @ author Yeong Ka Shing Linus
 @ version 1.0
 @ since 2021-11-13
*/
import java.io.*;
import java.util.*;

public class Login {
  /**
 * The filepath to access the stafflist stored in the file.
 */
  //private static String path = System.getProperty("user.dir") + "/data/"; //for replit
  private static String path = System.getProperty("user.dir") + "\\data\\";
/**
 * Creates the Login controller.
 */
  public Login() {}
  /**
 * Reads from "stafflist.txt" file and extracts name corresponding to id
 */
  public String read(int id) {
		
		try {
			//File file = new File("stafflist.txt");
			File file = new File(path + "stafflist.txt");
			Scanner sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				String test = sc.nextLine();
				String temp[] = test.split(",");
				String name = temp[0];
				int employeeId = Integer.parseInt(temp[2]);
				if (employeeId == id) {
					return name;
				}
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Check file path!");
		}

    return null;
	}
}