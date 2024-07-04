package com.demo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {
  private MutableLiveData<List<User>> users;
  private Repository repository;

  public UserViewModel() {
    repository = new Repository();
    users = new MutableLiveData<>();
    loadUsers();
  }

  public LiveData<List<User>> getUsers() {
    return users;
  }

  public void refreshUsers() {
    loadUsers();
  }

  private void loadUsers() {
    repository.getUsers().observeForever(newUsers -> {
      users.setValue(newUsers);
    });
  }
}
