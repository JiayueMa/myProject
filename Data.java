
public class Data {

    String date;
    String itemID;
    String itemName;
    String num;   //if positive, means buy, if negative, means sell
    String price; 
    String totalPrice; 
    public Data() {
    	
    }
    public Data(String date, String itemID, String name,String num, String price, String totalPrice) {
    	this.date = date;
    	this.itemName = name;
    	this.itemID = itemID;
    	this.num = num;
    	this.price = price;
    	this.totalPrice = totalPrice; 	
    }
    
    public String showData() {
    	StringBuffer buff = new StringBuffer();
    	//buff.append("Date: ");
    	buff.append(this.date);
    	buff.append(" ");
    	//buff.append("itemID: ");
    	buff.append(this.itemID);
    	buff.append(" ");
    	//buff.append("itemName: ");
    	buff.append(this.itemName);
    	buff.append(" ");
    	buff.append(this.num);
    	buff.append(" ");
    	buff.append(this.price);
    	buff.append(" ");
    	buff.append(this.totalPrice);
    	
    	return buff.toString();
    }
    
}