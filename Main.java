
import java.io.IOException;
import java.util.*;

//CUS 720
//Jiayue Ma
//Final Project Codes
//Topic: St. John's Campus Store System 

// For the date period, I only put for a year from January 01 to Dec 31 ( 0101 - 1231)
// All file stored (read from/write into) "D:/" 
//History data form is MMDD<space>ID<space>Name<space>num<Enter>
//Item Data form is ID<space>Name<space>price<space>number<Enter>

// Cited: "How to write/read file in java"  by  Nam Ha Minh  
// https://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java  

public class Main {
	
	final static String itemFile = "d:/items.txt";
	final static String dataFile = "d:/history.txt";
    
	public static void main(String[] args) throws IOException {
		 System.out.println("---St. John's Campus Store v1.0---");
		 System.out.println("-------Author: Jiayue Ma--------");
		 
		 
		 
         DataHistory myHistory = new DataHistory(dataFile);
         AllItem mylist = new AllItem(itemFile);
         
         Scanner sc = new Scanner(System.in);
         int option;
         
         while(true) {
           //Menu options
           showMenu();
           option = sc.nextInt();
           if(option == 1) sellItem(sc,myHistory, mylist);
           if(option == 2) buyItem(sc,myHistory, mylist);
           if(option == 3) searchByDate(sc, myHistory);
           if(option == 4) searchByItem(sc,myHistory);
           if(option == 5) createItem(sc,mylist);
           if(option == 6) inStock(mylist,sc);
           if(option == 7) bussinessToday(myHistory);
           if(option == 8) dataBackup(myHistory,mylist);
           if(option == 9) {
        	   System.out.println("-------End----------");
        	   return;
           }
         }

	}
	
	public static void showMenu() { //Menu options
		 System.out.println();
		 System.out.println();
		 System.out.println("------------Menu------------");        
         System.out.println("	1 - Sell Item");
         System.out.println("	2 - Buy Item");
         System.out.println("	3 - Search by Date");
         System.out.println("	4 - Search by Item");
         System.out.println("	5 - Create Item");
         System.out.println("	6 - Instock Information");
         System.out.println("	7 - Bussiness for Today");
         System.out.println("	8 - Data Backup");
         System.out.println("	9 - Quit");
             
	}
	
	public static void sellItem(Scanner sc,DataHistory myHistory,AllItem mylist) throws IOException {
		// sell  item
		System.out.println("Please Input your item id");
		String id = sc.next();
		if(id.length()==0) {
			return;
		}	
		
		Item myItem = mylist.checkID(id);
		if(myItem == null) {	
			System.out.println("This id is not available.");
			return;
		}	
		
		String name = mylist.getName(id);
		System.out.println("Item Name: " + name);
		System.out.println("How many items you want to sell");
		int num = sc.nextInt();
		if(num<=0) {
			System.out.println("The number you input is wrong.");
			return;
		}
		int index = mylist.sell(id, num);
		if(index == 0) {
			myHistory.createData(id, name, 0-num, myItem.price);
			System.out.println(num + " items are sold to the customer");
		} else {
			System.out.println("You do not have enough items in the stock.");
		}
		
	}
		
	public static void buyItem(Scanner sc,DataHistory myHistory, AllItem mylist) throws IOException {
		//buy some item; or add some items into the stock
		System.out.println("Please Input your item ID");
		String id = sc.next();
		Item myItem = mylist.checkID(id);
		if(myItem == null) {	
			System.out.println("This id is not available.");
			return;
		}		
		
		String name = mylist.getName(id);
		System.out.println("Item Name: " + name);
		System.out.println("How many items you want to buy");
		int num = sc.nextInt();
		if(num>0) {
		mylist.buy(id, num);
		myHistory.createData(id, name, num, myItem.price);
		System.out.println(num + " items are added into the stock");
		} else {
			System.out.println("The number you input is wrong.");
			return;
		}
	}
	
