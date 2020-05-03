package com.haphap.movieticketapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.haphap.movieticketapp.database.AppDatabase;
import com.haphap.movieticketapp.database.UserDao;
import com.haphap.movieticketapp.model.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserDao userDao;

    public UserViewModel(@NonNull Application application) {
        super(application);

        AppDatabase appDatabase = AppDatabase.getInstance(application);
        userDao = appDatabase.userDao();
    }

    public Long inserUser(User user){
        return userDao.insertUser(user);
    }

    public List<User> getallUsers(){
        return userDao.getAllUsers();
    }

}
