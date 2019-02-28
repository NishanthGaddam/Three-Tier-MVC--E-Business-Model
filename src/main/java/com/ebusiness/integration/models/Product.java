package com.ebusiness.integration.models;

// Import the required library files
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

// Define the entity Customer, it will also map to table name Product i.e. @Table(name = "Product")
@Entity
@MappedSuperclass // Annotation is showing that it is mapped to other entities as super class
public class Product 
{
    @Id // Define primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // Other product attributes
    private String brand;
    private String model;
    private String operatingSystem;
    private String display;
    private Integer price;
    private String weight;
    private String camera;
    private String wireless;
    private String description;
    private String productType;
    
    // Mapping to 'Order' entity, one to many
    @OneToMany(mappedBy = "product") // Mapped to product object of 'Order' class
    private List<Order> orders = new ArrayList<Order>(); // This list will contain references of all orders of this customer

    // Attribute value getter methods
	public Integer getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public String getDisplay() {
		return display;
	}

	public Integer getPrice() {
		return price;
	}

	public String getWeight() {
		return weight;
	}

	public String getCamera() {
		return camera;
	}

	public String getWireless() {
		return wireless;
	}

	public String getDescription() {
		return description;
	}

	public String getProductType() {
		return productType;
	}

	public List<Order> getOrders() {
		return orders;
	}

	// Attribute value setter methods
	public void setId(Integer id) {
		this.id = id;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public void setWireless(String wireless) {
		this.wireless = wireless;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setOrders(Order order) {
		this.orders.add(order);
	}
    
}