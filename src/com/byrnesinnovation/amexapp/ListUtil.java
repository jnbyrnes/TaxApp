package com.byrnesinnovation.amexapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;

public class ListUtil {
	private static final String NO_ITEMS_MESSAGE = "No Items Available.";
	private static final String TAG_COUNT = "count";
	private static final String TAG_TYPE = "type";
	private static final String TAG_ORIGIN = "origin";
	private static final String TAG_NAME = "name";
	private static final String TAG_AMOUNT = "amount";
	private static final String TAG_SALES_TAX = "sales_tax";
	private static final String TAG_SUBTOTAL = "subtotal";
	
   static List<HashMap<String, Object>> buildListOfItems(Context activity, List<Item> items) {
	   	ArrayList<HashMap<String, Object>> mList = new ArrayList<HashMap<String, Object>>();
	   	if (items != null && items.size() > 0) {
		    	for (Item item : items) {
		    		// creating new HashMap
		            HashMap<String, Object> map = new HashMap<String, Object>();
		
		            // adding each child node to HashMap key => value
		            map.put(TAG_COUNT, "("+item.getCount()+") ");
		            map.put(TAG_TYPE, item.getType());
		            map.put(TAG_ORIGIN, item.getOrigin());
		            map.put(TAG_NAME, item.getName());
		            map.put(TAG_AMOUNT, activity.getResources().getString(R.string.fragment_amount)+": "+item.getAmount());
		            if (item.getSalesTax()!=null){
		            	map.put(TAG_SALES_TAX, activity.getResources().getString(R.string.fragment_sales_tax)+": "+item.getSalesTax());
		            	map.put(TAG_SUBTOTAL, activity.getResources().getString(R.string.fragment_subtotal)+": "+item.getAmount().add(item.getSalesTax()));
		            }
		            
		            // adding HashList to ArrayList
		            mList.add(map);
		    	}
	   	} else {
	   		HashMap<String, Object> map = new HashMap<String, Object>();
	   		map.put(TAG_COUNT, "");
	   		map.put(TAG_TYPE, "");
	   		map.put(TAG_ORIGIN, "");
	   		map.put(TAG_NAME, NO_ITEMS_MESSAGE);
	   		map.put(TAG_AMOUNT, "");
	   		map.put(TAG_SALES_TAX, "");
	   		map.put(TAG_SUBTOTAL, "");
	   		mList.add(map);
	   	}
	   	return mList;
   }
	   
   static String[] buildItemElementTitle() {
	   return new String[] {TAG_COUNT, TAG_TYPE, TAG_ORIGIN, TAG_NAME, TAG_AMOUNT, TAG_SALES_TAX, TAG_SUBTOTAL};
   }

   static int[] buildItemElementLabel() {
	   return new int[] { R.id.item_list_count_label, R.id.item_list_type_label, R.id.item_list_origin_label, R.id.item_list_name_label, R.id.item_list_amount_label, R.id.item_list_sales_tax_label, R.id.item_list_subtotal_label};
   }

}
