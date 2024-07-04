package com.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class UserDetailActivity extends AppCompatActivity {
  private TextView name, location, bio, publicRepos, followers, following;
  private ImageView avatar;
  private UserDetailViewModel userDetailViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_detail);

    name = findViewById(R.id.user_name);
    location = findViewById(R.id.user_location);
    bio = findViewById(R.id.user_bio);
    publicRepos = findViewById(R.id.user_public_repo);
    followers = findViewById(R.id.user_followers);
    following = findViewById(R.id.user_following);
    avatar = findViewById(R.id.user_avatar);

    int userId = getIntent().getIntExtra("user_id", -1);

    userDetailViewModel = new ViewModelProvider(this).get(UserDetailViewModel.class);
    userDetailViewModel.getUser(userId).observe(this, user -> {
      if (user != null) {
        name.setText(user.getLogin());
        location.setText(user.getLocation());
        bio.setText(user.getBio());
        publicRepos.setText(String.valueOf(user.getPublic_repos()));
        followers.setText(String.valueOf(user.getFollowers()));
        following.setText(String.valueOf(user.getFollowing()));
        Glide.with(this).load(user.getAvatar_url()).circleCrop().into(avatar);
      }
    });
  }
}