import java.util.*;
import java.text.SimpleDateFormat;
public class MyMethod {
	//some custom methods
	  public static void mySort(ArrayList <Item> itemList) {
		   	
		   	Collections.sort(itemList, (Item p, Item q)->{
		   		if(p.itemID.compareTo(q.itemID)<0) {
		   			return -1; 
		   		}  else {
		   			return 1;
		   		}

		   	});
		   }
	  
	  public static int strToInt(String str) {
		  
		  int n = 0;
		  for(int i = 0 ; i < str.length();i++) {
			  n = n*10 + (str.charAt(i) - '0');
		  }
		  return n;
	  }
	  
	  public static String daysNow() {
		   SimpleDateFormat ft = new SimpleDateFormat("MMdd");
		   Date dNow = new Date();
		   return ft.format(dNow).toString();  
	   }
	   
	   public static void myDataSort(ArrayList<Data> list) {
		   Collections.sort(list, (Data a, Data b)->{
			   if(a.date.compareTo(b.date)<0) {
				   return -1;
			   } else {
				   return 1;
			   }
		   });
	   
	   }
}
