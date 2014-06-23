package com.example.odotracker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


//import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ParseException;
import android.os.Environment;
//import android.text.format.DateFormat;
import android.view.Gravity;
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
	SimpleDateFormat  dateformat = null;
		//private Activity activity;
	
	//We must accept the textViewResourceId parameter, but it will be unused
	//for the purposes of this example.
	public CSVAdapter(Context context, int textViewResourceId, Calendar from, Calendar to) {
		super(context, textViewResourceId);
		
		//Store a reference to the Context so we can use it to load a file from Assets.
		this.ctx = context;
		dateformat = new SimpleDateFormat(ctx.getResources().getString(R.string.date_format));
		//Load the data.
		loadArrayFromFile(from, to);	
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
//	     if (getItem(pos).getIndex().compareTo("No.") == 0)
	     {
	    	 col2.setGravity(Gravity.CENTER);
	    	 col3.setGravity(Gravity.CENTER);
	    	 col4.setGravity(Gravity.CENTER);
	    	 col5.setGravity(Gravity.CENTER);
	    	 col6.setGravity(Gravity.CENTER);
	    	 col1.setTypeface(null, Typeface.BOLD);
	    	 col2.setTypeface(null, Typeface.BOLD);
	    	 col3.setTypeface(null, Typeface.BOLD);
	    	 col4.setTypeface(null, Typeface.BOLD);
	    	 col5.setTypeface(null, Typeface.BOLD);
	    	 col6.setTypeface(null, Typeface.BOLD);
	    	 
	    	 
	     }
	     else
	     {
	    	 col6.setGravity(Gravity.LEFT);
	    	 col1.setTypeface(null, Typeface.NORMAL);
	    	 col2.setTypeface(null, Typeface.NORMAL);
	    	 col3.setTypeface(null, Typeface.NORMAL);
	    	 col4.setTypeface(null, Typeface.NORMAL);
	    	 col5.setTypeface(null, Typeface.NORMAL);
	    	 col6.setTypeface(null, Typeface.NORMAL);
	    	 
	     }
	     
	     
	      
	    
	        
	    
	        
	        
	
		//We could handle the row clicks from here. But instead
		//we'll use the ListView.OnItemClickListener from inside
		//of MainActivity, which provides some benefits over doing it here.
		
		/*mView.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Toast.makeText(parent.getContext(), getItem(pos).getCapital(), Toast.LENGTH_SHORT).show();
			}
		});*/
	     
	     /*android:background="@color/buttonBackGroundBlue"
	    	        android:textColor="@color/buttonTextWhite"*/ 
	     if (pos == 0)
	    	 convertView.setBackgroundColor( Color.CYAN);
	     else
	    	 convertView.setBackgroundColor(((pos != 0) && (pos % 2 == 0)) ? Color.WHITE : Color.LTGRAY);
	     return convertView;
	}
	
	/*
	 * Helper method that loads the data from the states.csv and builds
	 * each csv row into a State object which then gets added to the Adapter.
	 */
	private void loadArrayFromFile(Calendar from, Calendar to){
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
					String[] RowData = line.split(",",-1);
					
					if (no == 0)
					{
						cur.setIndex("No.");
						cur.setDate(RowData[0]);
						cur.setStart(RowData[1]);
						cur.setEnd(RowData[2]);
						cur.setDistance(RowData[3]);
						cur.setNotes(RowData[4]);
						no += 1;
						this.add(cur);
					}
					else
					{
						Calendar currentCalendar = Calendar.getInstance();
						try {
							ParsePosition pos = new ParsePosition(0);
							Date date = dateformat.parse(RowData[0], pos);
							currentCalendar.setTime(date);
						} catch (ParseException e) {
						    e.printStackTrace();
						}
						
						if ((currentCalendar.after(from) && currentCalendar.before(to)) || isSameDay(currentCalendar, from) || isSameDay(currentCalendar,to))
						{
							cur.setIndex(Integer.toString(no));
							cur.setDate(RowData[0]);
							cur.setStart(RowData[1]);
							cur.setEnd(RowData[2]);
							cur.setDistance(RowData[3]);
							cur.setNotes(RowData[4]);
							no += 1;
							this.add(cur);
						}
					}
					
					//Add the State object to the ArrayList (in this case we are the ArrayList).
					//this.add(cur);
				}
				
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isSameDay(Calendar cal1, Calendar cal2) {
	    if (cal1 == null || cal2 == null)
	        return false;
	    return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
	            && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) 
	            && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}
	
	

}
