package com.example.sillyskunk;

import java.io.Console;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
			              implements OnClickListener{

	TextView yourname;
	Button touchme, clickme;
	ImageView img;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        touchme = (Button) findViewById(R.id.btn_touchme);
        clickme = (Button) findViewById(R.id.btn_clickme);
        yourname = (TextView) findViewById(R.id.txt_yourname);
        img = (ImageView) findViewById(R.id.img_holder);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void onClickMeClick(View v){
    	Toast.makeText(this, "kyaaa!~ "+yourname.getText()+" clicked me!", Toast.LENGTH_LONG).show();
    	img.setImageResource(R.drawable.cutegirl001);
    }

    public void onTouchMeClick(View v){
    	Toast.makeText(this, "kyaaaaaaa!~ "+yourname.getText()+" touched me!", Toast.LENGTH_LONG).show();
    	img.setImageResource(R.drawable.cutegirl002);
    }

    public void onGropeMeClick(View v){
    	Toast.makeText(this, "kimochiwarui! "+yourname.getText()+" groped me!", Toast.LENGTH_LONG).show();
    	img.setImageResource(R.drawable.cutegirl003);
    }
    
	@Override
	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch(v.getId()){
//		case 
//		}
	}
    
}
