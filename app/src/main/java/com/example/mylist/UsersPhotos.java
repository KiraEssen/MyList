package com.example.mylist;

import android.os.Parcel;
import android.os.Parcelable;

public class UsersPhotos implements Parcelable{

    private String myURL;
    private String myTitle;

    public UsersPhotos(String url, String title){
        myURL = url;
        myTitle = title;
    }

    protected UsersPhotos(Parcel in) {
        myURL = in.readString();
        myTitle = in.readString();
    }

    public static final Creator<UsersPhotos> CREATOR = new Creator<UsersPhotos>() {
        @Override
        public UsersPhotos createFromParcel(Parcel in) {
            return new UsersPhotos(in);
        }

        @Override
        public UsersPhotos[] newArray(int size) {
            return new UsersPhotos[size];
        }
    };

    public static UsersPhotos[] getUsersPhotos (){
        return new UsersPhotos[]{
                new UsersPhotos("R.drawable.1", "person1"),
                new UsersPhotos("R.drawable.2", "person2"),
                new UsersPhotos("R.drawable.3", "person3"),
                new UsersPhotos("R.drawable.4", "person4"),
                new UsersPhotos("R.drawable.5", "person5"),
                new UsersPhotos("R.drawable.6", "person6"),
        };
    }

    public String getMyURL() {
        return myURL;
    }

    public void setMyURL(String myURL) {
        this.myURL = myURL;
    }

    public String getMyTitle() {
        return myTitle;
    }

    public void setMyTitle(String myTitle) {
        this.myTitle = myTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(myURL);
        dest.writeString(myTitle);
    }
}
