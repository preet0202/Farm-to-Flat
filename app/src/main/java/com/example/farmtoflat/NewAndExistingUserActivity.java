package com.example.farmtoflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.farmtoflat.R;

public class NewAndExistingUserActivity extends AppCompatActivity {


    Button newUser,existingUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_and_existing_user);
        newUser = findViewById(R.id.new_user);
        existingUser = findViewById(R.id.existing_user);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewAndExistingUserActivity.this, VerificationActivity.class);
                startActivity(intent);
            }
        });

        existingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewAndExistingUserActivity.this, VerificationActivity.class);
                startActivity(intent);
            }
        });
    }
}