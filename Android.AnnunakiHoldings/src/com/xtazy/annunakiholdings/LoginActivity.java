package com.xtazy.annunakiholdings;

import com.xtazy.annunakiholdings.R;
import com.xtazy.annunakiholdings.lib.DBHelper;
import com.xtazy.annunakiholdings.models.User;

import android.app.Activity;
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
    	
    	Log.v("xtazy.message", "entered username: "+username);
    	Log.v("xtazy.message", "entered password: "+password);
    	
    	DBHelper db = new DBHelper(this);
    	User user = db.getUser(username, password);
    	
    	if(user.id == -1){
    		Toast.makeText(this, "invalid login credentials!", Toast.LENGTH_LONG).show();
    	}else{
    		Toast.makeText(this, "success!", Toast.LENGTH_LONG).show();
    	}
    		
    }
}
