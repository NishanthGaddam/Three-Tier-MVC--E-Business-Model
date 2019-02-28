package com.ebusiness.integration.models;

// Import the required library files
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Define the entity Order, it will also map to table name Order i.e. @Table(name = "Order")
// But 'Order' is a keyword, so will not make table with this name, give a different name to table
@Entity
@Table(name = "Orders")
public class Order 
{
    @Id // Define primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    // Other order attributes
    private Date orderTime;
    private Integer quantity;
    
    @ManyToOne // Mapping to 'Customer' entity, many to one
    private Customer customer;
    
    @ManyToOne // Mapping to 'Product' entity, many to one
    private Product product;

    // Attribute value getter methods
	public Integer getId() {
		return id;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Product getProduct() {
		return product;
	}

	// Attribute value setter methods
	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}