package com.myself.app.sainsburystest.test.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import com.myself.app.sainsburystest.model.JsonResponse;
import com.myself.app.sainsburystest.model.Product;
import com.myself.app.sainsburystest.utilities.PropertyUtility;
import com.myself.app.sainsburystest.utilities.ServiceUtility;

public class ServiceUtilityTest {
	
	@Test
	public void getHtmlDocumentByUrlTest() {
		Document doc = null;
		try {
			doc = ServiceUtility.getHtmlDocumentByUrl(PropertyUtility.getProperty("web_url"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(doc.title(), "Berries, cherries & currants | Sainsbury's");
	}
	
	@Test
	public void getWebUrlLinksTest() {
		List<String> webUrlLinks = null;
		try {
			webUrlLinks = ServiceUtility.getWebUrlLinks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(webUrlLinks);
		assertEquals(webUrlLinks.get(0), "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html");
	}
	
	@Test
	public void getSingleItemTest() {
		Product product = null;
		try {
			String productLink = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
			product = ServiceUtility.getProductByProductLink(productLink);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(product.getTitle(), "Sainsbury's Strawberries 400g");
		assertEquals(product.getUnitPrice(), 1.75);
		assertEquals(product.getkCalPer100(), 33);
		assertEquals(product.getDescription(), "by Sainsbury's strawberries");
	}
	
	@Test
	public void getProductInfosTest() {
		JsonResponse resp = null;
		try {
			resp = ServiceUtility.getProductInfos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Product first = resp.getProducts().get(0);
		
		assertEquals(first.getTitle(), "Sainsbury's Strawberries 400g");
		assertEquals(first.getUnitPrice(), 1.75);
		assertEquals(first.getkCalPer100(), 33);
		assertEquals(first.getDescription(), "by Sainsbury's strawberries");
	}
	
	@Test
	public void calculateTotalsTest() {
		JsonResponse resp = null;
		try {
			resp = new JsonResponse();
			
			resp.addProduct(new Product("Sainsbury's Strawberries 400g", 1.75, 33, "by Sainsbury's strawberries"));
			resp.addProduct(new Product("Sainsbury's Blueberries 200g", 1.75, 45, "by Sainsbury's blueberries"));
			resp.addProduct(new Product("Sainsbury's Cherry Punnet 200g", 1.5, 52, "Cherries"));
			
			resp.calculateTotals();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(resp.getTotal().getGross(), 5);
		assertEquals(resp.getTotal().getVat(), 0.83);
	}
	
	@Test
	public void getProductInfosJSONStringTest() {
		String result = null;
		try {
			result = ServiceUtility.getProductInfosJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(result);
	}
}
