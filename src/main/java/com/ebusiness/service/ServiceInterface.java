package com.ebusiness.service;

//Import the required library files
import java.util.List;
import javax.ejb.Local;
import com.ebusiness.integration.models.Customer;
import com.ebusiness.integration.models.Laptop;
import com.ebusiness.integration.models.Order;
import com.ebusiness.integration.models.Product;
import com.ebusiness.integration.models.Phone;

//This interface will be implemented by ServiceInterfaceImplementation

@Local
public interface ServiceInterface  {
	// Declare the all required methods
	
	// Return by id
    Customer returnCustomerById(int id);
    Product returnProductById(int id);
    Laptop returnLaptopById(int id);
    Phone returnSmartPhoneById(int id);
    Order returnOrderById(int id);

    // Return all
    List<Customer> returnAllCustomers();
    List<Product> returnAllProducts();
    List<Laptop> returnAllLaptops();
    List<Phone> returnAllSmartPhones();
    List<Order> returnAllOrders();
    
    // Add a new thing
    String addNew(Customer customer);
    String addNew(Product product);
    String addNew(Laptop laptop);
    String addNew(Phone smartphone);
    String addNew(Order order);
    
    // Remove something
    Order removeOrder(int id);
}