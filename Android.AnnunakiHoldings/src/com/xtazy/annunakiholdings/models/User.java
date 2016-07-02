package com.xtazy.annunakiholdings.models;

public class User {
	public int id;
	
	public String username;
	public String password;
	public String firstName;
	public String lastName;
	public String email;
	public String gender;
	public String role; // user, admin
	public String balance;
	
	public User(){
		// nothing to do here
		this.id = -1;
	}
}
