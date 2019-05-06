package com.example.mykotlindemoprogect;

import Data.Dao.UserDao;
import Data.Dao.DataBase.UserDatabase;
import Data.Repository.UserRepository;
import Data.Dao.Subtype.User;
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

public class UserProfileViewModel extends ViewModel {
    private String userId;
    private LiveData<User> userLiveData;
    private UserRepository userRepository;

//    public UserProfileViewModel(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public UserProfileViewModel() {
    }

    public void init(int userId, Context context, InitFinishCallBack initFinishCallBack) {
        if (this.userLiveData != null) {
            // ViewModel is created on a per-Fragment basis, so the userId
            // doesn't change.
            return;
        }
        UserDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                UserDatabase.class, "database-name").build();
        UserDao userDao = db.userDao();
        userRepository = new UserRepository(userDao);
        userLiveData = userRepository.getUser(userId);
        initFinishCallBack.onInitFinish();
    }
    public LiveData<User> getUser() {
        return userLiveData;
    }

    public void changeUserName(User user){
        userRepository.changeuserName(user);
    }

    public interface InitFinishCallBack{
        void onInitFinish();
    }
}
