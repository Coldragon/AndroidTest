package com.example.recyclerview.model;

import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class User {

	private String mId;
	private String mUsername;
	private String mAvatarUrl;

	private static List<User> FAKE_USERS = Arrays.asList(
			new User("001", "Jake", "https://i.pravatar.cc/512?img=1"),
			new User("002", "Paul", "https://i.pravatar.cc/512?img=2"),
			new User("003", "Phil", "https://i.pravatar.cc/512?img=3"),
			new User("004", "Guillaume", "https://i.pravatar.cc/512?img=4"),
			new User("005", "Francis", "https://i.pravatar.cc/512?img=5"),
			new User("006", "George", "https://i.pravatar.cc/512?img=6")
	);

	public User(String id, String login, String avatarUrl) {
		this.mId = id;
		this.mUsername = login;
		this.mAvatarUrl = avatarUrl;
	}

	public String getId() { return mId; }
	public String getUsername() { return mUsername; }
	public String getAvatarUrl() { return mAvatarUrl; }

	public static User random(){
		return FAKE_USERS.get( (int)(Math.random()* FAKE_USERS.size()));
	}

	@Override
	public boolean equals(@Nullable Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof User)) return false;
		return (((User) obj).mAvatarUrl == this.mAvatarUrl && ((User) obj).mUsername == this.mUsername);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mUsername, mAvatarUrl);
	}
}