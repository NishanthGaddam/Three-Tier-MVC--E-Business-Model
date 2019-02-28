package com.ebusiness.presentation;

//Import the required library files
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.List;
import com.ebusiness.integration.models.Laptop;
import com.ebusiness.service.ServiceInterface;

@ManagedBean //Name of bean will be same of class name 'managedBeanCustomer'
@SessionScoped
public class ManagedBeanLaptop {
    private ServiceInterface service;

    @EJB
    public void setLaptopService(ServiceInterface service){
        this.service = service;
    }

    // Declare required variables
    private Laptop newLaptop = new Laptop();
	private Laptop currentLaptop;
	private List<Laptop> laptopsList;
	private String currentSearchKeyword;
	private int countSearchResults;

	// Constructor
    public ManagedBeanLaptop(){
    }

    public Laptop getLaptopById(int id){
        return service.returnLaptopById(id);
    }
    
    public List<Laptop> getLaptopsList(){
    	laptopsList = service.returnAllLaptops();
        return laptopsList;
    }
    
    public List<Laptop> getLaptopsList(String searchKeyword){
    	countSearchResults = 0;
    	if(laptopsList != null) laptopsList.clear();
    	else laptopsList = new ArrayList<Laptop>();
		for (Laptop laptop : service.returnAllLaptops()) {
			if(laptop.getBrand().toLowerCase().contains(searchKeyword.toLowerCase())
					|| Integer.toString(laptop.getId()).contains(searchKeyword)
					|| laptop.getDisplay().toLowerCase().contains(searchKeyword.toLowerCase())
					|| laptop.getHardDisk().toLowerCase().contains(searchKeyword.toLowerCase())
					|| laptop.getModel().toLowerCase().contains(searchKeyword.toLowerCase())
					|| Integer.toString(laptop.getPrice()).contains(searchKeyword)
					|| Integer.toString(laptop.getId()).contains(searchKeyword)
					)
			{
				laptopsList.add(laptop);
				countSearchResults++;
			}
		}
        return laptopsList;
    }
        
    public Laptop getCurrentLaptop(){
        return currentLaptop;
    }
    
    public void setCurrentLaptop(Laptop laptop){
    	this.currentLaptop = laptop;
    }
    
    public Laptop getNewLaptop() {
		return newLaptop;
	}

	public void setNewLaptop(Laptop newLaptop) {
		this.newLaptop = newLaptop;
	}
    
    public String getLaptopDetailsPage(){
        return "showLaptopDetails";
    }
    
    public String addLaptop(){
    	newLaptop.setProductType("Laptop");
    	String result = service.addNew(newLaptop);
		if (result == ""){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Successfully added the laptop with product id " + newLaptop.getId() + ".", null));
			newLaptop = new Laptop();
	    	return "stockOfLaptops";
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Unsuccessful in adding new Laptop.", null));
			newLaptop = new Laptop();
			return "createNewLaptop";
		}
    }
        
    public double calcualtePrice(double unitPrice, int quantity)
    {
    	return unitPrice * quantity;
    }
    
    public String searchLaptopResultPage()
    {
    	return "foundLaptop";
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
}