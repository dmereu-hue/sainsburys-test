package com.myself.app.sainsburystest.main;

import com.myself.app.sainsburystest.utilities.ServiceUtility;

public class Main {
	public static void main(String[] args) {
		String result = null;
		try {
			result = ServiceUtility.getProductInfosJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(result);
	}
}
