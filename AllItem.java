import java.util.*;
import java.io.*;

public class AllItem {
	//item list. store all items and the details
    ArrayList <Item>itemList = new ArrayList<>();

   
   public AllItem(String filePath) throws FileNotFoundException{
	  
	   getFile(filePath);
	   MyMethod.myItemSort(this.itemList);
   
   }
   
 
   
   public void getFile(String filePath) throws FileNotFoundException {
	   //get all item information from the file
	   Scanner scan = new Scanner(new File(filePath));
	   String str = null;	   
	   String itemName= null;
	   String itemID;
	   
		double price;
		int inStock;
	   
	   while(scan.hasNextLine()) {
		   
		 str = scan.nextLine();
		 if(str.length()<=0) {
 		    return;	
 		}
		 if(str!=null) {
			 if(str.charAt(0) == ' ') return;
			 String[] s= str.split(" ");
			 itemID = s[0];
			 itemName = s[1];	
			 price = Double.valueOf(s[2]);
			 inStock = Integer.valueOf(s[3]);  
			 
			 //get the value to item
            
			 itemList.add(new Item(itemID,itemName,inStock,price));  // add all items to the list
			 MyMethod.myItemSort(itemList);
	    } 
		 
	   }
 
	  scan.close(); 
   }
   
   public ArrayList<Item> showAll() {
	  return itemList;
   }
   
   public int sell(String itemID, int num) throws IOException {	    
	   for(Item temp: itemList) {
		  if(temp.itemID.equals(itemID)) {
			  if(temp.inStock < num) {
				  return -1;
			  } else {
				  temp.inStock -=num;   //cut the number
				  writeFile();    //file 
				  return 0;
			  }
		  }
	   }	   
	   return 0; //return 0 OK; return -1 itemNum is not enough;
   }
   
   public String getName(String id) {
	   
	    for(Item temp: itemList) {
			if(temp.itemID.equals(id)) {
				return temp.itemName;
			}	
		}	   
	    return null;
   }
   
   
   public void buy(String itemID, int num) throws IOException {   
	   for(Item temp: itemList) {
			if(temp.itemID.equals(itemID)) {
				temp.inStock += num;
				writeFile();
				return ;
			}	
	   }
   }
   
   public void writeFile() throws IOException {  //writing file function
	   FileWriter fwriter = null;
	   MyMethod.myItemSort(itemList);
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
   
   public Item checkID(String id) {

	   for(Item temp:itemList) {
		   if(temp.itemID.equals(id)) return temp;
	   }
	   return null;
   }
   
   public  void addItem(String id, String name, int num , double price) {
	   itemList.add(new Item(id,name,num,price));
   }  
   
   public void backup() throws IOException {  //back up the data in other files for safe
       FileWriter fwriter = null;
	   
	   fwriter = new FileWriter("d:/items_backup.txt");
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
