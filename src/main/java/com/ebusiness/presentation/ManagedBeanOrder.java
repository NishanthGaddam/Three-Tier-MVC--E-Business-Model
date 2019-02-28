package com.ebusiness.presentation;

//Import the required library files
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ebusiness.integration.models.Customer;
import com.ebusiness.integration.models.Order;
import com.ebusiness.integration.models.Product;
import com.ebusiness.service.ServiceInterface;

@ManagedBean //Name of bean will be same of class name 'managedBeanOrder'
@SessionScoped
public class ManagedBeanOrder {
    private ServiceInterface service;

    @EJB
    public void setOrderService(ServiceInterface service){
        this.service = service;
    }

    // Declare required variables
    private Order newOrder = new Order();
	private Order currentOrder;
	private int deleteOrder;
	private List<Order> ordersList;
	private String currentSearchKeyword;
	private int countSearchResults;
	
	Map<String, String> availableCustomers = new LinkedHashMap<String, String>();
	Map<String, String> availableProducts = new LinkedHashMap<String, String>();
	private int selectedCustomerId;
	private int selectedPoductId;

	// Constructor
    public ManagedBeanOrder(){
    }
    
    public Order getOrderById(int id){
        return service.returnOrderById(id);
    }
    
    public List<Order> getOrdersList(){
    	ordersList = service.returnAllOrders();
        return ordersList;
    }
    
    public List<Order> getOrdersList(String searchKeyword){
    	countSearchResults = 0;
    	if(ordersList != null) ordersList.clear();
    	else ordersList = new ArrayList<Order>();
		for (Order order : service.returnAllOrders()) {
			if(order.getProduct().getBrand().toLowerCase().contains(searchKeyword.toLowerCase())
					|| Integer.toString(order.getId()).contains(searchKeyword)
					|| order.getCustomer().getName().toLowerCase().contains(searchKeyword.toLowerCase())
					|| order.getCustomer().getAddress().toLowerCase().contains(searchKeyword.toLowerCase())
					|| order.getProduct().getModel().toLowerCase().contains(searchKeyword.toLowerCase())
					|| Integer.toString(order.getQuantity()).contains(searchKeyword)
					|| Integer.toString(order.getProduct().getId()).contains(searchKeyword)
					)
			{
				ordersList.add(order);
				countSearchResults++;
			}
		}
        return ordersList;
    }
        
    public Order getCurrentOrder(){
        return currentOrder;
    }
    
    public void setCurrentOrder(Order order){
    	this.currentOrder = order;
    }
    
    public Order getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(Order newOrder) {
		this.newOrder = newOrder;
	}
    
    public String getOrderDetailsPage(){
        return "showOrderDetails";
    }
    
    public String getAfterOrderDeletePage(){
    	try
    	{
    		service.removeOrder(deleteOrder);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Successfully deleted the order.", null));
    	}
    	catch(Exception e)
    	{
    		FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Unsuccessful in deleting the order. " + e.getMessage(), null));
    	}
        return "listOfOrders";
    }
    
    public String addOrder(){
    	// Get reference of selected customer and set it to new order
    	Customer customer = service.returnCustomerById(selectedCustomerId);
    	newOrder.setCustomer(customer);
    	
    	// Get reference of selected product and set it to new order
    	Product product = service.returnProductById(selectedPoductId);	
    	newOrder.setProduct(product);
    	
    	// Add current time to new order
    	newOrder.setOrderTime(new Date());
    	
    	// Add the order object reference to selected customer
    	customer.setOrders(newOrder);
    	
    	// Add the order object reference to selected product
    	product.setOrders(newOrder);
    	
    	// Call the service method to add the newly created order object
    	String result = service.addNew(newOrder);
    	
		if (result == ""){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Successfully added the order with order id " + newOrder.getId() + ".", null));
			newOrder = new Order();
	    	return "listOfOrders";
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Unsuccessful in adding new order.",
							null));
			newOrder = new Order();
			return "";
		}
    }
    
	public int getDeleteOrder() {
		return deleteOrder;
	}

	public void setDeleteOrder(int deleteOrder) {
		this.deleteOrder = deleteOrder;
	}
        
    public double calcualtePrice(double unitPrice, int quantity)
    {
    	return unitPrice * quantity;
    }
    
    public String searchOrderResultPage()
    {
    	return "foundOrder";
    }

	public String getCurrentSearchKeyword() {
		return currentSearchKeyword;
	}

	public void setCurrentSearchKeyword(String currentSearchKeyword) {
		this.currentSearchKeyword = currentSearchKeyword;
	}

	public int getCountSearchResults() {
		return countSearchResults;
	}
	
	public Map<String, String> getAvailableCustomers() {
    	if(availableCustomers != null) availableCustomers.clear();
		for (Customer customer : service.returnAllCustomers()) {
			availableCustomers.put(customer.getName(), customer.getId().toString());
			}
		return availableCustomers;
	}

	public void setAvailableCustomers(Map<String, String> availableCustomers) {
		this.availableCustomers = availableCustomers;
	}

	public Map<String, String> getAvailableProducts() {
    	if(availableProducts != null) availableProducts.clear();
		for (Product product : service.returnAllProducts()) {
			availableProducts.put(product.getId().toString() + ". " + product.getBrand() + " " + product.getModel(), product.getId().toString());
			}
		return availableProducts;
	}

	public void setAvailableProducts(Map<String, String> availableProducts) {
		this.availableProducts = availableProducts;
	}

	public int getSelectedCustomerId() {
		return selectedCustomerId;
	}

	public void setSelectedCustomerId(int selectedCustomerId) {
		this.selectedCustomerId = selectedCustomerId;
	}

	public int getSelectedPoductId() {
		return selectedPoductId;
	}

	public void setSelectedPoductId(int selectedPoductId) {
		this.selectedPoductId = selectedPoductId;
	}
}