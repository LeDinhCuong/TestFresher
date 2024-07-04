package com.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

  private UserViewModel userViewModel;
  private RecyclerView recyclerView;
  private UserAdapter userAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    userViewModel.getUsers().observe(this, users -> {
      userAdapter = new UserAdapter(users, this);
      recyclerView.setAdapter(userAdapter);
      userAdapter.setOnUserClickListener(position -> {
        User user = users.get(position);
        Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
        intent.putExtra("user_id", user.getId());
        startActivity(intent);
      });
    });

  }
}
