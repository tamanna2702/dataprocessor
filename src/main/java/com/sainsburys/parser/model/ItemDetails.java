package com.sainsburys.parser.model;

public class ItemDetails {

	private String title;
	
	private String size;
	
	private double unitPrice;
	
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ItemDetails [title=" + title + ", size=" + size + ", unitPrice=" + unitPrice + ", description="
				+ description + "]";
	}
	
	
}
