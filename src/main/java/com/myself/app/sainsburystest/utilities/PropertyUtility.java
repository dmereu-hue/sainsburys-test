package com.myself.app.sainsburystest.utilities;

import java.util.Properties;

public class PropertyUtility {
	
	public static String getProperty(String propertyName) throws Exception {
		Properties prop = new Properties();
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		prop.load(classloader.getResourceAsStream("properties.properties"));
		
		return prop.getProperty(propertyName);
	}
}
