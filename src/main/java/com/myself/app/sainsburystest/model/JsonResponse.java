package com.myself.app.sainsburystest.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

public class JsonResponse {
	
	@JsonIgnore
	private List<Product> products;
	
	private Total total;
	
	public JsonResponse() {
		this.products = new ArrayList<Product>();
		this.total = new Total();
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	public void calculateTotals() throws Exception {
		for (Product product : products) {
			double unitPrice = product.getUnitPrice();
			
			this.total.addGross(unitPrice);
		}
		
		this.total.calculateVat();
	}

	@JsonValue
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Total getTotal() {
		return total;
	}

	public void setTotal(Total total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "JsonResponse [products=" + products + ", total=" + total + "]";
	}
}
	
