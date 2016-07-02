package com.xtazy.annunakiholdings;

import com.xtazy.annunakiholdings.lib.DBHelper;
import com.xtazy.annunakiholdings.models.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AdminEditUserAcivity extends Activity {

	String username;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_edit_user_acivity);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_edit_user_acivity, menu);
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
		
		Bundle bundle = getIntent().getExtras();
		
		this.username = bundle.getString("username");
		
		DBHelper db = new DBHelper(this);
		
		User user = db.getUser(this.username);
		
		TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
		TextView txtPassword = (TextView) findViewById(R.id.txtPassword);
		TextView txtFirstName = (TextView) findViewById(R.id.txtFirstName);
		TextView txtLastName = (TextView) findViewById(R.id.txtLastName);
		TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
		TextView txtGender = (TextView) findViewById(R.id.txtGender);
		TextView txtBalance = (TextView) findViewById(R.id.txtBalance);
		Switch swtStatus = (Switch) findViewById(R.id.swtStatus);
		
		txtUsername.setText(user.username);
		txtPassword.setText(user.password);
		txtFirstName.setText(user.firstName);
		txtLastName.setText(user.lastName);
		txtEmail.setText(user.email);
		txtGender.setText(user.gender);
		txtBalance.setText(user.balance);
		
		if(user.status.equalsIgnoreCase("active")){
			swtStatus.setChecked(true);
		}else{
			swtStatus.setChecked(false);
		}
	}
	
	public void btnBackClick(View v){
		this.finish();
	}
	
	public void btnDeleteClick(View v){
		final AdminEditUserAcivity me = this;
		
		new AlertDialog.Builder(this)
		.setTitle("Confirm User Delete!")
		.setMessage("Do you really want to delete user? This process irreversible!")
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

		    public void onClick(DialogInterface dialog, int whichButton) {
		    	// delete user
		    	
		    	// then finish this activity
		    	setResult(RESULT_OK, null);
		    	me.finish();
//		        Toast.makeText(me, "Yaay", Toast.LENGTH_SHORT).show();
		    }})
		 .setNegativeButton(android.R.string.no, null).show();
	}
	
	public void btnSaveClick(View v){
		
	}
}
