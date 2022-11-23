import java.util.*;
import java.io.*;
public class DataHistory {
	//All activities happens this years
    ArrayList<Data> list = new ArrayList<>();
    
    
    public DataHistory(String path) throws FileNotFoundException {
    	
    	getFile(path);
    	MyMethod.myDataSort(list);
    	
    } 
    
    public void getFile(String path) throws FileNotFoundException {
    	Scanner scan = new Scanner(new File(path));
    	Data temp = new Data();
    	String str = null;
    	while(scan.hasNextLine()) {
    		str = scan.nextLine();
    		if(str.length()<=0) {
    		    return;	
    		}
    		
    		String[] p = str.split(" ");
    		temp.date = p[0];
    		temp.itemID = p[1];
    		temp.itemName = p[2];
    		temp.num = p[3];
            temp.price = p[4];
            temp.totalPrice = p[5];
    		list.add(temp); 
    		temp = new Data();	
    	}
    	
    	scan.close();
    	
    }      
    
    public void createData(String itemID,String name, int num, double price) throws IOException { //create a new history data
    	String date = MyMethod.daysNow();
    	String str = String.valueOf(num);
    	if(num>0) {
    	  str= "+" + str ;	
    	} else {
    		
    	}
    	double totalPrice = num * price;
    	Data newData = new Data(date,itemID,name,str,String.valueOf(price),String.valueOf(totalPrice));
    	list.add(newData);
    	MyMethod.myDataSort(list);
    	writeFile();
    	//WriteData;
    }
	
	public ArrayList<Data> searchAll(String start, String end) {	//searching all activities from "start" date to "end" date
		ArrayList<Data> ans = new ArrayList<>();
		for(Data temp:list) {
			if(temp.date.compareTo(start)>=0 && temp.date.compareTo(end)<=0) {
				ans.add(temp);			
			}
		}		
		return ans;
	}
	
	public ArrayList<Data> searchSingleItem(String itemID) {   //Searching all activities regarding the item by its id 
		 ArrayList<Data> ans = new ArrayList<>();
	      for(Data temp:list) {
			 if(temp.itemID.equals(itemID)){
				   ans.add(temp);
			 }
	      }
	    return ans;	
	}
		
	   public void writeFile() throws IOException {
		   FileWriter fwriter = null;
		   MyMethod.myDataSort(list);
		   fwriter = new FileWriter("d:/history.txt");
		   
		   for(Data temp:list) {     			  
			  fwriter.write(temp.showData()+"\r\n");   
		   }
		   
		  fwriter.flush();
		  fwriter.close();		   
	   }
	   
	   public ArrayList<Data> showOutStock() {  //show all activities for "Today"
		   
			ArrayList<Data> ans = new ArrayList<>();
			for(Data temp:list) {
				 if(temp.date.equals(MyMethod.daysNow())){
					ans.add(temp);			
				}
			}		
			return ans;  
	   }   
	   public void backup() throws IOException { //save the data in another file
           FileWriter fwriter = null;
		   
		   fwriter = new FileWriter("d:/history_backup.txt");
		 
		   for(Data temp:list) {     
			  
			  fwriter.write(temp.showData()+"\r\n");   
		   }
		   
		  fwriter.flush();
		  fwriter.close();
	   }  
}