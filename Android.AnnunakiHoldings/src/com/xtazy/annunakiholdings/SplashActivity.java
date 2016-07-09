package com.xtazy.annunakiholdings;

import com.firebase.client.Firebase;
import com.xtazy.annunakiholdings.util.SystemUiHider;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SplashActivity extends Activity {
	MediaPlayer splashMusic;
	
	protected void firebaseInit(){
		Firebase.setAndroidContext(this);
		final Firebase myFirebaseRef = new Firebase("https://annunakiholdings.firebaseio.com/");

		myFirebaseRef.child("launches").setValue("app launch detected ");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.firebaseInit();
		
		setContentView(R.layout.activity_splash);

		splashMusic = MediaPlayer.create(this, R.raw.win95);
		splashMusic.start();
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(10000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openActivity = new Intent("android.intent.action.LOGIN");
					startActivity(openActivity);
				}
			}
		};
		
		timer.start();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		splashMusic.release();
		finish();
	}
}
