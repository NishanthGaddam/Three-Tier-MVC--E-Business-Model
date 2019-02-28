package com.ebusiness.integration.models;

// Import the required library files
import javax.persistence.Entity;

// Define the entity Phone, it will also map to table name Product because it is inheriting the Product
@Entity
public class Phone extends Product {
	// Primary key and few attributes already in class 'Product'
	
	// Declare other specific attributes
    private String connectivity;
    private String locationFeature;
    private String numberOfSim;
    private String simType;
    
    // Attribute value getter methods
	public String getConnectivity() {
		return connectivity;
	}
	public String getLocationFeature() {
		return locationFeature;
	}
	public String getNumberOfSim() {
		return numberOfSim;
	}
	public String getSimType() {
		return simType;
	}
	
	// Attribute value setter methods
	public void setConnectivity(String connectivity) {
		this.connectivity = connectivity;
	}
	public void setLocationFeature(String locationFeature) {
		this.locationFeature = locationFeature;
	}
	public void setNumberOfSim(String numberOfSim) {
		this.numberOfSim = numberOfSim;
	}
	public void setSimType(String simType) {
		this.simType = simType;
	}    
}