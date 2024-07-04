package com.demo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserDetailViewModel extends ViewModel {
  private MutableLiveData<User> user;
  private UserRepository userRepository;

  public UserDetailViewModel(){
    userRepository = new UserRepository();
  }

  public LiveData<User> getUser(int userId){
    if(user == null){
      user = new MutableLiveData<>();
      loadUser(userId);
    }
    return user;
  }

  private void loadUser(int userId){
    userRepository.getUserDetail(user, userId);
  }
}
