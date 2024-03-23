package model;

import java.util.ArrayList;

public class Account {
	ArrayList<User> users = new ArrayList<User>();
	User currUser;
	
	public Account(ArrayList<User> users) {
		this.users = users;
	}

	
	public User getCurrUser() {
		return currUser;
	}


	public void setCurrUser(User currUser) {
		this.currUser = currUser;
	}


	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	// create new Account
	public void registerAccount(User user) {
		users.add(user);
	}
	
	// validasi input 
	// if true -> current user is logged in
	public Boolean loginAccount(String email, String password) {
		System.out.println("testing");
		for(User u: users) {
			System.out.println("masuk ke loop");
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				System.out.println("here");
				currUser = u;
				return true;
			}
		}
		return false;
	}
	
}
