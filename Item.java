public class Item {
	
	String itemName;
	String itemID;
	double price;
	int inStock;

	public Item() {  
		
	}
	public Item(String id, String name, int inStock, double price) {
		this.inStock = inStock;
		this.itemID = id;
		this.itemName = name;
		this.price = price;
		
		//this.totalValue = price *inStock;
	}
	
	public int ItemBuy(int num) {
		//buy items
		this.inStock += num;
		
		return 0;
	}
	
	public int ItemSell(int num) {
		//sell items
		if(num>this.inStock) return -1;
		this.inStock -= num;
		
		return 0;
	}
	
	public String showInfo() {//toString
		StringBuffer buff = new StringBuffer();
		buff.append(this.itemID);
		buff.append(" ");
		buff.append(this.itemName);		
		buff.append(" ");
		buff.append(this.price);
		
		buff.append(" ");
		buff.append(this.inStock);

		
		return buff.toString();
	}
	
	
	
}
