package com.example.odotracker;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Distance extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.distance);
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user touches the "Distance" button */
	public void computeDistance(View view) {
	    // Do something in response to button click
		 Intent myIntent = new Intent(view.getContext(), Distance_Distance.class);
         startActivityForResult(myIntent, 0);
		
	}
	/** Called when the user touches the "Odometer" button */
	public void computeOdometer(View view) {
	    // Do something in response to button click
		 Intent myIntent = new Intent(view.getContext(), Distance_Odometer.class);
         startActivityForResult(myIntent, 0);
		
	}
	
	
	
	

}
