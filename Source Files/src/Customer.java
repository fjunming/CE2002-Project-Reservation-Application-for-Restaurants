/**
Represents a customer
@author Benedict Ee
@version 1.0\
@since 13-11-2021
*/

import java.util.*;

public class Customer {
    /**
 * True represents the customer is a member. Else, the customer is not a member.
 */
    private boolean member;
    /**
 * The ID of the staff who created the customer order list.
 */
    private int staff_id;
    /**
 * The name of the staff who created the customer order list.
 */
    private String staff_name;
    /**
 * The reservation id of the customer.
 */
    private int reserved_id;
    /**
 * The time of reservation of the customer.
 */
    private String reserved_time;
    /**
 * The number of customers to be served in that reservation instance.
 */
    private int number_of_pax;
    /**
 * The order list of the customer.
 */
    public OrderControl order;
    /**
 * The name of the customer.
 */
    private String name;
    //private int contact;
/**
Creates a customer
@param member If true, customer is a member. Else, customer is not a member.
@param staff_id The ID of the staff who created the customer order list.
@param staff_name The name of the staff who created the customer order list.
@param reserved_id The reservation id of the customer.
@param reserved_time The time of reservation of the customer.
@param number_of_pax The number of customers to be served in that reservation instance.
@param name The name of the customer.
*/
    public Customer(boolean member, int staff_id, String staff_name, int reserved_id, String reserved_time, int number_of_pax, String name){
        this.member = member;
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.reserved_id = reserved_id;
        this.reserved_time = reserved_time;
        this.number_of_pax = number_of_pax;
        this.name = name;
        this.order = new OrderControl(reserved_time);
    }
/**
 * Prints the invoice upon customer check_out.
 */
    public void PrintInvoice(){
        order.PrintInvoice(this.member, this.number_of_pax, this.staff_name, this.reserved_id);
    }
/**
 * Gets the name of the customer.
 * @return the name of the customer.
 */
    public String getName(){
      return this.name;
    }
/**
 * Gets the reservation id of the customer.
  * @return the reservation id of the customer.
 */
    public int getId() {
      return this.reserved_id;
    }
/**
 * Gets the number of customers in the reservation instance.
  * @return the number of customers in the reservation instance.
 */
    public int getSize(){
      return this.number_of_pax;
    }
/**
 * Gets the staff's name who created the customer's order.
  * @return the name of the staff who created the customer's order.
 */
    public String getStaffName(){
      return this.staff_name;
    }

}
