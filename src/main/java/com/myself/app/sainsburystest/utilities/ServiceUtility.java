package com.myself.app.sainsburystest.utilities;

import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myself.app.sainsburystest.model.JsonResponse;
import com.myself.app.sainsburystest.model.Product;

public class ServiceUtility {
	public static Document getHtmlDocumentByUrl(String url) throws Exception {
		return Jsoup.connect(url).get();
	}
	
	public static List<String> getWebUrlLinks() throws Exception {
		List<String> links = null;
		
		try {
			Document doc = ServiceUtility.getHtmlDocumentByUrl(PropertyUtility.getProperty("web_url"));
			
			String sainsburysUrl = PropertyUtility.getProperty("sainsburys_url") ;
			
			links = doc.select("ul.productLister a").stream()
					.map(elem -> elem.attr("href"))
					.map(elem -> sainsburysUrl + "/" + elem.replace("../", ""))
					.filter(elem -> elem.contains("berries-cherries-currants"))
					.collect(Collectors.toList()); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return links;
	}
	
	public static Product getProductByProductLink(String productLink) throws Exception {
		
		Document singleElementDoc = ServiceUtility.getHtmlDocumentByUrl(productLink);
		
		Product product = new Product();
		
		Elements title = singleElementDoc.select(PropertyUtility.getProperty("title_selector"));
		if (title.size() > 0) {
			product.setTitle(title.get(0).text().trim());
		}
		
		Elements unitPrice = singleElementDoc.select(PropertyUtility.getProperty("unit_price_selector"));
		if (unitPrice.size() > 0) {
			product.setUnitPrice(GeneralPurposeUtility.convertToDouble(GeneralPurposeUtility.createStringForOnlyNumbers(unitPrice.get(0).text().trim())));
		}
		
		try {
			Elements kCalPer100g = singleElementDoc.select(PropertyUtility.getProperty("k_cal_per_100_g_selector")).get(1).select("td");
			if (kCalPer100g.size() > 0) {
				product.setkCalPer100(Integer.parseInt(GeneralPurposeUtility.createStringForOnlyNumbers(kCalPer100g.get(0).text().trim())));
			}
		} catch (Exception e) {
			//System.out.println(product.getTitle() + ": kCalPer100g not found");
		}
		
		
		Elements description = singleElementDoc.select(PropertyUtility.getProperty("description_selector"));
		if (description.size() > 0) {
			product.setDescription(description.get(0).text().trim());
		} 
		
		return product;
	}
	
	public static JsonResponse getProductInfos() throws Exception {
		
		List<String> webUrlLinks = getWebUrlLinks();
		
		JsonResponse resp = new JsonResponse();
		
		for (String link : webUrlLinks) {
			Product product = getProductByProductLink(link);
			resp.addProduct(product);
		}
		
		resp.calculateTotals();
		
		return resp;
	}
	
	public static String getProductInfosJSONString() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(getProductInfos());
	}
}
