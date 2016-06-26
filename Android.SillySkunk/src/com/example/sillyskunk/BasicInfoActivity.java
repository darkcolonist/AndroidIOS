package com.example.sillyskunk;

import com.example.sillyskunk.lib.DBHelper;
import com.example.sillyskunk.models.Profile;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BasicInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.basic_info, menu);
		return true;
	}
	
	public void btnSubmitClick(View v){
		TextView txtFirstName = (TextView) findViewById(R.id.txtFirstName);
		TextView txtLastName = (TextView) findViewById(R.id.txtLastName);
		TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
		TextView txtBirthday = (TextView) findViewById(R.id.txtBirthday);
		
		boolean hasError = false;
		if(txtFirstName.getText().toString().isEmpty()){
			txtFirstName.setError("first name is required!");
			hasError = true;
		}else if(txtLastName.getText().toString().isEmpty()){
			txtLastName.setError("last name is required!");
			hasError = true;
		}else if(txtEmail.getText().toString().isEmpty()){
			txtEmail.setError("email is required!");
			hasError = true;
		}else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches()){
			txtEmail.setError("email is invalid!");
			hasError = true;
		}else if(txtBirthday.getText().toString().isEmpty()){
			txtBirthday.setError("birthday is required!");
			hasError = true;
		}
		
		if(hasError){
			Toast.makeText(this, "there are some errors found in your information, please review the information first!", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(this, "thank you, "+ txtFirstName.getText() +"!", Toast.LENGTH_LONG).show();
			
			Profile prof = new Profile(txtFirstName.getText().toString()
					, txtLastName.getText().toString()
					, txtEmail.getText().toString()
					, txtBirthday.getText().toString()
					, "male");
			
			loadNextScreen(prof);
		}
		
	}
	
	private void loadNextScreen(final Profile prof){
		final BasicInfoActivity theUltimateBasicInfoActivityHandler = this;
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openActivity = new Intent("com.example.sillyskunk.BASICINFOSUMMARY");
					
					Bundle bundle = new Bundle();
					
					bundle.putString("firstName", prof.firstName);
					bundle.putString("lastName", prof.lastName);
					bundle.putString("email", prof.email);
					bundle.putString("birthday", prof.birthday);
					bundle.putString("gender", prof.gender);
				
					DBHelper db = new DBHelper(theUltimateBasicInfoActivityHandler);
					db.addProfile(prof);
					
					openActivity.putExtras(bundle);
					startActivity(openActivity);
				}
			}
		};
		
		timer.start();
	}

}

