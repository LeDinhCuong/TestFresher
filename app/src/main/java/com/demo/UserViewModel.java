package com.demo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {
  private MutableLiveData<List<User>> users;
  private UserRepository userRepository;

  public UserViewModel(){
    userRepository = new UserRepository();
  }

  public LiveData<List<User>> getUsers(){
    if(users == null){
      users = new MutableLiveData<>();
      loadUser();
    }
    return users;
  }

  private void loadUser(){
    userRepository.getUsers(users);
  }
}
