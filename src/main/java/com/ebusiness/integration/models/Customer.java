package com.ebusiness.integration.models;

// Import the required library files
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// Define the entity Customer, it will also map to table name Customer i.e. @Table(name = "Customer")
@Entity
public class Customer 
{
    @Id // Define primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // Other customer attributes
    private String name;
    private String address;
    private String phone;
    private String email;
    
    // Mapping to 'Order' entity, one to many
    @OneToMany(mappedBy = "customer") // Mapped to customer object of 'Order' class
    private List<Order> orders = new ArrayList<Order>(); // This list will contain references of all orders of this customer

    // Attribute value getter methods
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public List<Order> getOrders() {
		return orders;
	}

	// Attribute value setter methods
	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOrders(Order order) {
		this.orders.add(order);
	}
}