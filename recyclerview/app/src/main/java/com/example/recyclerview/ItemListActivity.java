package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerview.model.User;
import com.example.recyclerview.model.UserRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemListActivity extends AppCompatActivity implements UserListAdapter.Listener {

	private RecyclerView mRecyclerView;
	private FloatingActionButton mFloatingActionButton;
	private UserListAdapter mUserListAdapter;
	private UserRepository mUserRepository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);

		mUserRepository = new UserRepository();

		// Floating Button
		mFloatingActionButton = findViewById(R.id.activity_list_user_fab);
		mFloatingActionButton.setOnClickListener(view -> {
			mUserRepository.generateNewRandomUser();
			loadData();
		});

		// Recycler View
		mRecyclerView = findViewById(R.id.activity_list_user_rv);
		mUserListAdapter = new UserListAdapter(this);
		mRecyclerView.setAdapter(mUserListAdapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadData();
	}

	private void loadData() {
		mUserListAdapter.updateList(mUserRepository.getUsersList());
	}

	@Override
	public void onClickDelete(User user) {
		mUserRepository.removeUser(user);
		loadData();
	}
}