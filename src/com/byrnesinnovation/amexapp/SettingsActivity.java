package com.byrnesinnovation.amexapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SettingsActivity extends Activity {
	static final int DEFAULT_INDEX = 3;
	
	private String fBaseTaxString;
	private int fBaseTaxIndex;
	private String fImportTaxString;
	private int fImportTaxIndex;
	private String fRoundingString;
	private int fRoundingIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
        Intent intent = getIntent();
        fBaseTaxString = intent.getStringExtra(MainActivity.EXTRA_BASE_SALES);
        fBaseTaxIndex = intent.getIntExtra(MainActivity.EXTRA_BASE_INDEX, DEFAULT_INDEX);
        fImportTaxString = intent.getStringExtra(MainActivity.EXTRA_IMPORT_SALES);
        fImportTaxIndex = intent.getIntExtra(MainActivity.EXTRA_IMPORT_INDEX, DEFAULT_INDEX);
        fRoundingString = intent.getStringExtra(MainActivity.EXTRA_ROUNDING_INCREMENT);
        fRoundingIndex = intent.getIntExtra(MainActivity.EXTRA_ROUNDING_INDEX, DEFAULT_INDEX);

		Spinner baseTaxSpinner = (Spinner) findViewById(R.id.settings_activity_base_tax_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> baseTaxAdapter = ArrayAdapter.createFromResource(this,
		        R.array.base_tax_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		baseTaxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		baseTaxSpinner.setAdapter(baseTaxAdapter);
		baseTaxSpinner.setOnItemSelectedListener(new BaseTaxSpinnerListener());
		baseTaxSpinner.setSelection(fBaseTaxIndex);
		
		Spinner importTaxSpinner = (Spinner) findViewById(R.id.settings_activity_import_tax_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> importTaxAdapter = ArrayAdapter.createFromResource(this,
		        R.array.import_tax_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		importTaxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		importTaxSpinner.setAdapter(importTaxAdapter);
		importTaxSpinner.setOnItemSelectedListener(new ImportTaxSpinnerListener());
		importTaxSpinner.setSelection(fImportTaxIndex);
		
		Spinner roundingSpinner = (Spinner) findViewById(R.id.settings_activity_rounded_increment_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> roundingAdapter = ArrayAdapter.createFromResource(this,
		        R.array.rounding_increment_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		roundingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		roundingSpinner.setAdapter(roundingAdapter);
		roundingSpinner.setOnItemSelectedListener(new RoundingSpinnerListener());
		roundingSpinner.setSelection(fRoundingIndex);
		
		Button settingsButton = (Button) findViewById(R.id.settings_activity_button);
		OnClickListener settingsButtonListener = new OnClickListener() {
			   @Override
			   public void onClick(View v) {
				   modifySettings();
			   }
		};
		settingsButton.setOnClickListener(settingsButtonListener);

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	private void modifySettings() {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(MainActivity.EXTRA_BASE_SALES, fBaseTaxString);
		intent.putExtra(MainActivity.EXTRA_BASE_INDEX, fBaseTaxIndex);
		intent.putExtra(MainActivity.EXTRA_IMPORT_SALES, fImportTaxString);
		intent.putExtra(MainActivity.EXTRA_IMPORT_INDEX, fImportTaxIndex);
		intent.putExtra(MainActivity.EXTRA_ROUNDING_INCREMENT, fRoundingString);
		intent.putExtra(MainActivity.EXTRA_ROUNDING_INDEX, fRoundingIndex);
	    setResult(MainActivity.SETTINGS_RESPONSE_CODE, intent);
	    finish();
	}
	
	public class BaseTaxSpinnerListener implements OnItemSelectedListener {
	    public void onItemSelected(AdapterView<?> parent, View view, 
	            int pos, long id) {
	        // An item was selected. You can retrieve the selected item using
	        // parent.getItemAtPosition(pos)
	    	fBaseTaxIndex = pos;
	    	fBaseTaxString = parent.getItemAtPosition(fBaseTaxIndex).toString();
	    }
	    
	    public void onNothingSelected(AdapterView<?> parent) {
	        // Another interface callback
	    }

	}
	
	public class ImportTaxSpinnerListener implements OnItemSelectedListener {
	    public void onItemSelected(AdapterView<?> parent, View view, 
	            int pos, long id) {
	        // An item was selected. You can retrieve the selected item using
	        // parent.getItemAtPosition(pos)
	    	fImportTaxIndex = pos;
	    	fImportTaxString = parent.getItemAtPosition(fImportTaxIndex).toString();
	    }
	    
	    public void onNothingSelected(AdapterView<?> parent) {
	        // Another interface callback
	    }

	}
	
	public class RoundingSpinnerListener implements OnItemSelectedListener {
	    public void onItemSelected(AdapterView<?> parent, View view, 
	            int pos, long id) {
	        // An item was selected. You can retrieve the selected item using
	        // parent.getItemAtPosition(pos)
	    	fRoundingIndex = pos;
	    	fRoundingString = parent.getItemAtPosition(fRoundingIndex).toString();
	    }
	    
	    public void onNothingSelected(AdapterView<?> parent) {
	        // Another interface callback
	    }

	}

}
