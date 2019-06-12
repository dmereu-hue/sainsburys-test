package com.myself.app.sainsburystest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Product {
	private String title;
	private double unitPrice;
	
	@JsonInclude(Include.NON_NULL)
	private Integer kCalPer100;
	
	private String description;
	
	public Product() {
		this.description = "";
	}
	
	public Product(String title, double unitPrice, int kCalPer100, String description) {
		this();
		this.title = title;
		this.unitPrice = unitPrice;
		this.kCalPer100 = kCalPer100;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getkCalPer100() {
		return kCalPer100;
	}

	public void setkCalPer100(Integer kCalPer100) {
		this.kCalPer100 = kCalPer100;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "[title=" + title + ", unitPrice=" + unitPrice + ", kCalPer100=" + kCalPer100 + ", description=" + description + "]";
	}
}
