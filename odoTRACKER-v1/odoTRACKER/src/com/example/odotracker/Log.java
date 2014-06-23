package com.example.odotracker;





import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import android.os.Bundle;
import android.os.Environment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Log extends Activity {
	
	 private Calendar from = Calendar.getInstance();
	 private Calendar to = Calendar.getInstance();
	 final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log);
		
		from.set(Calendar.YEAR, 1990);
		//Lookup our ListView
		ListView mList = (ListView)findViewById(R.id.mListView);
		
	
		
				
		//Create Adapter. The second parameter is required by ArrayAdapter
		//which our Adapter extends. In this example though it is unused,
		//so we'll pass it a "dummy" value of -1.
		CSVAdapter mAdapter;
		mAdapter = new CSVAdapter(this, -1, from, to);
			
		//attach our Adapter to the ListView. This will populate all of the rows.
		mList.setAdapter(mAdapter);
				
		mAdapter = null;	
		/*
		 * This listener will get a callback whenever the user clicks on a row. 
		 * The pos parameter will tell us which row got clicked.
		 * 
		 * For now we'll just show a Toast with the state capital for the state that was clicked.
		 */
		/*mList.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int pos,long id) {
			Toast.makeText(v.getContext(), mAdapter.getItem(pos).getCapital(), Toast.LENGTH_SHORT).show();
		}
		});*/
		
		mList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // When clicked
               final int pos = position;
             // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
               // 2. Chain together various setter methods to set the dialog characteristics
                String message = getResources().getString(R.string.message_delete).concat(" ").concat(Integer.toString(position)).concat("?");
                builder.setMessage(message)
                       .setTitle(R.string.title_delete)
                       .setPositiveButton(R.string.yes_delete,new DialogInterface.OnClickListener() {
                    	   public void onClick(DialogInterface dialog,int id) {
                    		   // if this button is clicked, delete the log
                    		   //MainActivity.this.finish();
                    		   removeLineFromFile(pos);
                    		   dialog.cancel();
                    		   onResume();
                    	   }
                       	})
                       	.setNegativeButton(R.string.cancel_delete,new DialogInterface.OnClickListener() {
                       		public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
                       			dialog.cancel();
                       		}
                       	});
                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    // Update your UI here.
	    CSVAdapter mAdapter;
	    mAdapter = new CSVAdapter(this, -1, from, to);
	    ListView mList = (ListView)findViewById(R.id.mListView);
	    mList.setAdapter(mAdapter);
	}
	
	public void removeLineFromFile(int pos) {

		try {
				File root = Environment.getExternalStorageDirectory();
				File gpxfile = new File(root, getResources().getString(R.string.csv_history_file));
				if (!gpxfile.exists())
					return;
	         
				//Construct the new file that will later be renamed to the original filename.
				
				//File tempFile = new File(gpxfile.getAbsolutePath() + ".tmp");
				File tempFile = new File(root, getResources().getString(R.string.csv_history_file) + ".tmp");

		  	  
				InputStream is = new FileInputStream(gpxfile); 
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				FileWriter writer = new FileWriter(tempFile, true);
				String line = null;
				int no = 0;

		  
				//Read from the original file and write to the new
				//unless content matches data to be removed.
			
				while ((line = reader.readLine()) != null) {
					 
					if ((no != pos))
					{
						writer.append(line);
						writer.append("\n");
						writer.flush();
					}
					no += 1;
				}
				
				reader.close();
				writer.close();
		  
				gpxfile.delete();
				tempFile.renameTo(gpxfile);

			}
			catch (IOException ex) {
				ex.printStackTrace();
				}

			}

}
