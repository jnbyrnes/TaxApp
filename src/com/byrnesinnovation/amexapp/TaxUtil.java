package com.byrnesinnovation.amexapp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxUtil {
	public static final String BASE_SALES_TAX_PERCENT = "10";
	public static final String IMPORT_SALES_TAX_PERCENT = "5";
	public static final String ROUNDING_INCREMENT = "0.05";
	
	public static BigDecimal calculateSalesTax(Item item, String baseSalesTaxPercent, String importSalesTaxPercent, String roundingIncrement) {
		int baseSalesTaxPercentAsInt=0;
		int importSalesTaxPercentAsInt=0;
		int totalSalesTaxPercentAsInt=0;
		if (baseSalesTaxPercent == null) {
			baseSalesTaxPercent = BASE_SALES_TAX_PERCENT;
		}
		baseSalesTaxPercentAsInt = Integer.parseInt(baseSalesTaxPercent);
		if (importSalesTaxPercent == null) {
			importSalesTaxPercent = IMPORT_SALES_TAX_PERCENT;
		}
		importSalesTaxPercentAsInt = Integer.parseInt(importSalesTaxPercent);
		if (item.getType()==ItemType.OTHER) {
			totalSalesTaxPercentAsInt+=baseSalesTaxPercentAsInt;
		}
		if (item.getOrigin()==ItemOrigin.IMPORT) {
			totalSalesTaxPercentAsInt+=importSalesTaxPercentAsInt;
		}
		return roundBigDecimalValue(item.getAmount().multiply(new BigDecimal(totalSalesTaxPercentAsInt)).divide(new BigDecimal(100)), roundingIncrement, RoundingMode.UP);
	}
	
	public static BigDecimal roundBigDecimalValue(BigDecimal value, String incrementString, RoundingMode roundingMode) {
		BigDecimal increment = new BigDecimal(incrementString);
	    if (increment.signum() == 0) {
	        // 0 increment does not make much sense, but prevent division by 0
	        return value;
	    } else {
	        BigDecimal divided = value.divide(increment, 0, roundingMode);
	        BigDecimal result = divided.multiply(increment);
	        return result;
	    }
	}
}
