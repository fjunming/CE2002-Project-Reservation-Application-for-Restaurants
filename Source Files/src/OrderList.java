/**
 The order list controller that emulates a linked list structure.
 @ author Benedict Ee
 @ version 1.0
 @ since 2021-11-13
*/
import java.io.*;
import java.math.BigDecimal;

public class OrderList {
  /**
 * The starting node of the linked_list
 */
    private Node head;
    /**
 * The size of the linked list
 */
    private int list_size;
/**
 * Creates the order list
 */
    public OrderList(){
        this.head = null;
        this.list_size = 0;
    }
/**
 * Inserts a new node or increment the quantity of an existing node in the order list.
 * @param order_list The list of order nodes.
 * @param item The food item to be added into the node.
 * @param quantity The number of food items to be added into the node.
 * @return The updated order list.
 */
    public OrderList insert(OrderList order_list, Food item, int quantity){
        Node cur_node = order_list.head;
        while(cur_node != null){
            if (cur_node.food == item){
                cur_node.quantity += quantity;
                return order_list;
            }
            cur_node = cur_node.next;
        }

        Node new_node = new Node(item, quantity);
        new_node.next = null;

        if(order_list.head == null){
            order_list.head = new_node;
        }
        else{
            Node last = order_list.head;
            while(last.next != null){
                last = last.next;
            }
            last.next = new_node;
        }
        list_size += 1;
        return order_list;
    }
/**
 * Removes a node or decrement the quantity of an existing node from the order list.
 * @param order_list The list of order nodes.
 * @param index The index number of the food node in the order list to be removed.
 * @param quantity The number of food items to be removed from the node.
 * @return The updated order list.
 */
    public OrderList remove(OrderList order_list, int index, int quantity){
        Node curr_node = order_list.head;
        Node prev_node = order_list.head;

        if (index == 0){
            if (curr_node.quantity <= quantity) {
                order_list.head = order_list.head.next;
                list_size -= 1;
            }
            else{
                curr_node.quantity -= quantity;
            }
            return order_list;

        }

        for (int i=0; i<index; i++){
            prev_node = curr_node;
            curr_node = curr_node.next;
        }

        if (curr_node.quantity <= quantity) {
            prev_node.next = curr_node.next;
            list_size -= 1;
        }
        else{
            curr_node.quantity -= quantity;
        }
        return order_list;
    }
/**
 * Prints the order list, with or without the prices included.
 * @param order_list The list of order nodes.
 * @param invoice if invoice is true, prices of items are printed. Else, prices are not printed.
 */
    public static void printList(OrderList order_list, boolean invoice){
        Node curr_node = order_list.head;
        int count = 1;

        if (invoice == false){
            while(curr_node != null){
                System.out.printf("%d. %d %s(s)\n", count++, curr_node.quantity, curr_node.food.getName());
                curr_node = curr_node.next;
            }
        }
        else{
            while(curr_node != null){
                System.out.printf("%d. %3d %21s(s) %.2f\n", count++, curr_node.quantity, curr_node.food.getName(), (curr_node.food.getPrice()*curr_node.quantity));
                curr_node = curr_node.next;
            }
        }
    }
/**
 * Gets a string of the entire order list to be written into "sales.txt" for recording.
 * @param order_list The list of order nodes.
 * @return The string of the entire order list with its prices of each item.
 */
    public static String getOrderList(OrderList order_list){
        Node curr_node = order_list.head;
        String result = "";
        int count = 1;

        while(curr_node != null){
            String temp = (curr_node.quantity + "," + curr_node.food.getName() + "," + curr_node.food.getPrice()*curr_node.quantity + "\n");
            result = result.concat(temp);
            curr_node = curr_node.next;
        }

        return result;
    }
/**
 * Gets the name of the food item in the order list based on its index in the list.
 * @param order_list The list of order nodes.
 * @param index The index of the item in the order list to be accessed.
 * @return The name of the food item in the order list node.
 */
    public static String getItem(OrderList order_list, int index){
        Node curr_node = order_list.head;
        for (int i=0; i<index; i++){
            curr_node = curr_node.next;
        }
        return curr_node.food.getName();
    }
/**
 * Gets the quanity of the food item in the order list based on its index in the list.
 * @param order_list The list of order nodes.
 * @param index The index of the item in the order list to be accessed.
 * @return The number of food items in the order list node.
 */
    public static int getQuantity(OrderList order_list, int index){
        Node curr_node = order_list.head;
        for (int i=0; i<index; i++){
            curr_node = curr_node.next;
        }
        return curr_node.quantity;
    }
/**
 * Gets the subtotal of the entire order.
 * @param order_list The list of order nodes.
 * @return the subtotal of the items in the order list
 */
    public static BigDecimal getTotal(OrderList order_list){
        BigDecimal total = new BigDecimal("0");
        Node curr_node = order_list.head;

        while(curr_node != null){
            total = total.add(new BigDecimal(curr_node.food.getPrice() * curr_node.quantity));
            curr_node = curr_node.next;
        }
        return total;
    }
/**
 * Gets the size of the order list.
 * @return The size of the order list.
 */
    public int getList_size(){
        return this.list_size;
    }
}
