package com.myself.app.sainsburystest.test.utilities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.myself.app.sainsburystest.utilities.PropertyUtility;

public class PropertyUtilityTest {
	
	@Test
	public void isPropertyNotNullTest() {
		
		String groceriesUrl = null;
		
		try {
			
			groceriesUrl = PropertyUtility.getProperty("web_url");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(groceriesUrl);
	}
}
