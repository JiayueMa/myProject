import java.util.*;
import java.io.*;
public class DataHistory {
    ArrayList<Data> list = new ArrayList<>();
    
    
    public DataHistory() throws FileNotFoundException {
    	
    	getFile();
    	MyMethod.myDataSort(list);
    	
    }
    
    public void getFile() throws FileNotFoundException {
    	Scanner scan = new Scanner(new File("d:/history.txt"));
    	Data temp = new Data();
    	String str = null;
    	while(scan.hasNextLine()) {
    		str = scan.nextLine();
    		String[] p = str.split(" ");
    		temp.date = p[0];
    		temp.itemID = p[1];
    		temp.itemName = p[2];
    		temp.num = Integer.valueOf(p[3]);

    		list.add(temp); 
    		temp = new Data();
    		
    	}
    	
    	scan.close();
    	
    }      
    
    public void createData(String itemID,String name, int num) throws IOException {
    	String date = MyMethod.daysNow();
    	Data newData = new Data(date,itemID,name,num);
    	
    	list.add(newData);
    	MyMethod.myDataSort(list);
    	writeFile();
    	//WriteData;
    }
	
	public void searchAll(String start, String end) {
		if(start.compareTo(end)>0) {
			System.out.println("start date can't be later than end date");
			return;
		}
		
		System.out.println("Date: " + "ID: " +"Name: "+"Sell/Buy");
		
		for(Data temp:list) {
			if(temp.date.compareTo(start)>=0 && temp.date.compareTo(end)<=0) {
				System.out.println(temp.showData());
			}

		}
	}
	
	public void searchSingleItem(String itemID) {
		  System.out.println("Date: " + "ID: " +"Name: "+"Sell/Buy");
	      for(Data temp: list) {
			 if(temp.itemID.equals(itemID)){
				System.out.println(temp.showData());
			 }
	      }
		
	}
	
	
	   public void writeFile() throws IOException {
		   FileWriter fwriter = null;
		   
		   fwriter = new FileWriter("d:/history.txt");
		 
		   for(Data temp:list) {     
			 
			//  buff.append("\r\n ");
			  
			  fwriter.write(temp.showData()+"\r\n");   
		   }
		   
		  fwriter.flush();
		  fwriter.close();
		   
	   }
	   
	   public void showOutStock() {
		   for(Data temp: list) {
			   if(temp.date.equals(MyMethod.daysNow())){
				   System.out.println(temp.showData());
			   }
		   }
		   
	   }
	   
	   public void backup() throws IOException {
           FileWriter fwriter = null;
		   
		   fwriter = new FileWriter("d:/history_backup.txt");
		 
		   for(Data temp:list) {     
			 
			//  buff.append("\r\n ");
			  
			  fwriter.write(temp.showData()+"\r\n");   
		   }
		   
		  fwriter.flush();
		  fwriter.close();
	   }
   
}