// UserRepository.java
package com.example.project_327929279_326566999_final.data;

import android.app.Application;

import com.example.project_327929279_326566999_final.data.dao.UserDao;
import com.example.project_327929279_326566999_final.data.db.AppDatabase;
import com.example.project_327929279_326566999_final.data.entities.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserRepository {
    private final UserDao userDao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
    }

    public void register(User user, Runnable onSuccess, Runnable onUserExists) {
        executor.execute(() -> {
            User existing = userDao.getUserByUsername(user.username);
            if (existing == null) {
                userDao.insert(user);
                onSuccess.run();
            } else {
                onUserExists.run();
            }
        });
    }

    public void login(String username, String password, Callback callback) {
        executor.execute(() -> {
            User user = userDao.login(username, password);
            callback.onResult(user);
        });
    }

    public interface Callback {
        void onResult(User user);
    }
}
