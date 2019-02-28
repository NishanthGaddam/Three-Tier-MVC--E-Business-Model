package com.ebusiness.integration;

//Import the required library files
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.ebusiness.integration.models.Customer;
import com.ebusiness.integration.models.Movies;
import com.ebusiness.integration.models.Order;
import com.ebusiness.integration.models.Product;
import com.ebusiness.integration.models.Phone;

@Stateless
public class DAOInterfaceImplementation implements DAOInterface {
	
	protected EntityManager entityManager;
    
	@PersistenceContext
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
        }
   
    public EntityManager getEntityManager(){
        return this.entityManager;
    	}
    public Customer returnCustomerById(int id) {
    	Customer customer = entityManager.getReference(Customer.class, id);
    	return customer;
    	}

    public List<Customer> returnAllCustomers(){
    	List<Customer> resultList = entityManager.createQuery("SELECT c from " + Customer.class.getName() + " c").getResultList();
        return resultList;
    	}
    
    public String addNew(Customer customer) {        
    	try{
    		entityManager.persist(customer);
    		return "";
    		}
    	catch(Exception e){
    		return e.getMessage();
    		}
    	}
    
    public Phone returnSmartPhoneById(int id) {
		Phone smartphone = entityManager.getReference(Phone.class, id);
		return smartphone;
    	}
	
	public List<Phone> returnAllSmartPhones(){
		List<Phone> resultList = entityManager.createQuery("SELECT c from " + Phone.class.getName() + " c").getResultList();
	    return resultList;
		}
	
	public String addNew(Phone smartphone) {
    	try{
    		entityManager.persist(smartphone);
    		return "";
    		}
    	catch(Exception e){
    		return e.getMessage();
    		}
		}
	
	public Laptop returnMoviesById(int id) {
		Laptop laptop = entityManager.getReference(Movies.class, id);
		return laptop;
		}
	
	public List<Laptop> returnAllMovies(){
		List<Laptop> resultList = entityManager.createQuery("SELECT c from " + Movies.class.getName() + " c").getResultList();
		return resultList;
		}
	
	public String addNew(Laptop laptop) {
    	try{
    		entityManager.persist(laptop);
    		return "";
    		}
    	catch(Exception e){
    		return e.getMessage();
    		}
		}
	
	public Order returnOrderById(int id) {
		Order order = entityManager.getReference(Order.class, id);
		return order;
		}
	
	public List<Order> returnAllOrders(){
		List<Order> resultList = entityManager.createQuery("SELECT c from " + Order.class.getName() + " c").getResultList();
		return resultList;
		}
	
	public String addNew(Order order) {
    	try{
    		entityManager.persist(order);
    		return "";
    		}
    	catch(Exception e){
    		return e.getMessage();
    		}
		}
	
	public Order removeOrder(int id) {
    	Order order = (Order)entityManager.find(Order.class, id);
        entityManager.remove(order);
        return order;
    	}
	
	public Product returnProductById(int id) {
		Product product = entityManager.getReference(Product.class, id);
		return product;
		}
	
	public List<Product> returnAllProducts(){
		List<Product> resultList = entityManager.createQuery("SELECT c from " + Product.class.getName() + " c").getResultList();
		return resultList;
		}
	
	public String addNew(Product product) {
    	try{
    		entityManager.persist(product);
    		return "";
    		}
    	catch(Exception e){
    		return e.getMessage();
    		}
		}
	}