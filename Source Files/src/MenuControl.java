/**
 The menu controller for the food menu.
 @ author Nah Zheng Rong
 @ version 1.0
 @ since 2021-11-13
*/
import java.util.*;
import java.io.*;
public class MenuControl{
  /**
 * The array of food items in the menu.
 */
  private ArrayList<Food>f = MenuControl.createMenu();
  /**
 * The number of main course items.
 */
	private int main_course=0;
  /**
 * The number of dessert items.
 */
	private int dessert=0;
  /**
 * The number of drink items.
 */
	private int drink=0;
  /**
 * The number of set items.
 */
	private int promotion=0;
  /**
 * The filepath to access the menu stored in the file.
 */
	//private static String path = System.getProperty("user.dir") + "/data/"; //for replit
  private static String path = System.getProperty("user.dir") + "\\data\\";
  /**
  * Creates the menu controller
  */
  public MenuControl(){
		int l = f.size();
		int i =0;
		for(i=0;i<l;i++) {
			Food temp = this.f.get(i);
			if(temp.getType().equals("Main course")) {
				this.main_course=main_course+1;
			}
			if(temp.getType().equals("Dessert")) {
				this.dessert++;
			}
			if(temp.getType().equals("Drink")) {
				this.drink++;
			}
			if(temp.getType().equals("Promotion set")) {
				this.promotion++;
			}
		}
		
	}
  /**
  * Prints out the entire menu
  */
	public static void printMenu() {
		ArrayList<Food> f = MenuControl.createMenu();
		int a=0;
		String foodType;
		String setSplit[]=new String[10];
		foodType=f.get(a).getType();
        System.out.println("=================================");
				System.out.println("         "+f.get(a).getType());
        System.out.println("=================================");
		System.out.println();
    int menuid;
		while(a<f.size()) {
			
			if(f.get(a).getType().equals(foodType)==false) {
        System.out.println("=================================");
				System.out.println("         "+f.get(a).getType());
        System.out.println("=================================");
				foodType=f.get(a).getType();
				System.out.println();
			}
      menuid=a+1;
			System.out.printf("("+menuid+")"+" - "+f.get(a).getName()+ " -==- $%.2f%n",f.get(a).getPrice());
			if(f.get(a).getType().equals("set")==false)
				System.out.println(f.get(a).getDesc());
			System.out.println();
			
			if(f.get(a).getType().equals("Promotion set")) {
				setSplit=f.get(a).getDesc().split(",");
				int b=0,c=0,d=0;
				while(b<setSplit.length) {
					//c=getID(setSplit[b]);
					for(d=0;d<f.size();d++) {
						if(setSplit[b].equals(f.get(d).getName())) {
						System.out.println(f.get(d).getDesc());
						break;
						}
					}
					b=b+1;
				}
        System.out.println();
			}
			a=a+1;
		}
	}

/**
 * Adds a new food item to the menu.
 * @param name The food name.
 * @param desc The food description.
 * @param price The price of the food.
 * @param type The type of the food.
 */
	public void addFoodItem(String name,String desc,String price,String type) {
    double p = Double.valueOf(price);
		Food newfood = new Food(name,desc,p,type);
		int a;
		if(type.equals("Main course")) {
		f.add(main_course,newfood);
		main_course=main_course+1;
		System.out.println("Added "+newfood.getName()+" to the menu with ID "+main_course);
		}
		if(type.equals("Dessert")) {
			f.add(main_course+dessert,newfood);
			dessert=dessert+1;
			System.out.println("Added "+newfood.getName()+" to the menu with ID "+main_course+dessert);
		}
		if(type.equals("Drink")) {
			f.add(main_course+dessert+drink,newfood);
			drink=drink+1;
			System.out.println("Added "+newfood.getName()+" to the menu with ID "+main_course+dessert+drink);
		}
			editFile();
		}
    /**
 * Adds a new set item to the menu.
 * @param name The set name.
 * @param price The price of the set.
 * @param set[] The list of IDs of food in the set.
 */
    public void addSetItem(String name,String price,int[] set) {
    int a = set.length;
    int b,c;
    for(b=0;b<a;b++) {
      for(c=0;c<a;c++){
        if(set[b]==set[c] && b!=c){
          System.out.println("Duplicate entries in set");
          return;
        }
      }
      if(set[b]>main_course+dessert+drink){
        System.out.println("Cannot add another set to a set");
        return;
      }
      }
    double p = Double.valueOf(price);
		String type="Promotion set";
		String desc = new String();
		String descArray[]=new String[10];
		Scanner sc = new Scanner(System.in);
		String temp= new String();
		for (b=0;b<a;b++) {
			temp=getName(set[b]);
			descArray[b]=temp;
		}
		
		for(b=0;b<a-1;b++) {
			desc=desc+descArray[b];
			desc=desc+",";
		}
    desc=desc+descArray[b];
		
		Food newfood = new Food(name,desc,p,type);
		f.add(f.size(),newfood);
		promotion=promotion+1;
		System.out.println("Added "+newfood.getName()+" to the menu with ID "+f.size());
		editFile();
	}
/**
 * Edit a food item in the menu.
 * @param name The new name of the food.
 * @param desc The new description of the food.
 * @param price The new price of the food.
 * @param Id The ID of the food.
 */
  public void editFoodItem(String name,String desc,String price,int Id) {
    if(f.get(Id-1).getType().equals("Promotion set")){
      System.out.println("Item is a set");
      return;
    }
    if(price.isBlank()==false) {
	  double newp = Double.valueOf(price);
	  f.get(Id-1).updatePrice(newp);
    }
    if(desc.isBlank()==false) {
    	f.get(Id-1).updateDesc(desc);
    }
    
    int a=main_course+dessert+drink;
		while(a<=f.size()) {
			String setDesc = new String();
			setDesc=getDesc(a);
			if(setDesc.contains(getName(Id))) {
				setDesc=setDesc.replaceAll(getName(Id), name);
				f.get(a-1).updateDesc(setDesc);
			}
			a=a+1;
		}
    if(name.isBlank()==false) {
    	  f.get(Id-1).updateName(name);
    }
		editFile();
	}
  /**
 * Edit a set item in the menu.
 * @param name The new name of the set.
 * @param price The new price of the set.
 * @param set[] The list of IDs of food in the set.
 * @param Id The ID of the set.
 */
  public void editSetItem(String name,String price,int[] set,int Id) {
		int b,c;
    int a=set.length;
    if(!f.get(Id-1).getType().equals("Promotion set")){
      System.out.println("Item is not a set");
      return;
    }
    	for(b=0;b<a;b++) {
      for(c=0;c<a;c++){
        if(set[b]==set[c] && b!=c){
          System.out.println("Duplicate entries in set");
          return;
        }
      }
      if(set[b]>main_course+dessert+drink){
         System.out.println("Cannot add another set to set");
          return;
      }
      
      }
		String desc = new String();
		String descArray[]=new String[10];
	
		if(name.isBlank()==false){
		  f.get(Id-1).updateName(name);
    }
    if(price.isBlank()==false){
      double p = Double.valueOf(price);
      f.get(Id-1).updatePrice(p);
    }
		
		String temp= new String();
		
    
		for(b=0;b<a;b++) {
     
			temp=getName(set[b]);
			descArray[b]=temp;
		}
		if(descArray[0]!=null) {
			for(b=0;b<a-1;b++) {
				desc=desc+descArray[b];
				desc=desc+",";
			}
      desc=desc+descArray[b];
			f.get(Id-1).updateDesc(desc);
		}
		
		editFile();
	}
  /**
  * Edits the file once menu has been changed.
  */
  private void editFile() {
		int a;
		try {
		      FileWriter myWriter = new FileWriter(path + "foodname.txt");
		      //FileWriter myWriter = new FileWriter("foodname.txt");
		      for(a=0;a<main_course+dessert+drink+promotion;a++) {
		      myWriter.write(f.get(a).getName()+"\n");
		      }
		      myWriter.close();
		      FileWriter myWriter2 = new FileWriter(path + "desc.txt");
			  //FileWriter myWriter2 = new FileWriter("desc.txt");
		      for(a=0;a<main_course+dessert+drink+promotion;a++) {
		      myWriter2.write(f.get(a).getDesc()+"\n");
		      }
		      myWriter2.close();
		      FileWriter myWriter3 = new FileWriter(path + "type.txt");
		      //FileWriter myWriter3 = new FileWriter("type.txt");
		      for(a=0;a<main_course+dessert+drink+promotion;a++) {
		      myWriter3.write(f.get(a).getType()+"\n");
		      }
		      myWriter3.close();
		      FileWriter myWriter4 = new FileWriter(path + "price.txt");
			  //FileWriter myWriter4 = new FileWriter("price.txt");
		      for(a=0;a<main_course+dessert+drink+promotion;a++) {
		      myWriter4.write(f.get(a).getPrice()+"\n");
		      }
		      myWriter4.close();
		    
		    }
         catch (IOException e) {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		    }
	}
  /**
  * Removes a food item from the menu
  * @param Id The ID of the food to be removed
  */
  	public void deleteItem(int Id) {
		if(f.get(Id-1).getType().equals("Main course")){
			main_course=main_course-1;
		}
		if(f.get(Id-1).getType().equals("Dessert")){
			dessert=dessert-1;
		}
		if(f.get(Id-1).getType().equals("Drink")){
			drink=drink-1;
		}
		if(f.get(Id-1).getType().equals("Promotion set")){
			promotion=promotion-1;
		}
		int a=main_course+dessert+drink;
		while(a<=f.size()) {
			String setDesc = new String();
			setDesc=getDesc(a);
			if(setDesc.contains(getName(Id))) {
				setDesc=setDesc.replaceAll(getName(Id)+",", "");
				f.get(a-1).updateDesc(setDesc);
			}
			a=a+1;
		}
		f.remove(Id-1);
		System.out.println("Item removed");
		editFile();
	}
   /**
  * Creates the food menu by reading the files
  * @return The list of food items in the menu
  */
  public static ArrayList<Food> createMenu(){
    int a=0;
    ArrayList<Food> f=new ArrayList<Food>();
    String name[] = new String[30];
		String desc[] = new String[30];
		String type[] = new String[30];
		double price[] = new double[30];
		   try {
			      //File myObj = new File("foodname.txt");
			   File myObj = new File(path + "foodname.txt");
			      Scanner myReader = new Scanner(myObj);
			      while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        name[a]=data;
			        a=a+1;
			        
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		   a=0;
		   try {
			      //File myObj = new File("type.txt");
			   File myObj = new File(path + "type.txt");
			      Scanner myReader = new Scanner(myObj);
			      while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        type[a]=data;
			        a=a+1;
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		   a=0;
		   try {
			      //File myObj = new File("desc.txt");
			   File myObj = new File(path + "desc.txt");
			      Scanner myReader = new Scanner(myObj);
			      while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        desc[a]=data;
			        a=a+1;
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		   a=0;
		   try {
			      // myObj = new File("price.txt");
			   File myObj = new File(path + "price.txt");
			      Scanner myReader = new Scanner(myObj);
			      while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        price[a]=Double.valueOf(data);
			        a=a+1;
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		   a=0;
		 
		   while(name[a]!=null) {
			  Food newfood= new Food(name[a],desc[a],price[a],type[a]);
			  f.add(newfood);
			   a=a+1;
		   }
	  		//System.out.println(a);
		   a=0;
		   return f;
  }
   /**
  * Get the price of the food.
  *@param Id The ID of the food
  *@param quantity The quantity of food.
  *@return The price of the food
  */
	public double getPrice(int Id,int quantity) {
		return quantity*f.get(Id-1).getPrice();
	}
     /**
  * Get the price of the food.
  *@param Id The ID of the food
  *@return The price of the food
  */
	public double getPrice(int Id) {
		return f.get(Id-1).getPrice();
	}
   /**
  * Get the name of the food.
  *@param Id The ID of the food
  *@return The name of the food
  */
	public String getName(int Id) {
		return f.get(Id-1).getName();
	}
   /**
  * Get the description of the food.
  *@param Id The ID of the food
  *@return The description of the food
  */
	public String getDesc(int Id) {
		return f.get(Id-1).getDesc();
	}
  /**
  * Get the size of the menu.
  *@return The number of items in the menu
  */
  public int getLength(){
    return f.size();
  }
  /**
  * Get the ID of the food.
  *@param name The name of the food
  *@return The ID of the food
  */
	public int getID(String name) {
		int a;
		for(a=0;a<f.size();a++) {
			if(name.equals(f.get(a).getName())) {
				return a+1;
			}
		}
		return 0; //item not found
	}

  //added by Ben for customer class
  /**
  * Get the food item.
  *@param Id The ID of the food
  *@return The food object
  */
  public Food getFood(int Id){
        return f.get(Id-1);
    }
}
