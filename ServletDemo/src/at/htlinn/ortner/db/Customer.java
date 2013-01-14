package at.htlinn.ortner.db;

public class Customer {
	
	private int id;
	private String name;
	private String city;
	private int zip;
	private String street;
	
	
	public Customer(int id, String name, String city, int zip, String street) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.zip = zip;
		this.street = street;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	
}
