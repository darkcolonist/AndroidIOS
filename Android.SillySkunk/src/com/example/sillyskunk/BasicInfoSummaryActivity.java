package com.example.sillyskunk;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BasicInfoSummaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		setContentView(R.layout.activity_basic_info_summary);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.basic_info_summary, menu);
		return true;
	}

}