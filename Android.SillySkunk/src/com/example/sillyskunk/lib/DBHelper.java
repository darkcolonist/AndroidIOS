package com.example.sillyskunk.lib;

import java.util.ArrayList;
import java.util.List;

import com.example.sillyskunk.models.Profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "dbSillySkunk";
 
    // Contacts table name
    private static final String TABLE_PROFILES = "profiles";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "firstName";
    private static final String KEY_LASTNAME = "lastName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_BIRTHDAY = "birthday";
    private static final String KEY_GENDER = "gender";
 
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PROFILES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," 
                + KEY_FIRSTNAME + " TEXT,"
                + KEY_LASTNAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_BIRTHDAY + " TEXT,"
                + KEY_GENDER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
 
        // Create tables again
        onCreate(db);
    }
    
    public void addProfile(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, profile.firstName);
        values.put(KEY_LASTNAME, profile.lastName);
        values.put(KEY_EMAIL, profile.email);
        values.put(KEY_BIRTHDAY, profile.birthday);
        values.put(KEY_GENDER, profile.gender);
     
        // Inserting Row
        db.insert(TABLE_PROFILES, null, values);
        db.close(); // Closing database connection
    }
    
    // Getting All Contacts
    /**
     * 
     * @param last
     * @return
     */
    public List<Profile> getLast(int last) {
        List<Profile> profiles = new ArrayList<Profile>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PROFILES + " LIMIT " + last;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Profile profile = new Profile();
            	
            	profile.id = Integer.parseInt(cursor.getString(0));
            	profile.firstName = cursor.getString(1);
            	profile.lastName = cursor.getString(2);
            	profile.email = cursor.getString(3);
            	profile.birthday = cursor.getString(4);
            	profile.gender = cursor.getString(5);
            	
                // Adding contact to list
            	profiles.add(profile);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return profiles;
    }

}