	public static void searchByDate(Scanner sc, DataHistory myHistory) {
		//search all activities from start_date to end_date
	   System.out.println("Please input the starting date (Form as MMDD)");
	   String start = sc.next();
	   System.out.println("Please input the ending date (Form as MMDD)");
	   String end = sc.next();
	   ArrayList<Data> showList = myHistory.searchAll(start,end);
	   
	   if(showList.size()>0) {
		   System.out.println("All activities from "+ start +" to " + end );
		   System.out.println("  Date: " + "   ID: " +"     Name: "+"    Num:" + "       Price:" + "  Total Price:");
		   for(Data temp: showList) {
			   System.out.printf("%6s  %5s  %10s     %4s    %8s     %s%n", temp.date,temp.itemID, temp.itemName,  temp.num, temp.price, temp.totalPrice);			   
		   }
   
	   } else {
		   
		   System.out.println("No record avaiable.");
	   }	
	}	
	public static void searchByItem(Scanner sc,DataHistory myHistory) {
		//search all activities of a single item
		 System.out.println("Please input the id of item (int number)");
		 String id = sc.next();
	     ArrayList<Data> showList = myHistory.searchSingleItem(id);
	       
		   if(showList.size()>0) {
			   System.out.println("ID: " + id +" item activities");
			   System.out.println("  Date: " + "   ID: " +"     Name: "+"    Num:" + "       Price:" + "  Total Price:");
			   for(Data temp: showList) {
				   System.out.printf("%6s  %5s  %10s     %4s    %8s     %s%n", temp.date,temp.itemID, temp.itemName,  temp.num, temp.price, temp.totalPrice);			   
			   }
	   
		   } else {
			   
			   System.out.println("No record avaiable.");
		   }
	}
	
	public static void createItem(Scanner sc, AllItem mylist) throws IOException {
		//To create an new Item and add into the Store list
		String name = "";
		String id = "";	
		while(true) {       //To check the same name
			System.out.println("Please input the name of Item");
			name = sc.next();
			if(name.length() == 0) {
				return;
			}
			
			if(mylist.checkName(name)) {
			    System.out.println("This name is used already, Please use another name");
			    continue;
		   } else {
			   break;
		   }
		}				
		while(true) {          //To check the same id		     
			System.out.println("Please input the id of Item");
			id = sc.next();
			if(id.length() == 0) {
				return;
			}
			if(mylist.checkID(id)!=null) {
			    System.out.println("This id is used already, Please use another id");
			continue;
		   } else {
			   break;
		   }
		}			    
		
		System.out.println("Please input the price of Item");
		double price = sc.nextDouble();    //to get the price
	   
	    mylist.addItem(id, name, 0, price);
	    mylist.writeFile();
	}
	
	public static void inStock(AllItem mylist, Scanner sc) {
		System.out.println("All Item in the stock");
		// to print all items and the details in the stock
		ArrayList<Item> showList = mylist.showAll();
		double sum = 0.0;
		  if(showList.size()>0) {
			   System.out.println("ID: " + "      Name: " +"     Price: "+"       Num:  " + "   Total Price:");
			   for(Item temp: showList) {
				   System.out.printf("%3s  %10s    %7.2f      %5d      %8.2f %n", temp.itemID,temp.itemName,temp.price, 
						   temp.inStock, temp.inStock * temp.price);			   
				   sum = sum + Double.valueOf(temp.inStock * temp.price);
			   }
			   System.out.printf("Total Value: " + sum);
	   
		   } else {
			   
			 System.out.println("No record avaiable.");
		   }
	}
	
    public static void bussinessToday(DataHistory myHistory) {
    	//to show the activities happens today
    	
    	  System.out.println("All activities for today");
		  ArrayList<Data> showList = myHistory.showOutStock();
		  double sum = 0.0;  
		  if(showList.size()>0) {
			   System.out.println("  Date: " + "   ID: " +"     Name: "+"     Number" + "   Price:" + "    Total Price:");
			   for(Data temp: showList) {
				   System.out.printf("%6s  %5s  %10s     %5s    %6s     %8s%n", temp.date,temp.itemID, temp.itemName, 
						   temp.num, temp.price, temp.totalPrice);	
				   sum = sum + Double.valueOf(temp.totalPrice);
			   }
			   System.out.printf("Total Value: " + sum);
		   } else {
			   
			   System.out.println("No record avaiable.");
		   }	
	}
		
	public static void dataBackup( DataHistory myHistory, AllItem mylist) throws IOException {
		//to backup all data into files just in case.
		myHistory.backup();
		mylist.backup();
		System.out.println("You data has been stored in history_backup.txt and items_backup.txt");
	}
}