package com.ebusiness.presentation;

//Import the required library files
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.List;
import com.ebusiness.integration.models.Customer;
import com.ebusiness.service.ServiceInterface;

@ManagedBean // Name of bean will be same of class name 'managedBeanCustomer'
@SessionScoped
public class ManagedBeanCustomer {
    private ServiceInterface service;

    @EJB
    public void setCustomerService(ServiceInterface service){
        this.service = service;
    }

    // Declare required variables
    private Customer newCustomer = new Customer();
	private Customer currentCustomer;
	private List<Customer> customersList;
	private String currentSearchName;
	private int countSearchResults;

	// Constructor
    public ManagedBeanCustomer(){
    }
    
    // Required methods to access, modify data from/in service object
    
    public Customer getCustomerById(int id){
        return service.returnCustomerById(id);
    }
    
    public List<Customer> getCustomersList(){
    	customersList = service.returnAllCustomers();
        return customersList;
    }
    
    public List<Customer> getCustomersList(String searchName){
    	countSearchResults = 0;
    	if(customersList != null) customersList.clear();
    	else customersList = new ArrayList<Customer>();
		for (Customer customer : service.returnAllCustomers()) {
			if(customer.getName().toLowerCase().contains(searchName.toLowerCase()))
			{
				customersList.add(customer);
				countSearchResults++;
			}
		}
        return customersList;
    }
        
    public Customer getCurrentCustomer(){
        return currentCustomer;
    }
    
    public void setCurrentCustomer(Customer customer){
    	this.currentCustomer = customer;
    }
    
    public Customer getNewCustomer() {
		return newCustomer;
	}

	public void setNewCustomer(Customer newCustomer) {
		this.newCustomer = newCustomer;
	}
    
    public String getCustomerDetailsPage(){
        return "showCustomerDetails";
    }
    
    public String addCustomer(){
    	String result = service.addNew(newCustomer);
		if (result == ""){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Successfully added the customer: " + newCustomer.getName() + ".", null));
			newCustomer = new Customer();
	    	return "listOfCustomers";
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Unsuccessful in adding new Customer.", null));
			newCustomer = new Customer();
			return "";
		}
    }
        
    public double calcualtePrice(double unitPrice, int quantity)
    {
    	return unitPrice * quantity;
    }
    
    public String searchCustomerResultPage()
    {
    	return "foundCustomer";
    }

	public String getCurrentSearchName() {
		return currentSearchName;
	}

	public void setCurrentSearchName(String currentSearchName) {
		this.currentSearchName = currentSearchName;
	}

	public int getCountSearchResults() {
		return countSearchResults;
	}
}