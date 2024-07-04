package com.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

  private UserViewModel userViewModel;
  private RecyclerView recyclerView;
  private UserAdapter userAdapter;
  private SwipeRefreshLayout swipeRefreshLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.recycler_view);
    swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    userViewModel.getUsers().observe(this, users -> {
      if (userAdapter == null) {
        userAdapter = new UserAdapter(users, this);
        recyclerView.setAdapter(userAdapter);
        userAdapter.setOnUserClickListener(position -> {
          User user = users.get(position);
          Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
          intent.putExtra("user_id", user.getId());
          startActivity(intent);
        });
      }else{
        userAdapter.notifyDataSetChanged();
      }
      swipeRefreshLayout.setRefreshing(false);
      Toast.makeText(this, "Data loaded successfully", Toast.LENGTH_SHORT).show();
    });

    swipeRefreshLayout.setOnRefreshListener(() -> {
      userViewModel.refreshUsers();
    });

    userViewModel.refreshUsers();
  }
}
