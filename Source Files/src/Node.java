/**
Represents a node in the order list 
@author Benedict Ee
@version 1.0\
@since 13-11-2021
*/
public class Node{
  /**
  * The food item in the node
  */
    Food food;
    /**
  * The number of food items within the node
  */
    int quantity;
    /**
  * The pointer to the next node
  */
    Node next;
/**
  * Creates the food node in the order list.
  * @param food The food object to be stored in the node.
  * @param quantity The number to represent the food items stored in the node.
  */
    Node(Food food, int quantity){
        this.food = food;
        this.quantity = quantity;
        this.next = null;
    }
}
