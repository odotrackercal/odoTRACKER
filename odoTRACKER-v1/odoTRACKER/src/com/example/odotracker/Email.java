package com.example.odotracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Email extends Activity {

	File file = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		
		
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user touches the "Send" button */
	public void sendEmail(View view) {
	    // Do something in response to button click
		//get date from datePicker
		DatePicker datePicker1 = (DatePicker)findViewById(R.id.datePicker1);
		DatePicker datePicker2 = (DatePicker)findViewById(R.id.datePicker2);
		int day1 = datePicker1.getDayOfMonth();
	    int month1 = datePicker1.getMonth();
	    int year1 =  datePicker1.getYear();
	    
	    int day2 = datePicker2.getDayOfMonth();
	    int month2 = datePicker2.getMonth();
	    int year2 =  datePicker2.getYear();

	    Calendar from = Calendar.getInstance();
	    from.set(year1, month1, day1);
	    Calendar to = Calendar.getInstance();
	    to.set(year2, month2, day2);
	    
	    CSVAdapter mAdapter;
		mAdapter = new CSVAdapter(this, -1, from, to);
		
		if (mAdapter.getCount()>0)
		{
		
			SimpleDateFormat sdf = new SimpleDateFormat(getResources().getString(R.string.date_format));
					
			String sFileName = "History-";
			sFileName = sFileName.concat(sdf.format(from.getTime())).concat("-").concat(sdf.format(to.getTime())).concat(".csv");
			generateCsvFile(mAdapter, sFileName);
				
		    
			String pathname= Environment.getExternalStorageDirectory().getAbsolutePath();
			//File 
			file=new File(pathname, sFileName);
			
			Intent i = new Intent(Intent.ACTION_SEND);
			i.putExtra(Intent.EXTRA_SUBJECT, "odoTRACKER Log");
			i.putExtra(Intent.EXTRA_TEXT, "Please see the attached file(s) for the odometer log.");
			i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
			i.setType("text/plain");
			startActivity(Intent.createChooser(i, "You are using"));
			
			file.deleteOnExit();
		}
		else
		{
			Context context = getApplicationContext();
            CharSequence text = getResources().getString(R.string.toast_no_send);
	        int duration = Toast.LENGTH_SHORT;
	        Toast toast = Toast.makeText(context, text, duration);
	        LinearLayout toastLayout = (LinearLayout) toast.getView();
	        TextView toastTV = (TextView) toastLayout.getChildAt(0);
	        toastTV.setTextSize(getResources().getDimension(R.dimen.text_font_size));
	        toast.show();

		}



		
	}
	
	
	private void generateCsvFile(CSVAdapter mAdapter, String sFileName){
		// before we open the file check to see if it already exists
        try
        {
            File root = Environment.getExternalStorageDirectory();
            File gpxfile = new File(root, sFileName);
            boolean fileExists = gpxfile.exists();
            if (fileExists)
            {
            	fileExists = !gpxfile.delete();
            
            }
            FileWriter writer = new FileWriter(gpxfile, true);
            int no = 0;
            
            
                     	
         	for (int pos=0; pos<mAdapter.getCount(); pos++)
         	{
	   			// else assume that the file already has the correct header line
         		
         		if (no == 0)
         		{
         			writer.append(getResources().getString(R.string.text_no));
         		}
         		else
         		{
         			writer.append(Integer.toString(no));
         		}
	            writer.append(',');
	            writer.append(mAdapter.getItem(pos).getDate());
	            writer.append(',');
	            writer.append(mAdapter.getItem(pos).getStart());
	            writer.append(',');
	            writer.append(mAdapter.getItem(pos).getEnd());
	            writer.append(',');
	            writer.append(mAdapter.getItem(pos).getDistance());
	            writer.append(',');
	            writer.append(mAdapter.getItem(pos).getNotes());
	            writer.append('\n');
	            no += 1;
         	}

            //generate whatever data you want
            writer.flush();
            writer.close();
            
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } 
     }
	
	@Override  
	protected void onResume() {
          // TODO Auto-generated method stub
		//if(file.exists())  
		if(file!= null)
          {
               file.delete();
          }
          super.onResume();
      }

}
