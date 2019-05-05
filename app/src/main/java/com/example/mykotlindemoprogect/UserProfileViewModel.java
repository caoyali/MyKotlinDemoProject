package com.example.mykotlindemoprogect;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class UserProfileViewModel extends ViewModel {
    private String userId;
    private LiveData<User> user;

    public void init(String userId) {
        this.userId = userId;
    }
    public LiveData<User> getUser() {
        return user;
    }
}
