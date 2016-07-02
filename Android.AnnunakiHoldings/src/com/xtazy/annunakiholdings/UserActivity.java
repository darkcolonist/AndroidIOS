package com.xtazy.annunakiholdings;

import com.xtazy.annunakiholdings.lib.DBHelper;
import com.xtazy.annunakiholdings.models.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserActivity extends Activity {
	String username;
	DBHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		db = new DBHelper(this);
		Bundle bundle = getIntent().getExtras();
		username = bundle.getString("username");
		
		setContentView(R.layout.activity_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
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
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		
		User user = db.getUser(username);
		TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
		TextView txtBalance = (TextView) findViewById(R.id.txtBalance);
		TextView txtFullName = (TextView) findViewById(R.id.txtFullName);
		
		txtUsername.setText(user.username);
		txtBalance.setText(user.balance);
		txtFullName.setText(user.firstName + " " + user.lastName);
	}
	
	public void btnTransferClick(View v){
		Bundle bundle = new Bundle();
		bundle.putString("username", username);
		Intent openActivity = new Intent(
				"android.intent.action.USERTRANSFER");
		openActivity.putExtras(bundle);
		startActivityForResult(openActivity, 1);
	}
	
	public void btnHistoryClick(View v){
		Bundle bundle = new Bundle();
		bundle.putString("username", username);
		Intent openActivity = new Intent(
				"android.intent.action.USERHISTORY");
		openActivity.putExtras(bundle);
		startActivityForResult(openActivity, 1);
	}
	
	public void btnLogoutClick(View v){
		this.finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = new Bundle();			
			bundle.putString("username", username);
			Intent refresh = new Intent(this, UserActivity.class);
			refresh.putExtras(bundle);
			startActivity(refresh);
			this.finish();
		}
	}
}
