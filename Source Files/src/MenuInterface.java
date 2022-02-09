/**
 The menu interface for the food menu.
 @ author Nah Zheng Rong
 @ version 1.0
 @ since 2021-11-13
*/
import java.io.*;
import java.util.*;

public class MenuInterface {
/**
* Creates the menu interface
*/
public MenuInterface(){
  
}
  public static void menuOutput(){
    int choice,i,z,type;
    String f,p,d;
    String t = new String();
    MenuControl mc = new MenuControl();
    Scanner sc = new Scanner(System.in);
    int set[];
     choice = 2;
      while(choice<8 && choice>0){
       System.out.println( "=================================");
      System.out.println("         Food Menu");
      System.out.println( "=================================");
      System.out.println("(1) - Print menu");
      System.out.println("(2) - Add ala carte item");
      System.out.println("(3) - Delete ala carte item");
      System.out.println("(4) - Edit ala carte item");
      System.out.println("(5) - Add set item");
      System.out.println("(6) - Delete set item");
      System.out.println("(7) - Edit set item");
      System.out.println("(8) - Return to main menu");
      System.out.print("Enter your choice: ");
     if(!sc.hasNextInt()) {
    	  System.out.println("Invalid input");
    	  sc.next();
    	  continue;
      }
      choice = sc.nextInt();
      sc.nextLine();
      switch(choice){
        case 1:
          MenuControl.printMenu();
          break;
        case 2:
          f="";
          while(f.isBlank()){
          System.out.print("Enter name of food:");
          f = sc.nextLine();
          }
          p="";
          while(p.isBlank()){
           System.out.print("Enter price of food:");
          if(sc.hasNextFloat() || sc.hasNextInt()){
          p = sc.nextLine();
          if(Double.valueOf(p)<=0){
            System.out.println("Enter a number greater than 0");
            p="";
          }
          }
          else{
            System.out.println("Invalid input");
            sc.nextLine();
          }
          }
          type=4;
          while(type>3){
          System.out.println("(1) - Main course");
          System.out.println("(2) - Dessert");
          System.out.println("(3) - Drink");
          System.out.print("Enter type of food (1/2/3):");
          if(sc.hasNextInt()){
          type = sc.nextInt();
          if(type==1){
            t="Main course";
          }
          if(type==2){
            t="Dessert";
          }
          if(type==3){
            t="Drink";
          }
          if(type>3){
            System.out.println("Enter a number from 1 to 3");
            sc.nextLine();
          }
          }
          else{
            System.out.println("Invalid input");
            sc.nextLine();
          }
          }
          sc.nextLine();
          d="";
          while(d.isBlank()){
          System.out.print("Enter description of food:");
          d = sc.nextLine();
          }
          mc.addFoodItem(f,d,p,t);
          break;
        case 3:
          int del=100;
          while(del>mc.getLength() ||del<1){
          System.out.print("Enter ID of item to delete:");
          if(sc.hasNextInt()){
           del = sc.nextInt();
           if(del>mc.getLength()||del<1){
             System.out.println("ID not found!");
           }
          }
          else{
            System.out.println("Invalid input!");
            sc.nextLine();
          }
          }
          mc.deleteItem(del);
          break;
        case 4:
            i=100;
             while(i>mc.getLength() ||i<1){
            System.out.print("Enter id of food: ");
            if(sc.hasNextInt()){
           i = sc.nextInt();
           if(i>mc.getLength()||i<1){
             System.out.println("ID not found!");
             sc.nextLine();
           }
          }
          else{
            System.out.println("Invalid input!");
            sc.nextLine();
          }
             }
          sc.nextLine();
          System.out.print("Enter new name of food (leave blank if no change) :");
          f = sc.nextLine();
          while(true){
          System.out.print("Enter new price of food (leave blank if no change) :");
          p = sc.nextLine();
          if(!p.isBlank()){
            try{
              double price = Double.parseDouble(p);
              if(price<=0){
                System.out.println("Enter a number larger than 0");
                
              }
              else{
              break;
              }
            }
            catch(NumberFormatException e){
              System.out.println("Invalid input");
              
            }
          }
          else{
            break;
          }
          }
          System.out.print("Enter new description of food (leave blank if no change) :");
          d = sc.nextLine();
          mc.editFoodItem(f,d,p,i);
          break;
        case 5:
          f="";
          while(f.isBlank()){
          System.out.print("Enter name of set:");
          f = sc.nextLine();
          }
          p="";
          while(p.isBlank()){
           System.out.print("Enter price of set:");
          if(sc.hasNextFloat() || sc.hasNextInt()){
          p = sc.nextLine();
          if(Double.valueOf(p)<=0){
            System.out.println("Enter a number greater than 0");
            p="";
          }
          }
          else{
            System.out.println("Invalid input");
            sc.nextLine();
          }
          }
          int x = 0;
          while(x<2){
          System.out.print("Enter number of items in set:");
          if(!sc.hasNextInt()){
            System.out.println("Invalid input");
            sc.nextLine();
          }
          else{
            x = sc.nextInt();
            if(x<2){
              System.out.println("Enter a number larger than 1");
              sc.nextLine();
            }
            
          }
          }
          
          set=new int[x];
          int y=z=0;
          while(y<x){    	  
            System.out.print("Enter ID of item "+(y+1)+" of new set: ");
            if(!sc.hasNextInt()){
              System.out.println("Invalid input!");
              sc.next();
            }
            else {
            z=sc.nextInt();
            if(z>mc.getLength()||z<1){
              System.out.println("ID not found");
              sc.nextLine();
            }
            else {
            	set[y]=z;
            	y++;
            	
            }
            }
          }
          mc.addSetItem(f,p,set);
          break;
        case 6:
          int del1=100;
          while(del1>mc.getLength() || del1<1){
          System.out.print("Enter ID of item to delete:");
          if(sc.hasNextInt()){
           del1 = sc.nextInt();
           if(del1>mc.getLength()||del1<1){
             System.out.println("ID not found!");
              sc.nextLine();
           }
          }
          else{
            System.out.println("Invalid input!");
            sc.nextLine();
          }
          }
          mc.deleteItem(del1);
          break;
        case 7:
            i=100;
             while(i>mc.getLength()||i<1){
            System.out.print("Enter id of set: ");
            if(sc.hasNextInt()){
           i = sc.nextInt();
           if(i>mc.getLength() || i<1){
             System.out.println("ID not found!");
              sc.nextLine();
           }
          }
          else{
            System.out.println("Invalid input!");
            sc.nextLine();
          }
             }
            sc.nextLine();
          System.out.print("Enter new name of set (leave blank if no change) :");
          f = sc.nextLine();         
         while(true){
          System.out.print("Enter new price of set (leave blank if no change) :");
          p = sc.nextLine();
          if(!p.isBlank()){
            try{
              double price = Double.parseDouble(p);
              if(price<=0){
                System.out.println("Enter a number larger than 0");
                
              }
              else{
              break;
              }
            }
            catch(NumberFormatException e){
              System.out.println("Invalid input");
              
            }
          }
          else{
            break;
          }
          }
          String des = mc.getDesc(i);
          int numItem = 0;
          y=0;
          String temp;
         
          String[] fooditems = des.split(",");
          numItem = fooditems.length;
          int v = numItem;
          while(true){

          System.out.print("Enter new number of items in set (leave blank if no change) :");
          String w = sc.nextLine();
          if(!w.isBlank()){
            try{
            v = Integer.valueOf(w);
            if(v<2){
              System.out.println("Enter a number larger than 1");
              w="";
               v = numItem;
            }
            else{
            break;
            }
            }
            catch(NumberFormatException e){
            System.out.println("Invalid input");
            w="";
          
          }
          }
          else{
            break;
          }
          }
          set= new int[v];
          for(y=0;y<v;y++){
              set[y]=mc.getID(fooditems[y]);
              if(y+1==fooditems.length) {
            	  break;
              }
              
            }
          for(y=0;y<v;y++){
        	  z=y+1;
        	  while(true) {
        		  System.out.print("Enter ID of item "+z +" of set (leave blank if no change) : ");
        		  temp=sc.nextLine();
        		  if(temp.isBlank()==false){
        			  try {
        				  int id = Integer.valueOf(temp);
        				  if(id<1||id>mc.getLength()) {
        					  System.out.println("ID not found");
        				  }
        				  else {
        					  set[y]=Integer.valueOf(temp);
        					  break;
        				  }
        				  
        			  }
        			  catch(NumberFormatException e) {
        				  System.out.println("Invalid input");
        			  }
            }
        		  else{
        			  
        			  break;
        			  
            }
        	 }
          }
          mc.editSetItem(f,p,set,i);
          break;
        default:
        System.out.println("Exiting food menu...");
          return;
      }
      }
     }
}