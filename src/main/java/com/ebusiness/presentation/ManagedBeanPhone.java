package com.ebusiness.presentation;

//Import the required library files
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.List;
import com.ebusiness.integration.models.Phone;
import com.ebusiness.service.ServiceInterface;

@ManagedBean //Name of bean will be same of class name 'managedBeanPhone'
@SessionScoped
public class ManagedBeanPhone {
    private ServiceInterface service;

    @EJB
    public void setSmartPhoneService(ServiceInterface service){
        this.service = service;
    }

    // Declare required variables
    private Phone newSmartPhone = new Phone();
	private Phone currentSmartPhone;
	private List<Phone> smartPhonesList;
	private String currentSearchKeyword;
	private int countSearchResults;

	// Constructor
    public ManagedBeanPhone(){
    }
    
    public Phone getSmartPhoneById(int id){
        return service.returnSmartPhoneById(id);
    }
    
    public List<Phone> getSmartPhonesList(){
    	smartPhonesList = service.returnAllSmartPhones();
        return smartPhonesList;
    }
    
    public List<Phone> getSmartPhonesList(String searchKeyword){
    	countSearchResults = 0;
    	if(smartPhonesList != null) smartPhonesList.clear();
    	else smartPhonesList = new ArrayList<Phone>();
		for (Phone smartPhone : service.returnAllSmartPhones()) {
			if(smartPhone.getBrand().toLowerCase().contains(searchKeyword.toLowerCase())
					|| Integer.toString(smartPhone.getId()).contains(searchKeyword)
					|| smartPhone.getDisplay().toLowerCase().contains(searchKeyword.toLowerCase())
					|| smartPhone.getConnectivity().toLowerCase().contains(searchKeyword.toLowerCase())
					|| smartPhone.getModel().toLowerCase().contains(searchKeyword.toLowerCase())
					|| Integer.toString(smartPhone.getPrice()).contains(searchKeyword)
					|| Integer.toString(smartPhone.getId()).contains(searchKeyword)
					)
			{
				smartPhonesList.add(smartPhone);
				countSearchResults++;
			}
		}
        return smartPhonesList;
    }
        
    public Phone getCurrentSmartPhone(){
        return currentSmartPhone;
    }
    
    public void setCurrentSmartPhone(Phone smartPhone){
    	this.currentSmartPhone = smartPhone;
    }
    
    public Phone getNewSmartPhone() {
		return newSmartPhone;
	}

	public void setNewSmartPhone(Phone newSmartPhone) {
		this.newSmartPhone = newSmartPhone;
	}
    
    public String getSmartPhoneDetailsPage(){
        return "showSmartPhoneDetails";
    }
    
    public String addSmartPhone(){
    	newSmartPhone.setProductType("Smartphone");
    	String result = service.addNew(newSmartPhone);
		if (result == ""){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Successfully added the smartPhone details with product id " + newSmartPhone.getId() + ".", null));
			newSmartPhone = new Phone();
	    	return "stockOfSmartPhones";
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Unsuccessful in adding new SmartPhone.", null));
			newSmartPhone = new Phone();
			return "";
		}
    }
        
    public double calcualtePrice(double unitPrice, int quantity)
    {
    	return unitPrice * quantity;
    }
    
    public String searchSmartPhoneResultPage()
    {
    	return "foundSmartPhone";
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