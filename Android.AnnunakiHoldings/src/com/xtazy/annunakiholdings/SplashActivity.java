package com.xtazy.annunakiholdings;

import com.xtazy.annunakiholdings.util.SystemUiHider;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class SplashActivity extends Activity {
	MediaPlayer splashMusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
