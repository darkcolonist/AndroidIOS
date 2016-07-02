package com.xtazy.annunakiholdings;

import com.xtazy.annunakiholdings.lib.DBHelper;
import com.xtazy.annunakiholdings.models.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class UserTransferActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_transfer);
		
		db = new DBHelper(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_transfer, menu);
		return true;
	}

	String username;
	DBHelper db;
	User user;	
	TextView balance;
	TextView txtUsername;
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		
		Bundle bundle = getIntent().getExtras();
		username = bundle.getString("username");
		user = db.getUser(username);
		
		balance = (TextView) findViewById(R.id.txtBalance);
		txtUsername = (TextView) findViewById(R.id.txtUsername);
		
		balance.setText(user.balance);
		txtUsername.setHint("account name eg., "+username);
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
	
	public void btnTransferClick(View v){
		String fromUsername = username;
		String toUsername = "";
		String amount = "";
		
		TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
		TextView txtAmount = (TextView) findViewById(R.id.txtAmount);
		
		toUsername = txtUsername.getText().toString();
		amount = txtAmount.getText().toString();
		
		boolean hasError = false;
		if(toUsername.isEmpty()){
			txtUsername.setError("this field is required!");
			hasError = true;
		}
		
		if(amount.isEmpty()){
			txtAmount.setError("this field is required!");
			hasError = true;
		}
		
		Log.v("xtazy.message", "the amount is: "+amount);
		
		try{			
			
			if(!android.text.TextUtils.isDigitsOnly(amount) ||
					Double.parseDouble(amount) <= 0){
				txtAmount.setError("must be a positive non-zero number!");
				hasError = true;
			}
			
			if(Double.parseDouble(amount) > Double.parseDouble(user.balance)){
				txtAmount.setError("insufficient balance!");
				hasError = true;
			}
		}catch(NumberFormatException nfe){
			hasError = true;
			txtAmount.setError("numeric error occured!");
		}
		
		final User toUser = db.getUser(toUsername);
		
		if(toUsername.equalsIgnoreCase(fromUsername)){
			txtUsername.setError("you cannot transfer funds to your own account!");
			hasError = true;
		}
		
		if(toUsername.equalsIgnoreCase("aliens")){
			txtUsername.setError("the extraterrestrials frown on your insolence");
			Log.v("xtazy.message", "6.1");
			hasError = true;
		}
		
		if(toUser.id == -1){
			txtUsername.setError("username doesn't exist");
			Log.v("xtazy.message", "7.1");
			hasError = true;
		}
		
		if(hasError)
			return;
		
		final double theFinalAmount = Double.parseDouble(amount);
		final UserTransferActivity me = this;
		
		new AlertDialog.Builder(this)
		.setTitle("Confirm Transfer!")
		.setMessage("You will be transferring " + amount + " to " + toUser.username + ". Proceed?")
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

		    public void onClick(DialogInterface dialog, int whichButton) {
		    	// transfer funds
		    	db.transferFunds(user, toUser, theFinalAmount);
		    	
		    	// then finish this activity
		    	setResult(RESULT_OK, null);
		    	me.finish();
		    }})
		 .setNegativeButton(android.R.string.no, null).show();
	}
}
