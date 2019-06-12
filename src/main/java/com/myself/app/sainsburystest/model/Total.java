package com.myself.app.sainsburystest.model;

import com.myself.app.sainsburystest.utilities.GeneralPurposeUtility;

public class Total {
	private double gross;
	private double vat;
	
	public void addGross(double unitPrice) {
		this.gross += unitPrice;
	}
	
	public void calculateVat() throws Exception {
		double net = GeneralPurposeUtility.formatForDigits(gross / 1.2);
		this.vat =  GeneralPurposeUtility.formatForDigits(gross - net);
	}
	
	public double getGross() {
		return gross;
	}
	
	public void setGross(double gross) {
		this.gross = gross;
	}
	
	public double getVat() {
		return vat;
	}
	
	public void setVat(double vat) {
		this.vat = vat;
	}

	@Override
	public String toString() {
		return "[gross=" + gross + ", vat=" + vat + "]";
	}
}
