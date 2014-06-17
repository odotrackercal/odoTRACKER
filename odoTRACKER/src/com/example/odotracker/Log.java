package com.example.odotracker;





import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Log extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log);
		
		//Lookup our ListView
		ListView mList = (ListView)findViewById(R.id.mListView);
		
	
		
				
		//Create Adapter. The second parameter is required by ArrayAdapter
		//which our Adapter extends. In this example though it is unused,
		//so we'll pass it a "dummy" value of -1.
		CSVAdapter mAdapter;
		mAdapter = new CSVAdapter(this, -1);
			
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
	    mAdapter = new CSVAdapter(this, -1);
	    ListView mList = (ListView)findViewById(R.id.mListView);
	    mList.setAdapter(mAdapter);
	}
	

}
