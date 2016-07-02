package com.xtazy.annunakiholdings;

import com.xtazy.annunakiholdings.R;
import com.xtazy.annunakiholdings.lib.DBHelper;
import com.xtazy.annunakiholdings.models.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.v("xtazy.message", "login activity!");
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    
    public void btnLoginClick(View v){
    	TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
    	TextView txtPassword = (TextView) findViewById(R.id.txtPassword);
        
    	String username = txtUsername.getText().toString();
    	String password = txtPassword.getText().toString();
    	
    	Boolean hasError = false;
    	if(username.isEmpty()){
    		txtUsername.setError("this field cannot be empty!");
    		hasError = true;
    	}
    	
    	if(password.isEmpty()){
    		txtPassword.setError("this field cannot be empty!");
    		hasError = true;
    	}
    	
    	if(hasError)
    		return;
    	
    	DBHelper db = new DBHelper(this);
    	User user = db.getUser(username, password);
    	
    	if(user.id == -1){
    		Toast.makeText(this, "invalid login credentials!", Toast.LENGTH_LONG).show();
    	}else if(!user.status.equalsIgnoreCase("active")){
    		Toast.makeText(this, "your account is disabled! you must reach out to The True Annunaki!", Toast.LENGTH_LONG).show();
    	}else{
    		txtUsername.setText("");
        	txtPassword.setText("");
    		
    		Toast.makeText(this, "welcome, "+user.firstName+" "+user.lastName+"!", Toast.LENGTH_LONG).show();
    		
    		Bundle bundle = new Bundle();			
			bundle.putLong("user_id", user.id);
    		
    		if(user.role.equalsIgnoreCase("admin")){
    			// android.intent.action.ADMIN
    			Intent openActivity = new Intent("android.intent.action.ADMIN");
				openActivity.putExtras(bundle);
				startActivity(openActivity);
				
				Log.v("xtazy.message", "loading the admin page..?");
    		}else if(user.role.equalsIgnoreCase("user")){
    			// android.intent.action.USER
    		}
    	}
    		
    }
}
