package at.htlinn.ortner.db;

public class PartStock {

	private int id;
	private String smartphoneName;
	private int amount;
	
	public PartStock(int id, String smartphoneName, int amount) {
		this.id = id;
		this.smartphoneName = smartphoneName;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSmartphoneName() {
		return smartphoneName;
	}
	public void setSmartphoneName(String smartphoneName) {
		this.smartphoneName = smartphoneName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
