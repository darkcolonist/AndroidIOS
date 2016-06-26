package com.example.sillyskunk;

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
			
			UserInfo ui = new UserInfo(txtFirstName.getText().toString()
					, txtLastName.getText().toString()
					, txtEmail.getText().toString()
					, txtBirthday.getText().toString()
					, "male");
			
			loadNextScreen(ui);
		}
		
	}
	
	private void loadNextScreen(final UserInfo ui){		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openActivity = new Intent("com.example.sillyskunk.BASICINFOSUMMARY");
					
					Bundle bundle = new Bundle();
					
					bundle.putString("firstName", ui.firstName);
					bundle.putString("lastName", ui.lastName);
					bundle.putString("email", ui.email);
					bundle.putString("birthday", ui.birthday);
					bundle.putString("gender", ui.gender);
					
					openActivity.putExtras(bundle);
					startActivity(openActivity);
				}
			}
		};
		
		timer.start();
	}

}

class UserInfo{
	String firstName;
	String lastName;
	String email;
	String birthday;
	String gender;
	
	public UserInfo(String firstName, String lastName, String email, String birthday, String gender){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
	}
}

