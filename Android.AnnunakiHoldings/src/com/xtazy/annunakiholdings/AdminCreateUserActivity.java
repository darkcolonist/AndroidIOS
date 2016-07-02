package com.xtazy.annunakiholdings;

import com.xtazy.annunakiholdings.lib.DBHelper;
import com.xtazy.annunakiholdings.models.User;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AdminCreateUserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_create_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_create_user, menu);
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
	
	public void btnSaveClick(View v){
		DBHelper db = new DBHelper(this);
		TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
		TextView txtPassword = (TextView) findViewById(R.id.txtPassword);
		
		String username = txtUsername.getText().toString();
		String password = txtPassword.getText().toString();
		String firstName = ((TextView) findViewById(R.id.txtFirstName)).getText().toString();
		String lastName = ((TextView) findViewById(R.id.txtLastName)).getText().toString();
		String email = ((TextView) findViewById(R.id.txtEmail)).getText().toString();
		String gender = ((TextView) findViewById(R.id.txtGender)).getText().toString();
		String balance = ((TextView) findViewById(R.id.txtBalance)).getText().toString();
		String role = "user"; // because admin cannot create other admins!
		
		boolean hasError = false;
		
		if(username.isEmpty()){
			hasError = true;
			txtUsername.setError("field cannot be empty!");
		}else if(password.isEmpty()){
			hasError = true;
			txtPassword.setError("field cannot be empty!");
		}
		
		if(!username.isEmpty()){
			User user = db.getUser(username);
			
			if(user.id != -1){
				hasError = true;
				txtUsername.setError("this username is already taken!");
			}
		}
		
		if(!hasError){			
			
			db.addUser(username, password, firstName, lastName, email, gender, role, balance);
			
			Toast.makeText(this, "new account: "+username, Toast.LENGTH_SHORT).show();
			this.finish();
		}
	}
	
	public void btnBackClick(View v){
		this.finish();
	}
}
