package com.example.odotracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



/*
 * Very basic Custom Adapter that takes state name,capital pairs out of a csv
 * file from the assets and uses those values to build a List of State objects.
 * Overrides the default getView() method to return a TextView with the state name.
 * 
 * ArrayAdapter - a type of Adapter that works a lot like ArrayList.
 */
public class CSVAdapter extends ArrayAdapter<OdoInfo>{
	Context ctx;
	//private Activity activity;
	
	//We must accept the textViewResourceId parameter, but it will be unused
	//for the purposes of this example.
	public CSVAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		
		//Store a reference to the Context so we can use it to load a file from Assets.
		this.ctx = context;

		
		//Load the data.
		loadArrayFromFile();	
	}
	
	
	
	/*
	 * getView() is the method responsible for building a View out of a some data that represents
	 * one row within the ListView. For this example our row will be a single TextView that
	 * gets populated with the state name.
	 * (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int pos, View convertView, final ViewGroup parent){
		/*
		 * Using convertView is important. The system will pass back Views that have been
		 * created but scrolled off of the top (or bottom) of the screen, and thus are no
		 * longer being shown on the screen. Since they are unused, we can "recycle" them
		 * instead of creating a new View object for every row, which would be wasteful, 
		 * and lead to poor performance. The diference may not be noticeable in this
		 * small example. But with larger more complex projects it will make a significant
		 * improvement by recycling Views rather than creating new ones for each row.
		 */
		
		 if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = inflater.inflate(R.layout.listrow, null);
	        }
		 
		 TextView col1 = (TextView) convertView.findViewById(R.id.column1);
	     TextView col2 = (TextView) convertView.findViewById(R.id.column2);
	     TextView col3 = (TextView) convertView.findViewById(R.id.column3);
	     TextView col4 = (TextView) convertView.findViewById(R.id.column4);
	     TextView col5 = (TextView) convertView.findViewById(R.id.column5);
	     TextView col6 = (TextView) convertView.findViewById(R.id.column6);
	        
	     col1.setText(getItem(pos).getIndex());
	     col2.setText(getItem(pos).getDate());
	     col3.setText(getItem(pos).getStart());
	     col4.setText(getItem(pos).getEnd());
	     col5.setText(getItem(pos).getDistance());
	     col6.setText(getItem(pos).getNotes());
	     
	     if (pos == 0)
	     {
	    	 col1.setTextColor(Color.RED);
	    	 col1.setBackgroundColor(Color.GREEN);
	     }
	     else
	     {
	    	 col1.setTextColor(Color.BLACK);
	     }
	     
	     
	    
	        
	    
	        
	        
	
		//We could handle the row clicks from here. But instead
		//we'll use the ListView.OnItemClickListener from inside
		//of MainActivity, which provides some benefits over doing it here.
		
		/*mView.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Toast.makeText(parent.getContext(), getItem(pos).getCapital(), Toast.LENGTH_SHORT).show();
			}
		});*/
		
	     return convertView;
	}
	
	/*
	 * Helper method that loads the data from the states.csv and builds
	 * each csv row into a State object which then gets added to the Adapter.
	 */
	private void loadArrayFromFile(){
		try {
			// Get input stream and Buffered Reader for our data file.
			
			 File root = Environment.getExternalStorageDirectory();
	         File gpxfile = new File(root, ctx.getResources().getString(R.string.csv_history_file));
	         boolean fileExists = gpxfile.exists();
	            
			if (fileExists)
			{
		        InputStream is = new FileInputStream(gpxfile); 
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line;
				int no = 0;
				
				//Read each line
				while ((line = reader.readLine()) != null) {
					OdoInfo cur = new OdoInfo();
					String[] RowData = line.split(",");
					if (no == 0)
					{
						no += 1;
						cur.setIndex("No.");
						cur.setDate(RowData[0]);
						cur.setStart(RowData[1]);
						cur.setEnd(RowData[2]);
						cur.setDistance(RowData[3]);
						cur.setNotes(RowData[4]);
					
					}
					else
					{
						no += 1;
						cur.setIndex(Integer.toString(no));
						cur.setDate(RowData[0]);
						cur.setStart(RowData[1]);
						cur.setEnd(RowData[2]);
						cur.setDistance(RowData[3]);
						if (RowData[4].isEmpty())
							cur.setNotes("1");
						else
							cur.setNotes(RowData[4]);
					}
					
					//Add the State object to the ArrayList (in this case we are the ArrayList).
					this.add(cur);
				}
				
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
