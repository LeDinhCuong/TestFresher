package com.demo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserDetailViewModel extends ViewModel {
  private MutableLiveData<User> user;
  private Repository repository;

  public UserDetailViewModel(){
    repository = new Repository();
  }

  public LiveData<User> getUser(int userId){
    if(user == null){
      user = new MutableLiveData<>();
      loadUser(userId);
    }
    return user;
  }

  public void refreshUser(int userId) {
    loadUser(userId);
  }
  private void loadUser(int userId) {
    repository.getUserDetail(userId).observeForever(newUser -> {
      user.setValue(newUser);
    });
  }
}
