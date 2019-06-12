package com.myself.app.sainsburystest.model;

import java.util.ArrayList;
import java.util.List;

public class JsonResponse {
	private List<Product> results;
	private Total total;
	
	public JsonResponse() {
		this.results = new ArrayList<Product>();
		this.total = new Total();
	}
	
	public void addProduct(Product product) {
		this.results.add(product);
	}
	
	public void calculateTotals() throws Exception {
		for (Product product : results) {
			double unitPrice = product.getUnitPrice();
			
			this.total.addGross(unitPrice);
		}
		
		this.total.calculateVat();
	}

	public List<Product> getResults() {
		return results;
	}

	public void setResults(List<Product> results) {
		this.results = results;
	}

	public Total getTotal() {
		return total;
	}

	public void setTotal(Total total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "JsonResponse [products=" + results + ", total=" + total + "]";
	}
}
	
