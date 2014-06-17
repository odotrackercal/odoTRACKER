package com.example.odotracker;

import java.io.File;

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

public class Email extends Activity {

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
		String pathname= Environment.getExternalStorageDirectory().getAbsolutePath();
		String filename=getResources().getString(R.string.csv_history_file);
		File file=new File(pathname, filename);
		Intent i = new Intent(Intent.ACTION_SEND);
		i.putExtra(Intent.EXTRA_SUBJECT, "OdoTracker Log");
		i.putExtra(Intent.EXTRA_TEXT, "Please see the attached file(s) for the odometer log.");
		i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
		i.setType("text/plain");
		startActivity(Intent.createChooser(i, "You are using"));



		
	}
	

}
