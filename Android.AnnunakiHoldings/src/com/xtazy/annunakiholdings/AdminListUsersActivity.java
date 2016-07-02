package com.xtazy.annunakiholdings;

import java.util.List;

import com.xtazy.annunakiholdings.lib.DBHelper;
import com.xtazy.annunakiholdings.models.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 
 * @tutorial 
 *           http://androidexample.com/Create_A_Simple_Listview_-_Android_Example
 *           /index.php?view=article_discription&aid=65&aaid=90
 * @author admin
 * 
 */
public class AdminListUsersActivity extends Activity {

	ListView lstUsers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_list_users);

		refreshList();
	}

	public void refreshList() {
		Log.v("xtazy.message", "list refreshed...");

		lstUsers = (ListView) findViewById(R.id.lstUsers);

		// Defined Array values to show in ListView
		String[] values = new String[] { "Android List View",
				"Adapter implementation", "Simple List View In Android",
				"Create List View Android", "Android Example",
				"List View Source Code", "List View Array Adapter",
				"Android Example List View"

				, "item 01", "item 02", "item 03", "item 04", "item 05",
				"item 06", "item 07", "item 08", "item 09", "item 10",
				"item 11", "item 12", "item 13", "item 14", "item 15",
				"item 16", "item 17", "item 18", "item 19", "item 20",
				"item 21", "item 22", "item 23", "item 24", "item 25",
				"item 26", "item 27" };

		DBHelper db = new DBHelper(this);
		values = db.getUsernames();

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		lstUsers.setAdapter(adapter);

		// ListView Item Click Listener
		lstUsers.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// ListView Clicked item index
				int itemPosition = position;

				// ListView Clicked item value
				String itemValue = (String) lstUsers
						.getItemAtPosition(position);

				// load activity: android.intent.action.ADMINEDITUSER
				Bundle bundle = new Bundle();
				bundle.putString("username", itemValue);
				Intent openActivity = new Intent(
						"android.intent.action.ADMINEDITUSER");
				openActivity.putExtras(bundle);
				startActivityForResult(openActivity, 1);
			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Intent refresh = new Intent(this, AdminListUsersActivity.class);
			startActivity(refresh);
			this.finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_list_users, menu);
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
