package com.byrnesinnovation.amexapp;

import java.math.BigDecimal;

public class Item {
	
	private int fCount;
	private ItemType fType;
	private ItemOrigin fOrigin;
	private String fName;
	private BigDecimal fAmount;
	private BigDecimal fSalesTax;
	
	public Item(int count, ItemType type, ItemOrigin origin, String name, String amount) {
		fCount = count;
		fType = type;
		fOrigin = origin;
		fName = name;
		fAmount = new BigDecimal(amount);
	}
	
	public int getCount() {
		return fCount;
	}
	
	public ItemType getType() {
		return fType;
	}
	
	public ItemOrigin getOrigin() {
		return fOrigin;
	}
	
	public String getName() {
		return fName;
	}
	
	public BigDecimal getAmount() {
		return fAmount;
	}
	
	public void setSalesTax(BigDecimal salesTax) {
		fSalesTax = salesTax;
	}
	
	public BigDecimal getSalesTax() {
		return fSalesTax;
	}
	
}
