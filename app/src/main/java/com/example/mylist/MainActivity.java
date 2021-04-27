package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private RecyclerView cardUser;
    private ListAdapter listAdapter;
    private AppDatabase db;
    private UserDao userDao;
    private List<User> user;
    int mMargin = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = App.getInstance().getDatabase();
        userDao = db.userDao();

        initRecyclerView();
        loadItem();
    }

    private void initRecyclerView(){
        cardUser = findViewById(R.id.rView);
        cardUser.setLayoutManager(new GridLayoutManager(this, 2));

        ListAdapter.OnUserClickListener onUserClickListener = new ListAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(User user) {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                intent.putExtra(UserProfile.USER_LIST, user);
                startActivity(intent);
            }
        };

        listAdapter = new ListAdapter(onUserClickListener);
        cardUser.setAdapter(listAdapter);
        cardUser.addItemDecoration(new SpacesItemDecoration(mMargin));
    }

    private void loadItem(){
        user = userDao.getAll();
        Log.d("kira ", user.toString());
        if(user.size() == 0){
            generateUsers();
            user = userDao.getAll();
        }
        listAdapter.setItems(user);
        listAdapter.notifyDataSetChanged();
    }

    private void insertUser(String name, String dateOfBirth, String email, int icon){
        User user = new User(name, dateOfBirth, email, icon);
        userDao.insert(user);
    }

    private void generateUsers(){
        insertUser("User1", "03.04.2000", "user1@user.com", R.drawable.user1);
        insertUser("User2", "04.05.2001", "user2@user.com", R.drawable.user2);
        insertUser("User3", "05.06.2002", "user3@user.com", R.drawable.user3);
        insertUser("User4", "06.07.2003", "user4@user.com", R.drawable.user4);
        insertUser("User5", "07.08.2004", "user5@user.com", R.drawable.user5);
        insertUser("User6", "08.09.2005", "user6@user.com", R.drawable.user6);
    }

    public void deleteUser(int position) {
        User u = user.get(position);
        userDao.delete(u);
        user.remove(position);
        listAdapter.setItems(user);
        listAdapter.notifyDataSetChanged();
    }
}