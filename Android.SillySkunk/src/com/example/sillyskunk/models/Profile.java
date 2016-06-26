package com.example.sillyskunk.models;

//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
//
//import com.example.sillyskunk.lib.DBHelper;

public class Profile{
	public int id;
	public String firstName;
	public String lastName;
	public String email;
	public String birthday;
	public String gender;
	
	public Profile(){
		// nothing to do here
	}
	
	public Profile(String firstName, String lastName, String email, String birthday, String gender){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
	}
}
