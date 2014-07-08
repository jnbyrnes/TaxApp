package com.byrnesinnovation.amexapp;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;;

public class ScrollListFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private static final List<Item> itemList1 = Arrays.asList(new Item(1, ItemType.BOOKS, ItemOrigin.DOMESTIC, "book", "12.49"),
			new Item(1, ItemType.OTHER, ItemOrigin.DOMESTIC, "music CD", "14.99"),
			new Item(1, ItemType.FOOD, ItemOrigin.DOMESTIC, "chocolate bar", "0.85"));
	private static final List<Item> itemList2 = Arrays.asList(new Item(1, ItemType.FOOD, ItemOrigin.IMPORT, "box of chocolates", "10.00"),
			new Item(1, ItemType.OTHER, ItemOrigin.IMPORT, "bottle of perfume", "47.50"));
	private static final List<Item> itemList3 = Arrays.asList(new Item(1, ItemType.OTHER, ItemOrigin.IMPORT, "bottle of perfume", "27.99"),
			new Item(1, ItemType.OTHER, ItemOrigin.DOMESTIC, "bottle of perfume", "18.99"),
			new Item(1, ItemType.MEDICAL, ItemOrigin.DOMESTIC, "packet of headache pills", "9.75"),
			new Item(1, ItemType.FOOD, ItemOrigin.IMPORT, "box of chocolates", "11.25"));
	
	private MainActivity fActivity;
	private SimpleAdapter fAdapter;
	private List<Item> fItems;
	
	private int fSection;
	
	private ListView fListView;
	private TextView fTotalSalesTaxView;
	private TextView fTotalAmountView;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static ScrollListFragment newInstance(int sectionNumber) {
		ScrollListFragment fragment = new ScrollListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	fActivity = (MainActivity) getActivity();
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        fListView = (ListView) v.findViewById(R.id.main_fragment_listview);
        fTotalSalesTaxView = (TextView) v.findViewById(R.id.main_fragment_sales_tax_label);
        fTotalAmountView = (TextView) v.findViewById(R.id.main_fragment_total_label);
        Button receiptButton = (Button) v.findViewById(R.id.main_fragment_button);
        OnClickListener receiptButtonListener = new OnClickListener() {
			   @Override
			   public void onClick(View v) {
				   calculateTaxAndTotals();
			   }
		};
		receiptButton.setOnClickListener(receiptButtonListener);
        fSection = getArguments().getInt(ARG_SECTION_NUMBER);
        if (fSection==1) {
        	fItems = itemList1;
        } else if (fSection==2) {
        	fItems = itemList2;
        } else {
        	fItems = itemList3;
        }
        return v;
    }
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupList();
    }
    
    void setupList() {
    	// create the list item mapping
        String[] from = ListUtil.buildItemElementTitle();
        int[] to = ListUtil.buildItemElementLabel();
        
        // Updating data into ListView
        fAdapter = new ItemListAdapter(fActivity, ListUtil.buildListOfItems(fActivity, fItems), R.layout.item_list_entry, from, to);
        fListView.setAdapter(fAdapter);
    }
	
    void calculateTaxAndTotals() {
    	if (fItems!=null && fItems.size()>0) {
    		BigDecimal totalSalesTax = new BigDecimal("0");
    		BigDecimal totalAmount = new BigDecimal("0");
	    	// Calculate sales tax and totals
	    	for (Item item : fItems) {
	    		item.setSalesTax(TaxUtil.calculateSalesTax(item, fActivity.getBaseSalesTaxAmount(), fActivity.getImportSalesTaxAmount(), fActivity.getRoundingIncrement()));
	    		totalSalesTax = totalSalesTax.add(item.getSalesTax());
	    		totalAmount = totalAmount.add(item.getAmount());
	    		totalAmount = totalAmount.add(item.getSalesTax());
	    	}
	    	
	    	// Update data into ListView
	    	setupList();
	    	
	        // Modify totals
	        fTotalSalesTaxView.setText("Total Sales Tax: "+totalSalesTax);
	        fTotalAmountView.setText("Total Amount: "+totalAmount);
    	}
    }
	
    public class ItemListAdapter extends SimpleAdapter {

        public ItemListAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

    }

}
