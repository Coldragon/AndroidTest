package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	private RecyclerView recyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager layoutManager;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_item_list);
		recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
		recyclerView.setHasFixedSize(true);

		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);

		// specify an adapter (see also next example)
		mAdapter = new MyUserRecyclerViewAdapter();
		recyclerView.setAdapter(mAdapter);
	}
}