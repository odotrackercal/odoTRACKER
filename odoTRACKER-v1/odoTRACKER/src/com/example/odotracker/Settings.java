package com.example.odotracker;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends Activity{
	
	Spinner spinner_languages;
	Spinner spinner_units;
	ArrayAdapter<CharSequence> adapter;
	Button button_done;
	private Locale myLocale;
	private int Id_lenguage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		Id_lenguage=1;
		
		spinner_languages = (Spinner) findViewById(R.id.language);
		// Create an ArrayAdapter using the string array and a default spinner layout
		//ArrayAdapter<CharSequence> 
		adapter = ArrayAdapter.createFromResource(this,
				R.array.languages, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner_languages.setAdapter(adapter);
		
		spinner_units= (Spinner) findViewById(R.id.distance_unit);
		// Create an ArrayAdapter using the string array and a default spinner layout
		//ArrayAdapter<CharSequence> 
		adapter = ArrayAdapter.createFromResource(this,
		        R.array.distance_units, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner_units.setAdapter(adapter);
		
		// Spinner item selection Listener 
		spinner_languages.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        
		//Done setting
		
		button_done=(Button)findViewById(R.id.button_done);
		
		button_done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 if (Id_lenguage==1 )setLocale("vi"); else setLocale("en");
				
			}
		});
		
		
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void setLocale(String lang) {
		myLocale = new Locale(lang);
		Resources res = getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);
		Intent refresh = new Intent(this, MainActivity.class);
		//recreate();
		finish();
		
		startActivity(refresh);
	}
	public class CustomOnItemSelectedListener implements OnItemSelectedListener {
		 
	    public void onItemSelected(AdapterView<?> parent, View view, int pos,
	            long id) {
	         
	        Toast.makeText(parent.getContext(),
	                "On Item Select : \n" + parent.getItemAtPosition(pos).toString(),
	                Toast.LENGTH_LONG).show();
	      Id_lenguage=(int)id;
	        
	    }
	 
	    @Override
	    public void onNothingSelected(AdapterView<?> arg0) {
	        // TODO Auto-generated method stub
	    	
	    }
	    
	 
	}

}
