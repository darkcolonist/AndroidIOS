package com.example.sillyskunk;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class BasicInfoSummaryActivity extends Activity {

	TextView txtWelcome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_basic_info_summary);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		
		Bundle bundle = getIntent().getExtras();
		
		txtWelcome = (TextView) findViewById(R.id.txtWelcome);
		
		txtWelcome.setText("welcome, "+ bundle.getString("firstName") +"!");
		
//		txtWelcome.setText("doryaaa!");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.basic_info_summary, menu);
		return true;
	}

}
