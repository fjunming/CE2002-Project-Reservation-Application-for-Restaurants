/**
 The sales report controller.
 @ author Yeong Ka Shing Linus
 @ version 1.0
 @ since 2021-11-13
*/
import java.io.*;
import java.util.*;

public class SalesReport {
  /**
 * The filepath to access the sales information stored in the file.
 */
    //private static String path = System.getProperty("user.dir") + "/data/"; //for replit
    private static String path = System.getProperty("user.dir") + "\\data\\";
    /**
 * Prints the sales report.
 */
  public static void print() {
    Map<Integer, String> numToMonth = new HashMap<>();
    numToMonth.put(1,"January");numToMonth.put(2,"February");numToMonth.put(3,"March");
    numToMonth.put(4,"April");numToMonth.put(5,"May");numToMonth.put(6,"June");
    numToMonth.put(7,"July");numToMonth.put(8,"August");numToMonth.put(9,"September");
    numToMonth.put(10,"October");numToMonth.put(11,"November");numToMonth.put(12,"December");

    Scanner in = new Scanner(System.in);
    int day = -1;
    int month = 0;
    System.out.println("Input the day (1-31) you wish to generate the sales report: ");
    System.out.println("(Input 0 if you wish to generate monthly report) ");
    do {
      if (in.hasNextInt()) {
        day = in.nextInt();
      }
      if (day < 0 || day > 31) {
        System.out.println("Enter valid day!");
        System.out.println("Input the day (1-31) you wish to generate the sales report: ");
        System.out.println("(Input 0 if you wish to generate monthly report) ");
      }
      in.nextLine();
    } while (day <= -1 || day > 31);
        
    System.out.println("Input the month (1-12) you wish to generate the sales report: ");
    do {
      if (in.hasNextInt()) {
        month = in.nextInt();
      }
      if (month <= 0 || month > 12) {
        System.out.println("Enter valid month!");
        System.out.println("Input the month (1-12) you wish to generate the sales report: ");
      }
      in.nextLine();
    } while (month <= 0 || month > 12);

    try {
        File file = new File(path + "Sales.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> food = new ArrayList<String>();
        ArrayList<Integer> quantity = new ArrayList<Integer>();
        List<Double> totalPrice = new ArrayList<Double>();
        String temp;
        double subtotal=0, serviceCharge=0, gst=0, membership=0, revenue=0;

        do {
          String dateHeader = sc.nextLine();
          if (dateHeader.equals("Date/Time:")) {
            temp = sc.nextLine();
            String timeData[] = temp.split("/");
            int dayData = Integer.parseInt(timeData[0]);
            int monthData = Integer.parseInt(timeData[1]);
            sc.nextLine(); // skip order list header
            if ((monthData == month && dayData == day && day != 0) || monthData == month && day == 0) {
                temp = sc.nextLine();
                while(!temp.equals("Subtotal:")) {
                    String foodData[] = temp.split(",");
                    int tempQuantity = Integer.parseInt(foodData[0]);
                    String tempFood = foodData[1];
                    double tempTotalPrice = Double.parseDouble(foodData[2]);

                    if (food.isEmpty()) { // insert first food item
                        food.add(tempFood);
                        quantity.add(tempQuantity);
                        totalPrice.add(tempTotalPrice);
                    }
                    else { // insert subsequent items
                        if (food.contains(tempFood)) {
                            int idx = food.indexOf(tempFood);
                            quantity.add(idx, quantity.get(idx) + tempQuantity);
                            totalPrice.add(idx, totalPrice.get(idx) + tempTotalPrice);
                        }
                        else {
                            food.add(tempFood);
                            quantity.add(tempQuantity);
                            totalPrice.add(tempTotalPrice);
                        }
                    }
                    temp = sc.nextLine();
                }
                subtotal += Double.parseDouble(sc.nextLine());
                sc.nextLine();
                serviceCharge += Double.parseDouble(sc.nextLine());
                sc.nextLine();
                gst += Double.parseDouble(sc.nextLine());
                sc.nextLine();
                membership += Double.parseDouble(sc.nextLine());
                sc.nextLine();
                revenue += Double.parseDouble(sc.nextLine());
            }
          }
        } while(sc.hasNextLine());

        String[] foodArray = new String[food.size()];
        foodArray = food.toArray(foodArray);
        Integer[] quantityArray = quantity.toArray(new Integer[0]);
        Double[] totalPriceArray = new Double[totalPrice.size()];
        totalPriceArray = totalPrice.toArray(totalPriceArray);

        System.out.println("Period: " + numToMonth.get(month));
        System.out.printf("%-16s %-32s %-24s\n", "Quantity", "Food Item", "Respective Sales");
        for (int i=0; i<foodArray.length; i++) {
            System.out.printf("%-16d %-32s %-24.2f\n", quantityArray[i], foodArray[i], totalPriceArray[i]);
        }
        System.out.printf("\nSubtotal: %.2f", subtotal);
        System.out.printf("\nService Charge: %.2f", serviceCharge);
        System.out.printf("\nGST: %.2f", gst);
        System.out.printf("\nMembership: -%.2f", membership);
        System.out.printf("\nRevenue: %.2f\n\n", revenue);
    }
    catch(FileNotFoundException e) {
        System.out.println("Check file path!");
    }
  }
}