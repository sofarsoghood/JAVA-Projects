package at.htlinn.ortner.db;

public class PartSale {

	int saleID;
	int customerID;
	int amount;
	String productName;
	double price;
	String sellDate;
	
	public PartSale(int saleID, int customerID, int amount,
			String producerName, double price, String sellDate) {
		super();
		this.saleID = saleID;
		this.customerID = customerID;
		this.amount = amount;
		productName = producerName;
		this.price = price;
		this.sellDate = sellDate;
	}

	public int getSaleID() {
		return saleID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public int getAmount() {
		return amount;
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}

	public String getSellDate() {
		return sellDate;
	}
}
