package com.example.mylist;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    private long userId;

    @ColumnInfo(name = "userName")
    private String name;

    @ColumnInfo(name = "dateOfBirth")
    private String dateOfBirth;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "myURL")
    private int myURL;

    public User(String name, String dateOfBirth, String email, int myURL) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.myURL = myURL;
    }

     protected User(Parcel in) {
        name = in.readString();
        dateOfBirth = in.readString();
        email = in.readString();
        myURL = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMyURL() {
        return myURL;
    }

    public void setMyURL(int myURL) {
        this.myURL = myURL;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(dateOfBirth);
        dest.writeString(email);
        dest.writeInt(myURL);
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}


