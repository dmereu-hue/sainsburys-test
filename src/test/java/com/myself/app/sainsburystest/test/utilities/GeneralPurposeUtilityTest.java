package com.myself.app.sainsburystest.test.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.myself.app.sainsburystest.utilities.GeneralPurposeUtility;

public class GeneralPurposeUtilityTest {

	@Test
	public void createStringForOnlyNumbersTest() {
		String s1 = null;
		String s2 = null;
		
		try {
			s1 = GeneralPurposeUtility.createStringForOnlyNumbers("£1.75/unit");
			s2 = GeneralPurposeUtility.createStringForOnlyNumbers("33kcal");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(s1, "1.75");
		assertEquals(s2, "33");
	}
	
	@Test
	public void formatNumberTest() {
		double n1 = 0;
		try {
			n1 = GeneralPurposeUtility.convertToDouble("1.75909");
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(n1, 1.76);
	}
}
