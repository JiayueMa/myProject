import java.util.*;

import java.text.SimpleDateFormat;
public class MyMethod {
	//some custom methods
	  public static void myItemSort(ArrayList <Item> itemList) {
		   	//Override a sorting method, to Sort a <Item> List
		   	Collections.sort(itemList, (Item p, Item q)->{ //Lambda 
		   		if(Integer.valueOf(p.itemID)<Integer.valueOf(q.itemID)) {
		   			return -1; 
		   		}  else {
		   			return 1;
		   		}

		   	});
		   }
//	  
//	  public static int strToInt(String str) {
//		  //String to int
//		  int n = 0;
//		  for(int i = 0 ; i < str.length();i++) {
//			  n = n*10 + (str.charAt(i) - '0');
//		  }
//		  return n;
//	  }
	  
	  public static String daysNow() {
		  //get the date value as MMDD format
		   SimpleDateFormat ft = new SimpleDateFormat("MMdd");
		   Date dNow = new Date();
		   return ft.format(dNow).toString();  
	   }
	   
	   public static void myDataSort(ArrayList<Data> list) {
		 //Override a sorting method, to Sort a <Data> List
		   Collections.sort(list, (Data a, Data b)->{  // Lambda
			   if(a.date.compareTo(b.date)<0) {
				   return -1;
			   } else {
				   return 1;
			   }
		   });
	   
	   }
}
