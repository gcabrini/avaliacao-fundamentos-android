package com.example.administrador.myapplication.models.persistence;

import android.database.Cursor;

import com.example.administrador.myapplication.models.entities.User;

public class UserContract {
    public static final String TABLE = "user_service_order";
    public static final String ID = "id";
    public static final String USER = "username";
    public static final String PASSWORD = "password";

    public static final String[] COLUNS = {ID, USER, PASSWORD};

    public static String createTable() {
        final StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(USER + " TEXT, ");
        sql.append(PASSWORD + " TEXT ");
        sql.append(" ); ");
        return sql.toString();
    }

    public static String insertUser() {
        final StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + ", ");
        sql.append(USER + ", ");
        sql.append(PASSWORD);
        sql.append(" ) ");
        sql.append(" VALUES( ");
        sql.append(1 + ", ");
        sql.append("'kempow', 'kempow'");
        sql.append(" ) ");
        return sql.toString();
    }

    public static User bindUser(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setId((cursor.getInt(cursor.getColumnIndex(ID))));
            user.setUser(cursor.getString(cursor.getColumnIndex(USER)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
            return user;
        }
        return null;
    }
}
