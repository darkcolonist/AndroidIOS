package com.xtazy.annunakiholdings.lib;

import java.math.BigDecimal;
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
    private static final int DATABASE_VERSION = 11;
 
    // Database Name
    private static final String DATABASE_NAME = "dbAnnunakiHoldings";
    
    // Tables
    private static final String TABLE_USERS = "t_users";
    private static final String TABLE_TRANSACTIONS = "t_transactions";
 
    /**
     * raw / native db handler
     */
    private SQLiteDatabase rdb;
    
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	rdb = db;
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
                + " `balance` TEXT,"
                + " `status` TEXT"
                + ")";
        db.execSQL(statement);
        
        statement = "CREATE TABLE `"+TABLE_TRANSACTIONS+"`("
                + "`id` INTEGER PRIMARY KEY," 
                + " `uFrom` INTEGER,"
                + " `uTo` INTEGER,"
                + " `uAmount` TEXT,"
                + " `uDate` TEXT" 
                + ")";
        db.execSQL(statement);
        
        setupDefaultUsers(db);
    }
 
    private void setupDefaultUsers(SQLiteDatabase db) {
    	String columns = "(username, password, firstName, lastName, email, gender, role, balance, status)";
    	String statement;
    	statement = "INSERT INTO `"+TABLE_USERS+"`"
		  + " " + columns
		  + " VALUES('aliens', '2016', 'The True', 'Annunaki', 'cris@nms.ph', 'male', 'admin', '-1', 'active');";
		db.execSQL(statement);
		statement = "INSERT INTO `"+TABLE_USERS+"`"
		  + " " + columns
		  + " VALUES('acct1', '1122', 'Account 1', 'Holder', 'acct1@mailinator.com', 'male', 'user', '5000', 'active');";
		db.execSQL(statement);
		statement = "INSERT INTO `"+TABLE_USERS+"`"
		  + " " + columns
		  + " VALUES('acct2', '1122', 'Account 2', 'Holder', 'acct2@mailinator.com', 'female', 'user', '5000', 'active');";
		db.execSQL(statement);
		statement = "INSERT INTO `"+TABLE_USERS+"`"
		  + " " + columns
		  + " VALUES('acct3', '1122', 'Account 3', 'Holder', 'acct3@mailinator.com', 'female', 'user', '5000', 'active');";
		db.execSQL(statement);
		statement = "INSERT INTO `"+TABLE_USERS+"`"
		  + " " + columns
		  + " VALUES('acct4', '1122', 'Account 4', 'Holder', 'acct4@mailinator.com', 'female', 'user', '5000', 'inactive');";
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
    		String balance,
    		String status) {
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
        values.put("status", status);
     
        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }
    
    public void updateUser(
    		String username,
    		String password,
    		String firstName,
    		String lastName,
    		String email,
    		String gender,
    		String role,
    		String balance,
    		String status) {
        String statement = "UPDATE "+TABLE_USERS
        		+" SET"
        		+" `password`='"+password+"'"
        		+" ,`firstName`='"+firstName+"'"
        		+" ,`lastName`='"+lastName+"'"
        		+" ,`email`='"+email+"'"
        		+" ,`gender`='"+gender+"'"
        		+" ,`role`='"+role+"'"
        		+" ,`balance`='"+balance+"'"
        		+" ,`status`='"+status+"'"
        		+" WHERE `username`='"+username+"';";
        
        SQLiteDatabase db = this.getWritableDatabase();
    	db.execSQL(statement);
    }
    
    public void updateUser(User user){
    	String statement = "UPDATE "+TABLE_USERS
                +" SET"
                +" `password`='"+user.password+"'"
                +" ,`firstName`='"+user.firstName+"'"
                +" ,`lastName`='"+user.lastName+"'"
                +" ,`email`='"+user.email+"'"
                +" ,`gender`='"+user.gender+"'"
                +" ,`role`='"+user.role+"'"
                +" ,`balance`='"+user.balance+"'"
                +" ,`status`='"+user.status+"'"
                +" WHERE `username`='"+user.username+"';";
        
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(statement);
    }
    
    private User fetchUserFromCursor(Cursor cursor){
    	User user = new User();
    	
    	user.id = Integer.parseInt(cursor.getString(0));
    	user.username = cursor.getString(1);
    	user.password = cursor.getString(2);
    	user.firstName = cursor.getString(3);
    	user.lastName = cursor.getString(4);
    	user.email = cursor.getString(5);
    	user.gender = cursor.getString(6);
    	user.role = cursor.getString(7);
    	user.balance = cursor.getString(8);
    	user.status = cursor.getString(9);
    	
    	return user;
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
        		user = this.fetchUserFromCursor(cursor);
            } while (cursor.moveToNext());
        }
    	
    	return user;
    }
    
    public void deleteUser(String username){
    	String statement = "DELETE FROM " + TABLE_USERS 
				 + " WHERE `username` = '" + username + "';";
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.execSQL(statement);
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
        		user = this.fetchUserFromCursor(cursor);
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
    public List<User> getLastUsersByN(int last) {
        List<User> users = new ArrayList<User>();
        // Select All Query
        String statement = "SELECT  * FROM " + TABLE_USERS + " LIMIT " + last;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(statement, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	User user = new User();
            	
            	user = this.fetchUserFromCursor(cursor);
            	
                // Adding contact to list
            	users.add(user);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return users;
    }
    
    /**
     * will only fetch t_users of 'user' type, admins will not be fetched
     * @return
     */
    public List<User> getUsers(){
    	List<User> users = new ArrayList<User>();
        // Select All Query
        String statement = "SELECT * FROM " + TABLE_USERS + 
        		" WHERE `role` <> 'admin'" +
        		";";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(statement, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	User user = new User();
            	
            	user = this.fetchUserFromCursor(cursor);
            	
                // Adding contact to list
            	users.add(user);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return users;
    }
    
    public String[] getUsernames(){
    	List<String> usernames = new ArrayList<String>();
        // Select All Query
    	String statement = "SELECT * FROM " + TABLE_USERS + 
    			" WHERE `role` <> 'admin'" +
        		" ORDER BY `username`" +
        		";";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(statement, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	User user = new User();
            	
            	user = this.fetchUserFromCursor(cursor);
            	
                // Adding to list
            	usernames.add(user.username);
            } while (cursor.moveToNext());
        }
 
        String[] usernamesArr;
        
        if(usernames.size() == 0){
        	usernamesArr = new String[1];
        	usernames.add("no users yet");
        	usernames.toArray(usernamesArr);
        }else{        	
        	usernamesArr = new String[usernames.size()];
        	usernames.toArray(usernamesArr);
        }
        
        
        // return list
        return usernamesArr;
    }
    

    public String[] getTransactionStrArray(String username){
    	List<String> transactions = new ArrayList<String>();
        // Select All Query
    	String statement = "SELECT * FROM " + TABLE_TRANSACTIONS + 
    			" WHERE `uFrom` = '"+username+"'" +
    			"   OR `uTo` = '"+username+"'" +
        		" ORDER BY `uDate` DESC" +
        		";";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(statement, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	User user = new User();
            	
//            	+ "`id` INTEGER PRIMARY KEY," 0 
//                + " `uFrom` INTEGER,"       1
//                + " `uTo` INTEGER,"         2
//                + " `uAmount` TEXT,"        3
//                + " `uDate` TEXT"           4
            	
            	String from = cursor.getString(1);
            	String to = cursor.getString(2);
            	String amount = cursor.getString(3);
            	String date = cursor.getString(4);
            	
            	String rowString = "";
            	if(from.equalsIgnoreCase(username)){
            		rowString = "["+date+"] gave " + amount + " to " + to; 
            	}else{
            		rowString = "["+date+"] received " + amount + " from " + from;
            	}
            	
                // Adding to list
            	transactions.add(rowString);
            } while (cursor.moveToNext());
        }
 
        String[] transactionsArr;
        
        if(transactions.size() == 0){
        	transactionsArr = new String[1];
        	transactions.add("no transactions yet");
        	transactions.toArray(transactionsArr);
        }else{        	
        	transactionsArr = new String[transactions.size()];
        	transactions.toArray(transactionsArr);
        }
        
        
        // return list
        return transactionsArr;
    }
    
    public void addTransaction(
    		String from,
    		String to,
    		String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf = 
             new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);
        
        ContentValues values = new ContentValues();
        values.put("uFrom", from);
        values.put("uTo", to);
        values.put("uAmount", amount);
        values.put("uDate", currentTime);
     
        // Inserting Row
        db.insert(TABLE_TRANSACTIONS, null, values);
        db.close(); // Closing database connection
        
    }
    
    public void transferFunds(User from, User to, Double amount){
    	// compute
    	from.balance = String.valueOf(Double.parseDouble(from.balance) - amount);
    	to.balance = String.valueOf(Double.parseDouble(to.balance) + amount);
    	
    	// save users
    	this.updateUser(from);
    	this.updateUser(to);
    	
    	// save transaction
    	this.addTransaction(
    			from.username, 
    			to.username, 
    			String.valueOf(amount));
    	
    }

}
