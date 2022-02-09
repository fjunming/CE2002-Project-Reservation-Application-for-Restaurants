/**
 The Order interface for the customer order menu.
 @ author Benedict Ee
 @ version 1.0
 @ since 2021-11-13
*/
import java.util.*;

public final class OrderInterface {
    /**
    * Creates the order interface
    */
    private OrderInterface(){
    }
    /**
    * Prints out the customer order menu.
    * @param cust_arr_size The number of checked in customers.
    * @param cust_arr The list of checked in customers.
    * @param mc The menu controller to access menu methods.
    */
    public static void menuOutput(int cust_arr_size, Customer[] cust_arr, MenuControl mc){
        int choice;
        int cust = 0;
        int max = 3;
        int count;

        Scanner sc = new Scanner(System.in);

        if (cust_arr_size > 0){

            do{
                choice = 0;
                count = 0;
                System.out.println( "=================================");
                System.out.println("Welcome to the Order Menu:");
                System.out.println( "=================================");
                System.out.println("Current customer(s) list:");
                for (int i=0; i<cust_arr_size; i++){
                    System.out.printf("(%d): %s, %d pax.\n", (i+1), cust_arr[i].getName(), cust_arr[i].getSize());
                }
                System.out.printf("(%d): Exit to main menu\n", (cust_arr_size+1));
                System.out.println("\nPlease Select the customer to edit order: ");

                /// Exception handling for input of customer index
                do {
                    if (sc.hasNextInt()) {
                        cust = sc.nextInt();
                        if (cust <= 0 || cust > cust_arr_size+1){
                            System.out.println("Please input values between 1 to "  + (cust_arr_size+1) + " only!");
                        }
                        else{
                            break;
                        }
                    }
                    else {
                        System.out.println("Please input only integers!");
                        sc.next();
                    }
                    count++;
                    if (count >= max){
                        System.out.println("Max number of tries exceeded! Returning to main menu.");
                        choice = 4;
                    }
                }while (cust <= 0 || (cust > cust_arr_size+1));
                if (cust == (cust_arr_size+1)){
                  break;
                }
                cust -= 1;

                while(choice != 4 && cust < cust_arr_size) {
                    count = 0;
                    choice = 0;

                    System.out.printf("Current Customer: %s\n\n", cust_arr[cust].getName());
                    System.out.printf("Order Created by: %s\n\n", cust_arr[cust].getStaffName());
                    System.out.println("(1) - Display current order");
                    System.out.println("(2) - Add item to current order");
                    System.out.println("(3) - Remove item from current order");
                    System.out.println("(4) - Return to main menu");
                    System.out.println("(5) - Return to customer selection menu");
                    System.out.println("Please Input your choice:");

                    do {
                        if (sc.hasNextInt()) {
                            choice = sc.nextInt();
                            if (choice <= 0 || choice > 5){
                                System.out.println("Please input values between 1 to 5 only!");
                            }
                            else{
                                break;
                            }
                        }
                        else {
                            System.out.println("Please input only integers!");
                            if (count+1 < max) {
                                sc.next();
                            }
                        }
                        count++;
                        if (count >= max){
                            System.out.println("Max number of tries exceeded! Returning to main menu.");
                            choice = 4;
                        }
                    }while (choice <= 0 || choice > 5);

                    int item_id = 0;
                    int quantity = 0;
                    int tries;
                    int max_tries = 3;

                    switch(choice){
                        case 1:
                            cust_arr[cust].order.DisplayOrder();
                            break;
                        case 2:
                            mc.printMenu(); //Display menu for reference ***
                            tries = 0;
                            System.out.println("Input the item id of the item to be added to order:");

                            do {
                                if (sc.hasNextInt()) {
                                    item_id = sc.nextInt();
                                    if (item_id <= 0 || item_id > mc.getLength()){
                                        System.out.println("Please input values between 1 to " + mc.getLength() + " only!");
                                    }
                                    else{
                                        break;
                                    }
                                }
                                else {
                                    System.out.println("Please input only integers!");
                                    if (tries+1 < max_tries) {
                                        sc.next();
                                    }
                                }
                                tries++;
                                if (tries >= max_tries){
                                    System.out.println("Max number of tries exceeded! Returning to main menu.");
                                    choice = 4;
                                }
                            }while (item_id <= 0 || item_id > mc.getLength() && choice !=4);
                            if (choice == 4){
                                break;
                            }

                            ///Inputting of quantity of items to be added to order & Exception handling
                            tries = 0; //resetting no. of tries
                            System.out.println("Input the number of the items to be added to order:");

                            do {
                                if (sc.hasNextInt()) {
                                    quantity = sc.nextInt();
                                    if (quantity <= 0){
                                        System.out.println("Please input valid number(more than 0)!");
                                    }
                                    else{
                                        break;
                                    }
                                }
                                else {
                                    System.out.println("Please input only integers!");
                                    if (tries+1 < max_tries) {
                                        sc.next();
                                    }
                                }
                                tries++;
                                if (tries >= max_tries){
                                    System.out.println("Max number of tries exceeded! Returning to main menu.");
                                    choice = 4;
                                }
                            }while (quantity <= 0 && choice !=4);
                            if (choice == 4){
                                break;
                            }

                            System.out.println("Adding item...");
                            cust_arr[cust].order.AddItem(item_id, quantity);
                            System.out.println("Item added to order!");
                            break;

                        case 3:
                            int index=0;

                            cust_arr[cust].order.DisplayOrder(); //Display current order for reference
                            if (cust_arr[cust].order.getSize() == 0){
                              break;
                            }
                            ///Inputting of index of item to be removed from order list & Exception handling
                            tries = 0; //resetting no. of tries
                            System.out.println("Input the index number from current order list to remove from: ");

                            do {
                                if (sc.hasNextInt()) {
                                    index = sc.nextInt();
                                    if (index <= 0 || index > cust_arr[cust].order.getSize()){
                                        System.out.println("Please input values between 1 to " + cust_arr[cust].order.getSize() + " only!");
                                    }
                                    else{
                                        break;
                                    }
                                }
                                else {
                                    System.out.println("Please input only integers!");
                                    if (tries+1 < max_tries) {
                                        sc.next();
                                    }
                                }
                                tries++;
                                if (tries >= max_tries){
                                    System.out.println("Max number of tries exceeded! Returning to main menu.");
                                    choice = 4;
                                }
                            }while (index <= 0 || index > cust_arr[cust].order.getSize() && choice !=4);
                            if (choice == 4){
                                break;
                            }

                            index -= 1;
                            ///Inputting of quantity to be removed from index of order list & Exception handling
                            tries = 0; //resetting no. of tries
                            System.out.printf("Input the number of (%s) in the order list to remove: ", cust_arr[cust].order.PrintItem(index));
                            System.out.println();

                            do {
                                if (sc.hasNextInt()) {
                                    quantity = sc.nextInt();
                                    if (quantity <= 0){
                                        System.out.println("Please input valid values(more than 0)!");
                                    }
                                    else{
                                        break;
                                    }
                                }
                                else {
                                    System.out.println("Please input only integers!");
                                    if (tries+1 < max_tries) {
                                        sc.next();
                                    }
                                }
                                tries++;
                                if (tries >= max_tries){
                                    System.out.println("Max number of tries exceeded! Returning to main menu.");
                                    choice = 4;
                                }
                            }while (quantity <= 0 && choice !=4);
                            if (choice == 4){
                                break;
                            }

                            cust_arr[cust].order.RemoveItem(index, quantity);
                            System.out.println("Item removed from order!");
                            break;
                        case 4:
                            //cust_arr[cust].PrintInvoice(); //for troubleshoot
                            cust = cust_arr_size;
                            break;
                        case 5:
                            cust = 0;
                            choice = 4;
                            break;
                    }
                }
            } while(cust_arr_size > cust);
            System.out.println("Returning to main menu...");
        }

        else{
            System.out.println("Error! No customer in reservation list.");
            System.out.println("Returning to main menu...");
        }
    }
}
