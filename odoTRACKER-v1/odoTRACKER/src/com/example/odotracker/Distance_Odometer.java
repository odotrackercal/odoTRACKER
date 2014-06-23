package com.example.odotracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;



public class Distance_Odometer extends Activity {

	 private EditText Output;
	 private int year;
	 private int month;
	 private int day;
	 static final int DATE_PICKER_ID = 1111; 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.distance_odometer);
		
		//final View addView=getLayoutInflater().inflate(R.layout.save_button, null);
        //getActionBar().setCustomView(addView);
		//setCustomView(R.layout.save_button);
		
		 	Output = (EditText) findViewById(R.id.edit_date);
	 
	        // Get current date by calender
	        final Calendar c = Calendar.getInstance();
	        year  = c.get(Calendar.YEAR);
	        month = c.get(Calendar.MONTH);
	        day   = c.get(Calendar.DAY_OF_MONTH);
	 
	        // Show current date
	        Output.setText(new StringBuilder()
	                // Month is 0 based, just add 1
	                .append(month + 1).append("-").append(day).append("-")
	                .append(year).append(" "));
	  
	        // Button listener to show date picker dialog
	        Output.setOnClickListener(new OnClickListener() {
	 
	            @Override
	            public void onClick(View v) {
	                 
	                // On button click show datepicker dialog
	            	computeDistance();
	                showDialog(DATE_PICKER_ID);
	 
	            }
	        });
	        
	        /*Output.setOnFocusChangeListener(new OnFocusChangeListener() {
	            @Override
	            public void onFocusChange(View v, boolean hasFocus) 
	            {
	                if(hasFocus)
	                {
	                   //Show your calender here
	                	showDialog(DATE_PICKER_ID);
	                }
	            	else
	            	{
	                   //Hide your calender here
	            		
	                }
	            }
	        });*/
	
		
	/*	TabHost tabHost = getTabHost();
        
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
        tabHost.addTab(settingspec); // Adding settings tab*/
	        
	        
	    EditText edit_start_odometer= (EditText) findViewById(R.id.edit_start_odometer);
	    edit_start_odometer.setOnFocusChangeListener(new OnFocusChangeListener() {          

	               public void onFocusChange(View v, boolean hasFocus) {
	                   if(!hasFocus) {
	                       // set data for distance_travvelled
		                	   computeDistance();
	 	                   }
		               }
		           });
	    
	    EditText edit_end_odometer= (EditText) findViewById(R.id.edit_end_odometer);
	    edit_end_odometer.setOnFocusChangeListener(new OnFocusChangeListener() {          

	               public void onFocusChange(View v, boolean hasFocus) {
	                   if(!hasFocus) {
	                       // set data for distance_travvelled
	                	   computeDistance();
 	                   }
	               }
	           });
	        
	        
			}
	
	private void generateCsvFile(String sFileName){
		// before we open the file check to see if it already exists
        try
        {
            File root = Environment.getExternalStorageDirectory();
            File gpxfile = new File(root, sFileName);
            boolean fileExists = gpxfile.exists();
            FileWriter writer = new FileWriter(gpxfile, true);
            

            // if the file didn't already exist then we need to write out the header line
         	if (!fileExists)
         	{
         		writer.append(getResources().getString(R.string.text_date));
                writer.append(',');
         		writer.append(getResources().getString(R.string.start_file));
                writer.append(',');
                writer.append(getResources().getString(R.string.end_file));
                writer.append(',');
                writer.append(getResources().getString(R.string.distance_file));
                writer.append(',');
                writer.append(getResources().getString(R.string.text_notes));
                writer.append('\n');
         	}
   			// else assume that the file already has the correct header line
            writer.append(((EditText)findViewById(R.id.edit_date)).getText().toString());
            writer.append(',');
            writer.append(((EditText)findViewById(R.id.edit_start_odometer)).getText().toString());
            writer.append(',');
            writer.append(((EditText)findViewById(R.id.edit_end_odometer)).getText().toString());
            writer.append(',');
            writer.append(((EditText)findViewById(R.id.edit_distance_travelled)).getText().toString());
            writer.append(',');
            writer.append(((EditText)findViewById(R.id.edit_notes)).getText().toString());
            writer.append('\n');

            //generate whatever data you want
            writer.flush();
            writer.close();
            
            Context context = getApplicationContext();
            CharSequence text = getResources().getString(R.string.toast_saved);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            LinearLayout toastLayout = (LinearLayout) toast.getView();
            TextView toastTV = (TextView) toastLayout.getChildAt(0);
            toastTV.setTextSize(getResources().getDimension(R.dimen.text_font_size));
            toast.show();


            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } 
     }
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_PICKER_ID:
            // open datepicker dialog.
            // set date picker for current date
            // add pickerListener listner to date picker
            return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }
 
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {
 
        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                int selectedMonth, int selectedDay) {
             
            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;
 
            // Show selected date
            Output.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));
     
           }
        };
        public boolean computeDistance() {
        	
        	int start, stop;
     	   String temp1 = ((EditText)findViewById(R.id.edit_start_odometer)).getText().toString();
     	   String temp2 = ((EditText)findViewById(R.id.edit_end_odometer)).getText().toString();
            if((temp1.isEmpty() == false) && ((temp2.isEmpty() == false))){
                start = Integer.parseInt(temp1);
                stop = Integer.parseInt(temp2);
                int dis = stop - start;
                if (stop > start)
                {
             	   EditText distance = (EditText)findViewById(R.id.edit_distance_travelled);
             	   distance.setText(String.valueOf(dis));
             	   
             	   return true;
             	   
                }
             }
            
            return false;
                
        }
        
        public void saveOdometer(View view) {
    	    // Do something in response to button click
        	
        	if ( !((EditText)findViewById(R.id.edit_distance_travelled)).getText().toString().isEmpty())
        	{
        		generateCsvFile(getResources().getString(R.string.csv_history_file));   
        		cancelOdometer(view);
        	}
        	else
        	{
        		String temp1 = ((EditText)findViewById(R.id.edit_start_odometer)).getText().toString();
          	   	String temp2 = ((EditText)findViewById(R.id.edit_end_odometer)).getText().toString();
          	   	boolean isDistance = false;
                if((temp1.isEmpty() == false) && ((temp2.isEmpty() == false)))
                {
                	isDistance = computeDistance();
                	if (isDistance)
                	{
                		generateCsvFile(getResources().getString(R.string.csv_history_file));   
                		cancelOdometer(view);
                	}
                	else
                	{
                		Context context = getApplicationContext();
   	                 	CharSequence text = getResources().getString(R.string.toast_error);
   	                 	int duration = Toast.LENGTH_SHORT;
   	                 	Toast toast = Toast.makeText(context, text, duration);
   	                 	LinearLayout toastLayout = (LinearLayout) toast.getView();
   	                 	TextView toastTV = (TextView) toastLayout.getChildAt(0);
   	                 	toastTV.setTextSize(getResources().getDimension(R.dimen.text_font_size));
   	                 	toast.show();
                	}
                }
                else
                {
	        		 Context context = getApplicationContext();
	                 CharSequence text = getResources().getString(R.string.toast_error);
	                 int duration = Toast.LENGTH_SHORT;
	                 Toast toast = Toast.makeText(context, text, duration);
	                 LinearLayout toastLayout = (LinearLayout) toast.getView();
	                 TextView toastTV = (TextView) toastLayout.getChildAt(0);
	                 toastTV.setTextSize(getResources().getDimension(R.dimen.text_font_size));
	                 toast.show();
                }
        	}
    		
    	}
        
        public void cancelOdometer(View view) {
    	    // Do something in response to button click
    		 //Intent myIntent = new Intent(view.getContext(), Distance_Odometer.class);
             //startActivityForResult(myIntent, 0);
        	EditText  start = (EditText) findViewById(R.id.edit_start_odometer);
        	EditText  end = (EditText) findViewById(R.id.edit_end_odometer);
        	EditText  note = (EditText) findViewById(R.id.edit_notes);
        	EditText  distance = (EditText) findViewById(R.id.edit_distance_travelled);
        	start.setText(null);
        	end.setText(null);
        	note.setText(null);
        	distance.setText(null);
    	}
}
	
	
	
	

