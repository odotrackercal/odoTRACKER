package com.example.odotracker;
//tracksify

import java.util.Locale;

import com.example.odotracker.config.Config;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


public class MainActivity extends TabActivity {

	private Locale myLocale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TabHost tabHost = getTabHost();
        
        // Tab for Distance
        TabSpec distancespec = tabHost.newTabSpec("Distance");
        // setting Title and Icon for the Tab
        distancespec.setIndicator(getString(R.string.Tab_Distance));
        Intent distanceIntent = new Intent(this, Distance_Odometer.class);
        distancespec.setContent(distanceIntent);
         
        // Tab for Log
        TabSpec logspec = tabHost.newTabSpec("Log");       
        //logspec.setIndicator("Songs", getResources().getDrawable(R.drawable.icon_songs_tab));
        logspec.setIndicator(getString(R.string.Tab_log));
        Intent logIntent = new Intent(this, Log.class);
        logspec.setContent(logIntent);
         
        // Tab for Email
        TabSpec emailspec = tabHost.newTabSpec("Email");
        emailspec.setIndicator(getString(R.string.Tab_Email));
        Intent emailIntent = new Intent(this, Email.class);
        emailspec.setContent(emailIntent);
        
        
        // Tab for Settings
        TabSpec settingspec = tabHost.newTabSpec("Settings");
        settingspec.setIndicator(getString(R.string.Tab_Setting));
        Intent settingsIntent = new Intent(this, Settings.class);
        settingspec.setContent(settingsIntent);
         
        // Adding all TabSpec to TabHost
        TextView x;
        tabHost.addTab(distancespec); // Adding distance tab
        tabHost.addTab(logspec); // Adding log tab
        tabHost.addTab(emailspec); // Adding email tab
        tabHost.addTab(settingspec); // Adding settings tab
        
        x = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        x.setTextSize(getResources().getDimension(R.dimen.tabhost_font_size));
        x.setTextSize(Config.getDisplayScreen(this));
        x = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        x.setTextSize(getResources().getDimension(R.dimen.tabhost_font_size));
        x.setTextSize(Config.getDisplayScreen(this));
        x = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        x.setTextSize(getResources().getDimension(R.dimen.tabhost_font_size));
        x.setTextSize(Config.getDisplayScreen(this));
        x = (TextView) tabHost.getTabWidget().getChildAt(3).findViewById(android.R.id.title);
        x.setTextSize(getResources().getDimension(R.dimen.tabhost_font_size));
        x.setTextSize(Config.getDisplayScreen(this));
      
        
        tabHost.setOnTabChangedListener(new OnTabChangeListener()
        {
        	public void onTabChanged(String tabID)
            {
        		if ((tabID.compareTo("Email") == 0) || (tabID.compareTo("Settings") == 0) || (tabID.compareTo("Log") == 0)) 
        		{
        			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        			imm.hideSoftInputFromWindow(tabHost.getApplicationWindowToken(), 0);
        		}
        		else
        		{
        			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
        			InputMethodManager.HIDE_IMPLICIT_ONLY);
        		}
        		
        		
            }
        });
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	

}
