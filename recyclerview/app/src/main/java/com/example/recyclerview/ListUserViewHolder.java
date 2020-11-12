package com.example.recyclerview;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recyclerview.model.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListUserViewHolder extends RecyclerView.ViewHolder {

	private ImageView mImageViewAvatar;
	private TextView mTextViewUsername;
	private ImageButton mImageButtonDelete;

	public ListUserViewHolder(@NonNull View itemView) {
		super(itemView);
		mImageViewAvatar = itemView.findViewById(R.id.item_list_user_avatar);
		mTextViewUsername = itemView.findViewById(R.id.item_list_user_username);
		mImageButtonDelete = itemView.findViewById(R.id.item_list_user_delete_button);
	}

	public void bind(User user, UserListAdapter.Listener callback) {
		mTextViewUsername.setText(user.getUsername());
		mImageButtonDelete.setOnClickListener(view -> callback.onClickDelete(user));
		Glide.with(itemView.getContext())
			.load(user.getAvatarUrl())
			.apply(RequestOptions.circleCropTransform())
			.into(mImageViewAvatar);
	}
}
