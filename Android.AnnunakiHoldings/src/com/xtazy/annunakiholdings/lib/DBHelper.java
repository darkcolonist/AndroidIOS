package com.xtazy.annunakiholdings.lib;

import java.util.ArrayList;
import java.util.List;

import com.xtazy.annunakiholdings.models.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 6;
 
    // Database Name
    private static final String DATABASE_NAME = "dbAnnunakiHoldings";
    
    // Tables
    private static final String TABLE_USERS = "t_users";
    private static final String TABLE_TRANSACTIONS = "t_transactions";
 
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	Log.v("xtazy.message", "initialized database schema!");
    	
        String statement = "CREATE TABLE `"+TABLE_USERS+"`("
                + "`id` INTEGER PRIMARY KEY," 
                + " `username` TEXT,"
                + " `password` TEXT,"
                + " `firstName` TEXT,"
                + " `lastName` TEXT,"
                + " `email` TEXT,"
                + " `gender` TEXT," 
                + " `role` TEXT,"
                + " `balance` TEXT"
                + ")";
        db.execSQL(statement);
        
        statement = "CREATE TABLE `"+TABLE_TRANSACTIONS+"`("
                + "`id` INTEGER PRIMARY KEY," 
                + " `from` INTEGER,"
                + " `to` INTEGER,"
                + " `amount` TEXT,"
                + " `date` TEXT" 
                + ")";
        db.execSQL(statement);
        
        setupDefaultUsers(db);
    }
 
    private void setupDefaultUsers(SQLiteDatabase db) {
    	String columns = "(username, password, firstName, lastName, email, gender, role, balance)";
    	String statement;
    	statement = "INSERT INTO `"+TABLE_USERS+"`"
		  + " " + columns
		  + " VALUES('admin', '2016', 'The True', 'Annunaki', 'cris@nms.ph', 'male', 'admin', '-1');";
		db.execSQL(statement);
		statement = "INSERT INTO `"+TABLE_USERS+"`"
		  + " " + columns
		  + " VALUES('acct1', '1122', 'Account 1', 'Holder', 'acct1@mailinator.com', 'male', 'user', '5000');";
		db.execSQL(statement);
		statement = "INSERT INTO `"+TABLE_USERS+"`"
		  + " " + columns
		  + " VALUES('acct2', '1122', 'Account 2', 'Holder', 'acct2@mailinator.com', 'female', 'user', '5000');";
		db.execSQL(statement);
		statement = "INSERT INTO `"+TABLE_USERS+"`"
		  + " " + columns
		  + " VALUES('acct3', '1122', 'Account 3', 'Holder', 'acct3@mailinator.com', 'female', 'user', '5000');";
		db.execSQL(statement);
	}

	// Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
 
        // Create tables again
        onCreate(db);
    }
    
    public void addUser(
    		String username,
    		String password,
    		String firstName,
    		String lastName,
    		String email,
    		String gender,
    		String role,
    		String balance) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("firstName", firstName);
        values.put("lastName", lastName);
        values.put("email", email);
        values.put("gender", gender);
        values.put("role", role);
        values.put("balance", balance);
     
        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }
    
    public User getUser(String username){
		User user = new User();
    	
    	String statement = "SELECT *"
    					 + " FROM " + TABLE_USERS 
    					 + " WHERE `username` = '" + username + "'"
    					 + " LIMIT 1"
    					 + ";";
    	
    	SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(statement, null);
        
        if(cursor.moveToFirst()){
        	do {
            	user.id = Integer.parseInt(cursor.getString(0));
            	user.username = cursor.getString(1);
            	user.password = cursor.getString(2);
            	user.firstName = cursor.getString(3);
            	user.lastName = cursor.getString(4);
            	user.email = cursor.getString(5);
            	user.gender = cursor.getString(6);
            	user.role = cursor.getString(7);
            } while (cursor.moveToNext());
        }
    	
    	return user;
    }
    
    public User getUser(String username, String password){
    	User user = new User();
    	
    	String statement = "SELECT *"
    					 + " FROM " + TABLE_USERS 
    					 + " WHERE `username` = '" + username + "'"
    					 + "   AND `password` = '" + password + "'"
    					 + " LIMIT 1"
    					 + ";";
    	
    	SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(statement, null);
        
        if(cursor.moveToFirst()){
        	do {
            	user.id = Integer.parseInt(cursor.getString(0));
            	user.username = cursor.getString(1);
            	user.password = cursor.getString(2);
            	user.firstName = cursor.getString(3);
            	user.lastName = cursor.getString(4);
            	user.email = cursor.getString(5);
            	user.gender = cursor.getString(6);
            	user.role = cursor.getString(7);
            } while (cursor.moveToNext());
        }
    	
    	return user;
    }
    
    // Getting All Contacts
    /**
     * 
     * @param last
     * @return
     */
    public List<User> getLast(int last) {
        List<User> users = new ArrayList<User>();
        // Select All Query
        String statement = "SELECT  * FROM " + TABLE_USERS + " LIMIT " + last;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(statement, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	User user = new User();
            	
            	user.id = Integer.parseInt(cursor.getString(0));
            	user.username = cursor.getString(1);
            	user.password = cursor.getString(2);
            	user.firstName = cursor.getString(3);
            	user.lastName = cursor.getString(4);
            	user.email = cursor.getString(5);
            	user.gender = cursor.getString(6);
            	user.role = cursor.getString(7);
            	
                // Adding contact to list
            	users.add(user);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return users;
    }

}
