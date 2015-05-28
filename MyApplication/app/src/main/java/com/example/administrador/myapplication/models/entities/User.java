package com.example.administrador.myapplication.models.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.models.persistence.UserRepository;

public class User implements Parcelable {

    private Integer mId;
    private String mUser;
    private String mPassword;

    public User() {
        super();
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        this.mUser = user;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public static User getUser(String name, String password) {
        return UserRepository.getInstance().getUser(name, password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mId);
        dest.writeString(this.mUser);
        dest.writeString(this.mPassword);
    }

    private User(Parcel in) {
        this.mId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mUser = in.readString();
        this.mPassword = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
