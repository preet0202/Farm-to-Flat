package com.example.farmtoflat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.farmtoflat.R;

public class NewAndExistingUserActivity extends AppCompatActivity {

    Button existUser, newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_and_existing_user);

        existUser = findViewById(R.id.existing_user);
        newUser = findViewById(R.id.new_user);

        existUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewAndExistingUserActivity.this, "already a user", Toast.LENGTH_SHORT).show();
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewAndExistingUserActivity.this, "new user", Toast.LENGTH_SHORT).show();
            }
        });

    }
}