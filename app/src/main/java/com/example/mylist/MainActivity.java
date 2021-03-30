package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    private RecyclerView cardUser;
    private ListAdapter listAdapter;
    int mMargin = 30;
    SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPrefs = getPreferences(MODE_PRIVATE);

        initRecyclerView();
        savedJson();
        loadItem();
    }

    private void initRecyclerView(){
        cardUser = findViewById(R.id.rView);
        cardUser.setLayoutManager(new GridLayoutManager(this, 2));

        ListAdapter.OnUserClickListener onUserClickListener = new ListAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(User user) {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                intent.putExtra(UserProfile.USER_ID, user);
                startActivity(intent);
            }
        };

        listAdapter = new ListAdapter(onUserClickListener);
        cardUser.setAdapter(listAdapter);
        cardUser.addItemDecoration(new SpacesItemDecoration(mMargin));
    }

    private void savedJson(){
        Collection<User> user = getUsers();
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("Users", json);
        prefsEditor.apply();
    }

    private void loadItem(){
        Gson gson = new Gson();
        String json = myPrefs.getString("Users", "");
        Type type = new TypeToken<Collection<User>>() {}.getType();
        Collection<User> users = gson.fromJson(json, (Type) type);
        listAdapter.setItems(users);
    }

    private void deleteUser(){

    }

    private Collection<User> getUsers(){
        return Arrays.asList(
                new User("User1", "03.04.2000", "user1@user.com", R.drawable.user1),
                new User("User2", "04.05.2001", "user2@user.com", R.drawable.user2),
                new User("User3", "05.06.2002", "user3@user.com", R.drawable.user3),
                new User("User4", "06.07.2003", "user4@user.com", R.drawable.user4),
                new User("User5", "07.08.2004", "user5@user.com", R.drawable.user5),
                new User("User6", "08.09.2005", "user6@user.com", R.drawable.user6)
        );
    }
}