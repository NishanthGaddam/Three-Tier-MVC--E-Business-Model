package com.ebusiness.integration.models;

// Import the required library files
import javax.persistence.Entity;

// Define the entity Product, it will also map to table name Product because it is inheriting the Product
@Entity
public class Movies extends Product 
{
	// Primary key and few attributes already in class 'Product'
	
	// Declare other specific attributes
    private String processor;
    private String ram;
    private String hardDisk;
    private String networkInterface;
    private String opticalDrive;
	public String getProcessor() {
		return processor;
	}
	
	// Attribute value getter methods
	public String getRam() {
		return ram;
	}
	public String getHardDisk() {
		return hardDisk;
	}
	public String getNetworkInterface() {
		return networkInterface;
	}
	public String getOpticalDrive() {
		return opticalDrive;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	
	// Attribute value setter methods
	public void setRam(String ram) {
		this.ram = ram;
	}
	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}
	public void setNetworkInterface(String networkInterface) {
		this.networkInterface = networkInterface;
	}
	public void setOpticalDrive(String opticalDrive) {
		this.opticalDrive = opticalDrive;
	}
    }

