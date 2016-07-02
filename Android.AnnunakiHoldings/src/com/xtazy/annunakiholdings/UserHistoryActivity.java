package com.xtazy.annunakiholdings;

import com.xtazy.annunakiholdings.lib.DBHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class UserHistoryActivity extends Activity {

	ListView lstTransactions;
	String username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_history);
		
		refreshList();
	}

	public void refreshList(){
		lstTransactions = (ListView) findViewById(R.id.lstTransactions);

		// Defined Array values to show in ListView
		DBHelper db = new DBHelper(this);
		Bundle bundle = getIntent().getExtras();
		username = bundle.getString("username");
		
		String[] values = db.getTransactionStrArray(username);

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		lstTransactions.setAdapter(adapter);

		// ListView Item Click Listener
		lstTransactions.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item index
				int itemPosition = position;

				// ListView Clicked item value
				String itemValue = (String) lstTransactions
						.getItemAtPosition(position);
//
//				// load activity: android.intent.action.ADMINEDITUSER
//				Bundle bundle = new Bundle();
//				bundle.putString("username", itemValue);
//				Intent openActivity = new Intent(
//						"android.intent.action.ADMINEDITUSER");
//				openActivity.putExtras(bundle);
//				startActivityForResult(openActivity, 1);
			}

		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_history, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
