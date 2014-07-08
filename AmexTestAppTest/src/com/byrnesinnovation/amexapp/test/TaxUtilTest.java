package com.byrnesinnovation.amexapp.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.byrnesinnovation.amexapp.Item;
import com.byrnesinnovation.amexapp.ItemOrigin;
import com.byrnesinnovation.amexapp.ItemType;
import com.byrnesinnovation.amexapp.TaxUtil;

import junit.framework.TestCase;

public class TaxUtilTest extends TestCase {

	public final void testRoundFirstExample() {
		BigDecimal inputBD = new BigDecimal("1.03");
		assertEquals(TaxUtil.roundBigDecimalValue(inputBD, TaxUtil.ROUNDING_INCREMENT, RoundingMode.UP).floatValue(),1.05f);	
	}
	
	public final void testRoundSecondExample() {
		BigDecimal inputBD = new BigDecimal("1.051");
		assertEquals(TaxUtil.roundBigDecimalValue(inputBD, TaxUtil.ROUNDING_INCREMENT, RoundingMode.UP).floatValue(),1.10f);	
	}
	
	public final void testRoundThirdExample() {
		BigDecimal inputBD = new BigDecimal("1.05");
		assertEquals(TaxUtil.roundBigDecimalValue(inputBD, TaxUtil.ROUNDING_INCREMENT, RoundingMode.UP).floatValue(),1.05f);	
	}
	
	public final void testRoundFourthExample() {
		BigDecimal inputBD = new BigDecimal("1.900001");
		assertEquals(TaxUtil.roundBigDecimalValue(inputBD, TaxUtil.ROUNDING_INCREMENT, RoundingMode.UP).floatValue(),1.95f);	
	}
	
	public final void testInput1PurchaseDomesticBookWithBaseSalesTaxTenAndImportTaxFive() {
		Item domesticBook = new Item(1, ItemType.BOOKS, ItemOrigin.DOMESTIC, "book", "12.49");
		BigDecimal salesTax = TaxUtil.calculateSalesTax(domesticBook, TaxUtil.BASE_SALES_TAX_PERCENT, TaxUtil.IMPORT_SALES_TAX_PERCENT, TaxUtil.ROUNDING_INCREMENT);
		assertEquals(salesTax.floatValue(),0f);	
	}
	
	public final void testInput1PurchaseDomesticMusicWithBaseSalesTaxTenAndImportTaxFive() {
		Item domesticMusic = new Item(1, ItemType.OTHER, ItemOrigin.DOMESTIC, "music", "14.99");
		BigDecimal salesTax = TaxUtil.calculateSalesTax(domesticMusic, TaxUtil.BASE_SALES_TAX_PERCENT, TaxUtil.IMPORT_SALES_TAX_PERCENT, TaxUtil.ROUNDING_INCREMENT);
		assertEquals(salesTax.floatValue(),1.50f);
	}
	
	public final void testInput2PurchaseDomesticChocolateWithBaseSalesTaxTenAndImportTaxFive() {
		Item domesticChocolate = new Item(1, ItemType.FOOD, ItemOrigin.DOMESTIC, "chocolate bar", "0.85");
		BigDecimal salesTax = TaxUtil.calculateSalesTax(domesticChocolate, TaxUtil.BASE_SALES_TAX_PERCENT, TaxUtil.IMPORT_SALES_TAX_PERCENT, TaxUtil.ROUNDING_INCREMENT);
		assertEquals(salesTax.floatValue(),0f);
	}
	
	public final void testInput2PurchaseImportChocolateWithBaseSalesTaxTenAndImportTaxFive() {
		Item importChocolate = new Item(1, ItemType.FOOD, ItemOrigin.IMPORT, "box of chocolates", "10.00");
		BigDecimal salesTax = TaxUtil.calculateSalesTax(importChocolate, TaxUtil.BASE_SALES_TAX_PERCENT, TaxUtil.IMPORT_SALES_TAX_PERCENT, TaxUtil.ROUNDING_INCREMENT);
		assertEquals(salesTax.floatValue(),0.50f);
	}
	
	public final void testInput2PurchaseImportPerfumeWithBaseSalesTaxTenAndImportTaxFive() {
		Item importPerfume = new Item(1, ItemType.OTHER, ItemOrigin.IMPORT, "bottle of perfume", "47.50");
		BigDecimal salesTax = TaxUtil.calculateSalesTax(importPerfume, TaxUtil.BASE_SALES_TAX_PERCENT, TaxUtil.IMPORT_SALES_TAX_PERCENT, TaxUtil.ROUNDING_INCREMENT);
		assertEquals(salesTax.floatValue(),7.15f);
	}
	
	public final void testInput3PurchaseImportPerfumeWithBaseSalesTaxTenAndImportTaxFive() {
		Item importPerfume = new Item(1, ItemType.OTHER, ItemOrigin.IMPORT, "bottle of perfume", "27.99");
		BigDecimal salesTax = TaxUtil.calculateSalesTax(importPerfume, TaxUtil.BASE_SALES_TAX_PERCENT, TaxUtil.IMPORT_SALES_TAX_PERCENT, TaxUtil.ROUNDING_INCREMENT);
		assertEquals(salesTax.floatValue(),4.20f);
	}
	
	public final void testInput3PurchaseDomesticPerfumeWithBaseSalesTaxTenAndImportTaxFive() {
		Item domesticPerfume = new Item(1, ItemType.OTHER, ItemOrigin.DOMESTIC, "bottle of perfume", "18.99");
		BigDecimal salesTax = TaxUtil.calculateSalesTax(domesticPerfume, TaxUtil.BASE_SALES_TAX_PERCENT, TaxUtil.IMPORT_SALES_TAX_PERCENT, TaxUtil.ROUNDING_INCREMENT);
		assertEquals(salesTax.floatValue(),1.90f);
	}
	
	public final void testInput3PurchaseDomesticPillsWithBaseSalesTaxTenAndImportTaxFive() {
		Item domesticPills = new Item(1, ItemType.MEDICAL, ItemOrigin.DOMESTIC, "packet of headache pills", "9.75");
		BigDecimal salesTax = TaxUtil.calculateSalesTax(domesticPills, TaxUtil.BASE_SALES_TAX_PERCENT, TaxUtil.IMPORT_SALES_TAX_PERCENT, TaxUtil.ROUNDING_INCREMENT);
		assertEquals(salesTax.floatValue(),0f);
	}
	
	public final void testInput3PurchaseImportChocolateWithBaseSalesTaxTenAndImportTaxFive() {
		Item importChocolate = new Item(1, ItemType.FOOD, ItemOrigin.IMPORT, "chocolates", "11.25");
		BigDecimal salesTax = TaxUtil.calculateSalesTax(importChocolate, TaxUtil.BASE_SALES_TAX_PERCENT, TaxUtil.IMPORT_SALES_TAX_PERCENT, TaxUtil.ROUNDING_INCREMENT);
		assertEquals(salesTax.floatValue(),0.60f);
	}

}
