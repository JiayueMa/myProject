import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.*;

//CUS 720
//Jiayue Ma
//Final Project Code Part
//St. John's Campus-Shop Storage System 

// For the date period, I only put for a year from January 01 to Dec 31 ( 0101 - 1231)
// All file stored (read from/write into) "D:/" 
//History data form is MMDD<space>ID<space>Name<space>num<Enter>
//Item Data form is ID<space>Name<space>price<space>number<Enter>



public class Main {
    
	public static void main(String[] args) throws IOException {

         System.out.println("Welcome to St. John's Store System");
         DataHistory myHistory = new DataHistory();
         AllItem mylist = new AllItem("d:/items.txt");
         
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
           if(option == 7) outStack(myHistory);
           if(option == 8) dataBackup(myHistory,mylist);
           if(option == 9) {
        	   System.out.println("-------End----------");
        	   return;
           }
         }

	}
	
	public static void showMenu() {
		 System.out.println("------------Menu------------");
         
         System.out.println("1 - Sell Item");
         System.out.println("2 - Buy Item");
         System.out.println("3 - Search by Date");
         System.out.println("4 - Search by Item");
         System.out.println("5 - Create Item");
         System.out.println("6 - Instock Now");
         System.out.println("7 - Outstock Today");
         System.out.println("8 - Data Backup");
         System.out.println("9 - Quit");
             
	}
	
	public static void sellItem(Scanner sc,DataHistory myHistory,AllItem mylist) throws IOException {
		// sell some item
		System.out.println("Please Input your item id");
		String id = sc.next();
		System.out.println("Please Input the number");
		int num = sc.nextInt();
		String name = mylist.findName(id);
		if(name != null) {
			if(mylist.sell(id, num)==-1) {
			   System.out.println("The number of item is not enough");
			   return;
			}
		    myHistory.createData(id, name, 0-num);
		    
		} else {
			System.out.println("We do not have this item");
		}
	}
	
	
	public static void buyItem(Scanner sc,DataHistory myHistory, AllItem mylist) throws IOException {
		//buy some item; or add some items into the stock
		System.out.println("Please Input your item id");
		String id = sc.next();
		System.out.println("Please Input the number");
		int num = sc.nextInt();
		
		String name = mylist.findName(id);
		if(name!=null) {
		   mylist.buy(id, num);
		   myHistory.createData(id, name, num);
		} else {
			 System.out.println("this item is not existed"); 
		   }
	}
	
	public static void searchByDate(Scanner sc, DataHistory myHistory) {
		//search all activities from start_date to end_date
	   System.out.println("Please input the starting date (Form as MMDD)");
	   String start = sc.next();
	   System.out.println("Please input the ending date (Form as MMDD)");
	   String end = sc.next();
	   
	   myHistory.searchAll(start,end);
	
	}
	
	public static void searchByItem(Scanner sc,DataHistory myHistory) {
		//search all activities of a single item
		 System.out.println("Please input the id of item (int number)");
		 String id = sc.next();
	     myHistory.searchSingleItem( id);
		
	}
	
	public static void createItem(Scanner sc, AllItem mylist) throws IOException {
		//To create an new Item and add into the Store list
		String name = "";
		String id = "";
		
		while(true) {       //To check the same name
     
			System.out.println("Please input the name of Item");
			name = sc.next();
			
			if(name!=null && mylist.checkName(name)) {
			    System.out.println("This name is used already, Please use another name");
			    continue;
		   } else {
			   break;
		   }

		}
		
		while(true) {          //To check the same id
		     
			System.out.println("Please input the id of Item");
			id = sc.next();
			
			if(name!=null && mylist.checkID(id)) {
			    System.out.println("This id is used already, Please use another id");
			continue;
		   } else {
			   break;
		   }

		
		}
		
		System.out.println("Please input the price of Item");
		double price = sc.nextDouble();    //to get the price
	//	System.out.println("Please input the instock number of Item");
	//	int num = sc.nextInt();             //to get the number of the item
	   
	    mylist.addItem(id, name, 0, price);
	    mylist.writeFile();
	}
	
	public static void inStock(AllItem mylist, Scanner sc) {
		// to show all items and it's numbers and info in store
		mylist.showAll();
		//System.out.println("Press ENTER to the Menu");
	   // String t = sc.nextLine();
		
		
	}
	
    public static void outStack(DataHistory myHistory) {
    	//to show the activities happens today
		myHistory.showOutStock();
	}
	
	public static void dataBackup( DataHistory myHistory, AllItem mylist) throws IOException {
		//to backup all data into files just in case.
		myHistory.backup();
		mylist.backup();
		System.out.println("You data has been stored in history_backup.txt and items_backup.txt");
	}
}