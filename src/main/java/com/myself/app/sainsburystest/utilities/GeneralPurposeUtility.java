package com.myself.app.sainsburystest.utilities;

public class GeneralPurposeUtility {
	public static String createStringForOnlyNumbers(String s) throws Exception {
		return s.replaceAll("[^\\d.]", "");
	}
	
	public static double convertToDouble(String s) throws Exception {
		return formatForDigits(new Double(s));
	}
	
	public static double formatForDigits(double n) throws Exception {
		return Math.round(n * 100.0) / 100.0;
	}
}
