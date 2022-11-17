import java.util.*;
public class Item {
	
	String itemName;
	String itemID;
	double price;
	int inStock;
	double totalValue;
	
	public Item(String id, String name, int inStock, double price) {
		this.inStock = inStock;
		this.itemID = id;
		this.itemName = name;
		this.price = price;
		
		this.totalValue = price *inStock;
	}
	
	public int ItemBuy(int num) {
		this.inStock += num;
		
		return 0;
	}
	
	public int ItemSell(int num) {
		if(num>this.inStock) return -1;
		this.inStock -= num;
		
		return 0;
	}
	
	public String showInfo() {
		StringBuffer buff = new StringBuffer();
		buff.append(this.itemID);
		buff.append(" ");
		buff.append(this.itemName);		
		buff.append(" ");
		buff.append(this.price);
		
		buff.append(" ");
		buff.append(this.inStock);
	//	buff.append("");
	//	buff.append(" total value:");
	//	buff.append(this.totalValue);
		
		return buff.toString();
	}
	
	
	
}
