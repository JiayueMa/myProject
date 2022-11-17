
public class Data {

    String date;
    String itemID;
    String itemName;
    int num;   //if positive, means buy, if negative, means sell
    //boolean sellOrBuy;
    public Data() {
    	
    }
    public Data(String date, String itemID, String name,int num) {
    	this.date = date;
    	this.itemName = name;
    	this.itemID = itemID;
    	this.num = num;
    	//this.sellOrBuy = sob;  //true = buy; false == sell   	
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
    	buff.append(String.valueOf(this.num));
    	
    	return buff.toString();
    }
    
}