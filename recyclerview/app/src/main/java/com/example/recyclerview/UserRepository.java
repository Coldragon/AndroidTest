package com.example.recyclerview;

import java.util.ArrayList;

public class UserRepository {
	ArrayList<User> mUsersList;

	public UserRepository() {
		mUsersList = new ArrayList<>();
	}

	public ArrayList<User> getUsersList() {
		return mUsersList;
	}

	public User generateNewRandomUser() {
		 User newUser = User.random();
		 mUsersList.add(newUser);
		 return newUser;
	}

	public void removeUser(User user) {
		mUsersList.remove(user);
	}
}