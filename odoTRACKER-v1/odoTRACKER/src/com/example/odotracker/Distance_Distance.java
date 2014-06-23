package com.example.odotracker;

//import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
//import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Distance_Distance extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.distance_distance);
		
TabHost tabHost = getTabHost();
        
        // Tab for Distance
        TabSpec distancespec = tabHost.newTabSpec("Distance");
        // setting Title and Icon for the Tab
        distancespec.setIndicator("Distance");
        Intent distanceIntent = new Intent(this, Distance.class);
        distancespec.setContent(distanceIntent);
         
        // Tab for Log
        TabSpec logspec = tabHost.newTabSpec("Log");       
        //logspec.setIndicator("Songs", getResources().getDrawable(R.drawable.icon_songs_tab));
        logspec.setIndicator("Log");
        Intent logIntent = new Intent(this, Log.class);
        logspec.setContent(logIntent);
         
        // Tab for Email
        TabSpec emailspec = tabHost.newTabSpec("Email");
        emailspec.setIndicator("Email");
        Intent emailIntent = new Intent(this, Email.class);
        emailspec.setContent(emailIntent);
        
        // Tab for Settings
        TabSpec settingspec = tabHost.newTabSpec("Settings");
        settingspec.setIndicator("Settings");
        Intent settingsIntent = new Intent(this, Settings.class);
        settingspec.setContent(settingsIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(distancespec); // Adding distance tab
        tabHost.addTab(logspec); // Adding log tab
        tabHost.addTab(emailspec); // Adding email tab
        tabHost.addTab(settingspec); // Adding settings tab
		
        
			}
			

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	

}