package com.demo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
  private ApiService apiService;

  public UserRepository(){
    apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
  }

  public LiveData<List<User>> getUsers(MutableLiveData<List<User>> usersLiveData){
    apiService.getUsers().enqueue(new Callback<List<User>>() {
      @Override
      public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()) {
          usersLiveData.setValue(response.body());

        }
      }
      @Override
      public void onFailure(Call<List<User>> call, Throwable t) {

      }
    });
    return usersLiveData;
  }

  public LiveData<User> getUserDetail(MutableLiveData<User> userLiveData, int userID){
    apiService.getUserDetail(userID).enqueue(new Callback<User>() {
      @Override
      public void onResponse(Call<User> call, Response<User> response) {
        if (response.isSuccessful()) {
          userLiveData.setValue(response.body());
        }
      }

      @Override
      public void onFailure(Call<User> call, Throwable t) {

      }
    });
    return userLiveData;
  }
}
