package com.example.sillyskunk.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.example.sillyskunk.lib.DBHelper;

public class Profiles extends DBHelper {

	public Profiles(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

}
