import java.util.*;
import java.io.*;

public class AllItem {
	//item list. store all items and the details
    ArrayList <Item>itemList = new ArrayList<>();

   
   public AllItem(String filePath) throws FileNotFoundException{
	  
	   getFile(filePath);
	   MyMethod.mySort(itemList);
   
   }
   
 
   
   public void getFile(String filePath) throws FileNotFoundException {
	   
	   Scanner scan = new Scanner(new File(filePath));
	   String str = null;
	  // Item temp = null;
	   
	    String itemName= null;
		String itemID;
		double price;
		int inStock;
	   
	   while(scan.hasNextLine()) {
		   
		 str = scan.nextLine();
	   
		 if(str!=null) {
			 if(str.charAt(0) == ' ') return;
			 String[] s= str.split(" ");
			 itemID = s[0];
			 itemName = s[1];	
			 price = Double.valueOf(s[2]);
			 inStock = Integer.valueOf(s[3]);
			 
			 
            
			 itemList.add(new Item(itemID,itemName,inStock,price));
	    } 
		 
	   }
 
	  scan.close(); 
   }
   
   public void showAll() {
	   System.out.println("ID: "+ "Name: "+  "Price: "+ "number: ");
	   for(Item temp: itemList) {
		  System.out.println(temp.showInfo());
		  
	   }
   }
   
   public int sell(String itemID, int num) {
	    
	   for(Item temp: itemList) {
		  if(temp.itemID.equals(itemID)) {
			  if(temp.inStock < num) {
				  return -1;
			  } else {
				  temp.inStock -=num;
				  return 0;
			  }
		  }
	   }
	   
	   return -2;     //return 0 OK; return -1 itemNum is not enough; return -2 item is not found
   }
   
   public int buy(String itemID, int num) {
	   
	   for(Item temp: itemList) {
			if(temp.itemID.equals(itemID)) {
				temp.inStock += num;
				return 0;
			}
			
		   }
	   
	   return -2;   //return -2 item is not found ; return 0 OK
   }
   
   public void writeFile() throws IOException {
	   FileWriter fwriter = null;
	   
	   fwriter = new FileWriter("d:/items.txt");
	//   StringBuffer buff = new StringBuffer();
	   for(Item temp:itemList) {
		   
		   fwriter.write(temp.showInfo()+"\r\n"); 
	   }
	   
	  fwriter.flush();
	  fwriter.close();
	   
   }
   
   public boolean checkName(String name) {
	   for(Item temp:itemList) {
		   if(temp.itemName.equals(name)) return true;
	   }
	   return false;
   }
   
   public boolean checkID(String id) {
	   for(Item temp:itemList) {
		   if(temp.itemID.equals(id)) return true;
	   }
	   return false;
   }
   
   public  void addItem(String id, String name, int num , double price) {
	   itemList.add(new Item(id,name,num,price));
   }
   
 
   
   public void backup() throws IOException {
       FileWriter fwriter = null;
	   
	   fwriter = new FileWriter("d:/items_backup.txt");
	//   StringBuffer buff = new StringBuffer();
	   for(Item temp:itemList) {
		   
		   fwriter.write(temp.showInfo()+"\r\n"); 
	   }
	   
	  fwriter.flush();
	  fwriter.close();
   }
   
   public String findName(String str) {
	   for(Item temp:itemList) {
		   if(str.equals(temp.itemID)) {
			   return temp.itemName;
			   }
	   } 
	   return null;
   }
  
}
