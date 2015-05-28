package com.example.administrador.myapplication.models.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.models.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

public class UserRepository {

    private static class Singleton {
        public static final UserRepository INSTANCE = new UserRepository();
    }

    private UserRepository() {
        super();
    }

    public static UserRepository getInstance() {
        return Singleton.INSTANCE;
    }

    public User getUser(String userName, String password) {
        String where = UserContract.USER + " = ? AND " + UserContract.PASSWORD + " = ? ";
        String[] args = {userName, password};

        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUNS, where, args, null, null, null);
        User user = UserContract.bindUser(cursor);
        db.close();
        helper.close();
        return user;
    }
}
