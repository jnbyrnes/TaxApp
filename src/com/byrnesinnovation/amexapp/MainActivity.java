package com.byrnesinnovation.amexapp;

import java.util.Locale;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {
	
	static final int SETTINGS_REQUEST_CODE = 100;
	static final int SETTINGS_RESPONSE_CODE = 200;
	static final String EXTRA_BASE_SALES = "base_sales";
	static final String EXTRA_BASE_INDEX = "base_index";
	static final String EXTRA_IMPORT_SALES = "import_sales";
	static final String EXTRA_IMPORT_INDEX = "import_index";
	static final String EXTRA_ROUNDING_INCREMENT = "rounding_increment";
	static final String EXTRA_ROUNDING_INDEX = "rounding_index";
	
	private String fBaseSalesTaxAmount;
	private int fBaseSalesIndex;
	private String fImportSalesTaxAmount;
	private int fImportSalesIndex;
	private String fRoundingIncrement;
	private int fRoundingIndex;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.main_activity_pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
		    Intent intent = new Intent(this, SettingsActivity.class);
		    if (fBaseSalesTaxAmount!=null) {
		    	intent.putExtra(EXTRA_BASE_SALES, fBaseSalesTaxAmount);
		    	intent.putExtra(EXTRA_BASE_INDEX, fBaseSalesIndex);
		    }
		    if (fImportSalesTaxAmount!=null) {
		    	intent.putExtra(EXTRA_IMPORT_SALES, fImportSalesTaxAmount);
		    	intent.putExtra(EXTRA_IMPORT_INDEX, fImportSalesIndex);
		    }
		    if (fRoundingIncrement!=null) {
		    	intent.putExtra(EXTRA_ROUNDING_INCREMENT, fRoundingIncrement);
		    	intent.putExtra(EXTRA_ROUNDING_INDEX, fRoundingIndex);
		    }
		    startActivityForResult(intent, SETTINGS_REQUEST_CODE);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SETTINGS_REQUEST_CODE && resultCode == SETTINGS_RESPONSE_CODE) {
			fBaseSalesTaxAmount = data.getStringExtra(EXTRA_BASE_SALES);
			fBaseSalesIndex = data.getIntExtra(EXTRA_BASE_INDEX, SettingsActivity.DEFAULT_INDEX);
			fImportSalesTaxAmount = data.getStringExtra(EXTRA_IMPORT_SALES);
			fImportSalesIndex = data.getIntExtra(EXTRA_IMPORT_INDEX, SettingsActivity.DEFAULT_INDEX);
			fRoundingIncrement = data.getStringExtra(EXTRA_ROUNDING_INCREMENT);
			fRoundingIndex = data.getIntExtra(EXTRA_ROUNDING_INDEX, SettingsActivity.DEFAULT_INDEX);
		}
	}
	
	String getBaseSalesTaxAmount() {
		if (fBaseSalesTaxAmount==null) {
			return TaxUtil.BASE_SALES_TAX_PERCENT;
		} else {
			return fBaseSalesTaxAmount;
		}
	}
	
	String getImportSalesTaxAmount() {
		if (fImportSalesTaxAmount==null) {
			return TaxUtil.IMPORT_SALES_TAX_PERCENT;
		} else {
			return fImportSalesTaxAmount;
		}
	}
	
	String getRoundingIncrement() {
		if (fRoundingIncrement==null) {
			return TaxUtil.ROUNDING_INCREMENT;
		} else {
			return fRoundingIncrement;
		}
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return ScrollListFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

}
