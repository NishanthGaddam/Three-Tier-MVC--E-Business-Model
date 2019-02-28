package com.ebusiness.service;

//Import the required library files
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.ebusiness.integration.DAOInterface;
import com.ebusiness.integration.models.Customer;
import com.ebusiness.integration.models.Laptop;
import com.ebusiness.integration.models.Order;
import com.ebusiness.integration.models.Product;
import com.ebusiness.integration.models.Phone;

@Stateless
public class ServiceInterfaceImplementation implements ServiceInterface {
	
	protected DAOInterface dao;
	
    @EJB
    public void setDAO(DAOInterface dao){
        this.dao = dao;
        }
    
	public DAOInterface getDAO(){
        return this.dao;
    	}
	    
    public Customer returnCustomerById(int id){
        return dao.returnCustomerById(id);
    	}

    public List<Customer> returnAllCustomers(){
    	return dao.returnAllCustomers();
    	}
    
    public String addNew(Customer customer) {
        return dao.addNew(customer);
    	}
    
    public Phone returnSmartPhoneById(int id) {
		return dao.returnSmartPhoneById(id);
    	}

	public List<Phone> returnAllSmartPhones(){
		return dao.returnAllSmartPhones();
		}
	
	public String addNew(Phone smartphone) {
	    return dao.addNew(smartphone);
		}
	 
	public Laptop returnLaptopById(int id) {
		return dao.returnLaptopById(id);
		}
	
	public List<Laptop> returnAllLaptops(){
		return dao.returnAllLaptops();
		}
	
	public String addNew(Laptop laptop) {
		return dao.addNew(laptop);
		}
	 
	public Order returnOrderById(int id) {
		return dao.returnOrderById(id);
		}
	
	public List<Order> returnAllOrders(){
		return dao.returnAllOrders();
		}
	
	public String addNew(Order order) {
		return dao.addNew(order);
		}
	
	public Order removeOrder(int id) {
		return dao.removeOrder(id);
	    }
	
	public Product returnProductById(int id) {
		return dao.returnProductById(id);
		}
	
	public List<Product> returnAllProducts(){
		return dao.returnAllProducts();
		}
	
	public String addNew(Product product) {
		return dao.addNew(product);
		}
	}