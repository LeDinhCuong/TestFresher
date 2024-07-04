package com.demo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

  @GET("users")
  Call<List<User>> getUsers();

  @GET("users/{id}")
  Call<User> getUserDetail(@Path("id") int userId);

}
