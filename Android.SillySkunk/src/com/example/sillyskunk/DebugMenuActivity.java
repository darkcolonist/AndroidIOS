package com.example.sillyskunk;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DebugMenuActivity extends ListActivity {

	String classes[] = {
			"BasicInfoActivity", "BasicInfoSummaryActivity", "MainActivity", "SplashActivity"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(
				DebugMenuActivity.this, 
				android.R.layout.simple_expandable_list_item_1, 
				classes));
//		setContentView(R.layout.activity_debug_menu);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String classlist = classes[position];
		
		try{
			Class theClass = Class.forName("com.example.newpracticeandroid."+classlist);
			Intent theIntent = new Intent(DebugMenuActivity.this, theClass);
			startActivity(theIntent);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.debug_menu, menu);
		return true;
	}

}
