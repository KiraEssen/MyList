package com.example.mylist;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class UserProfile extends AppCompatActivity {
    public static final String USER_LIST = "userList";
    private ImageView fullPhoto;
    private TextView userName;
    private TextView userDateOfBirth;
    private TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        Bundle arguments = getIntent().getExtras();

        fullPhoto = (ImageView) findViewById(R.id.fullPhoto);
        userName = (TextView) findViewById(R.id.userName);
        userDateOfBirth = (TextView) findViewById(R.id.userDateOfBirth);
        userEmail = (TextView) findViewById(R.id.userEmail);

        if(arguments!=null){
            User user = (User) arguments.get(USER_LIST);
            if(user!=null){
                userName.setText(user.getName());
                userDateOfBirth.setText(user.getDateOfBirth());
                userEmail.setText(user.getEmail());

                Glide.with(this)
                        .load(user.getMyURL())
                        .into(fullPhoto);
            }
        }
    }
}
