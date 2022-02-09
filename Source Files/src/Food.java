/**
Represents a food item in the menu
@author Nah Zheng Rong
@version 1.0\
@since 13-11-2021
*/
public class Food {
  /**
  * The name of the food
  */
	String name;
  /**
  * The description of the food
  */
	String desc;
  /**
  * The price of the food
  */
	double price;
  /**
  * The type of the food
  */
	String type;
/**
 * Creates a new food item.
 * @param foodName The food name.
 * @param foodDesc The food description.
 * @param foodPrice The price of the food.
 * @param foodType The type of the food.
 */
	public Food(String foodName,String foodDesc,double foodPrice,String foodType) {
		name=foodName;
		price=foodPrice;
		desc=foodDesc;
		type=foodType;
	}
	public Food() {
		
	}
  /**
  * Get the price of the food.
  *@return The price of the food
  */
	public double getPrice() {
		return price;
	}
   /**
  * Get the name of the food.
  *@return The name of the food
  */
	public String getName() {
		return name;
	}
   /**
  * Get the type of the food.
  *@return The type of the food
  */
	public String getType() {
		return type;
	}
   /**
  * Get the description of the food.
  *@return The description of the food
  */
	public String getDesc() {
		return desc;
	}
   /**
  * Update the price of the food.
  *@param newPrice The new price of the food
  */
	public void updatePrice(double newPrice) {

		price=newPrice;
		System.out.println("Price changed to "+price);
	}
   /**
  * Update the name of the food.
  *@param newName The new name of the food
  */
	public void updateName(String newName) {
	
		name=newName;
		System.out.println("Name changed to "+name);
	
	
	}
  /**
  * Update the description of the food.
  *@param newDesc The new description of the food
  */
	public void updateDesc(String newDesc) {

		desc= newDesc;
		System.out.println("Description changed to "+desc);

	}
  /**
  * Update the type of the food.
  *@param newType The new type of the food
  */
	public void updateType(String newType) {

		type= newType;
		System.out.println("Type changed to "+type);
	
	}
}
