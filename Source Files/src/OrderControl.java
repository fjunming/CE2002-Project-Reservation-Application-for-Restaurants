/**
 The order controller for the customer's orders.
 @ author Benedict Ee
 @ version 1.0
 @ since 2021-11-13
*/
import java.math.BigDecimal;
import java.io.*;

public class OrderControl {
    //String time = "12/15/20 10:12:20"; //will be inputted by main program
    //String path = "C:\\Users\\Benedict\\IdeaProjects\\RestaurantFinal\\src\\";
  /**
 * The filepath to access the sales information stored in the file.
 */
    //private static String path = System.getProperty("user.dir") + "/data/"; //for replit
    private static String path = System.getProperty("user.dir") + "\\data\\";
  /**
 * Linked list of order items.
 */
    private OrderList order_list;
  /**
 * Menucontrol object needed for accessing its methods
 */
    private MenuControl menu;
/**
 * Date and Time in the format of String for methods' usage.
 */
    private String time;
/**
  * Creates the order controller
  * @param time The date and time when the order list was created.
  */
    public OrderControl(String time){
        this.order_list = new OrderList();
        this.menu = new MenuControl(); //for troubleshooting only
        this.time = time;
    }
/**
 * Adds an item into the order list.
 * @param item_id The id number of the food item to be added.
 * @param quantity The number of food items to be added.
 */
    public void AddItem(int item_id, int quantity){
        this.order_list.insert(order_list, menu.getFood(item_id), quantity);
    }
/**
 * Removes an item from the order list.
 * @param index The index number of the food item in the order list to be removed.
 * @param quantity The number of food items to be removed.
 */
    public void RemoveItem(int index, int quantity){
        this.order_list.remove(order_list, index, quantity);
    }
/**
 * Gets the name of the food in the order list based on the index.
 * @param index The index number of the food item in the order list.
 * @return The name of the food item.
 */
    public String PrintItem(int index){
        return OrderList.getItem(order_list, index);
    }
/**
 * Gets the quantity of the food in the order list based on the index.
 * @param index The index number of the food item in the order list.
 * @return The number of food items in the node.
 */
    public int CheckQuantity(int index){
        return OrderList.getQuantity(order_list, index);
    }
/**
 * Gets the size of the order list.
 * @return The size of the order list.
 */
    public int getSize(){
        return this.order_list.getList_size();
    }
/**
 * Prints the order list.
 */
    public void DisplayOrder(){
        if (this.order_list.getList_size() > 0) {
            System.out.println("The current order is:");
            OrderList.printList(order_list, false);
        }
        else{
            System.out.println("No items in order list!");
        }
    }
/**
 * Prints the invoice upon customer check_out and writes to "sales.txt" file to save a record of sales and items sold.
 * @param member If customer is a member, set to true. Else, set to false.
   @param number_of_pax The number of customers served in this reservation.
   @param staff_name The staff's name who created the customer's order.
   @param reserved_id The reservation ID of the customer with this order.
 */
    public void PrintInvoice(boolean member, int number_of_pax, String staff_name, int reserved_id) {
        int count = 40;

        BigDecimal sub_total = OrderList.getTotal(order_list);
        if (sub_total.equals(0)){
            return;
        }


        System.out.println("\n");
        for (int j=0; j<2; j++){
            for (int i=0; i<count; i++){
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println("          ---Restaurant App---");
        System.out.println("           123 St Jurong Road ");
        System.out.println("           Singapore, S640912 \n\n");
        System.out.printf("Server: %s Date/Time: %s\n", staff_name, time);
        System.out.printf("Reservation ID: %d Client(s): %d\n", reserved_id, number_of_pax);
        for (int i=0; i<count; i++) {
            System.out.print("-");
        }
        System.out.println();

        OrderList.printList(order_list, true);
        for (int i=0; i<count; i++) {
            System.out.print("-");
        }
        System.out.println();

        BigDecimal service = new BigDecimal("1.1");
        BigDecimal gst = new BigDecimal("1.07");
        BigDecimal mem_charge = new BigDecimal("0.0");

        BigDecimal service_total = sub_total.multiply(service);
        BigDecimal total = service_total.multiply(gst);
        BigDecimal service_charge = service_total.subtract(sub_total);
        BigDecimal gst_charge = total.subtract(service_total);

        System.out.printf("    SUBTOTAL..........          %.2f\n", sub_total);
        System.out.printf("    10%% SERVICE CHARGE          %.2f\n", service_charge);
        System.out.printf("%7.2f 7%% GST                  %.2f\n", service_total, gst_charge);
        //System.out.println(gst_charge.setScale(2, BigDecimal.ROUND_UP));

        if (member == false) {
            System.out.printf("  TOTAL DUE.                    %.2f\n", total);
        }
        else{
            BigDecimal mem_reduce = new BigDecimal("0.9");
            BigDecimal mem_total = total.multiply(mem_reduce);
            mem_charge = total.subtract(mem_total).setScale(2, BigDecimal.ROUND_UP);
            System.out.print("  " + total.setScale(2, BigDecimal.ROUND_UP) + "  10% MEMBER'S DISCOUNT -");
            //System.out.printf("%7.2f 10% MEMBER'S DISCOUNT   -", total.setScale(2));
            System.out.println(mem_charge);
            System.out.printf("  TOTAL DUE.                    %.2f\n", mem_total);
            //System.out.println(mem_total.setScale(2, BigDecimal.ROUND_UP));
            total = mem_total;
        }
        System.out.println("Thank you for dining with us!!!");

        for (int j=0; j<2; j++){
            for (int i=0; i<40; i++){
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.println("\n");

        //Writing to Sales.txt
        File file = new File(path + "Sales.txt");
        //File file = new File("Sales.txt");
        try {
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write("\n" + "Date/Time:\n");
            br.write(time + "\n");
            br.write("Order List:\n");
            br.write(OrderList.getOrderList(order_list));
            br.write("Subtotal:\n");
            br.write(sub_total + "\n");
            br.write("Service Charge:\n");
            br.write(service_charge + "\n");
            br.write("GST:\n");
            br.write(gst_charge + "\n");
            br.write("Membership Discount:\n");
            br.write(mem_charge + "\n");
            br.write("Total Charge:\n");
            br.write(total + "\n\n");

            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("File not found in path! (Sales.txt)");
            e.printStackTrace();
        }

    }

}
