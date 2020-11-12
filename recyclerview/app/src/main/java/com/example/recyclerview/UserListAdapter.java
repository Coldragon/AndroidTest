package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<ListUserViewHolder> {

	private List<User> users = new ArrayList<>();

	private final Listener callback;
	public interface Listener {
		void onClickDelete(User user);
	}

	public UserListAdapter(Listener callback) {
		this.callback = callback;
	}

	@NonNull
	@Override
	public ListUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		Context context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.activity_item_list, parent,false);
		return new ListUserViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ListUserViewHolder holder, int position) {
		holder.bind(users.get(position), callback);
	}

	@Override
	public int getItemCount() {
		return users.size();
	}

	public void updateList(List<User> newList) {
		DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new UserDiffCallback(newList, this.users));
		this.users = new ArrayList<>(newList);
		diffResult.dispatchUpdatesTo(this);
	}
}
