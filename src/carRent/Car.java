package carRent;

public class Car {
	protected int id;
	protected String model;
	protected String vehicle_assembler;
	protected String year;
	protected float price;
	
	public Car() {
	}
	
	public Car(int id) {
		this.id = id;
	}
	
	public Car(int id, String model, String vehicle_assembler, String year, float price) {
		this(model, vehicle_assembler, year, price);
		this.id = id;
	}
	
	public Car(String model, String vehicle_assembler, String year, float price) {
		this.model = model;
		this.vehicle_assembler = vehicle_assembler;
		this.year = year;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVehicle_assembler() {
		return vehicle_assembler;
	}

	public void setVehicle_assembler(String vehicle_assembler) {
		this.vehicle_assembler = vehicle_assembler;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
