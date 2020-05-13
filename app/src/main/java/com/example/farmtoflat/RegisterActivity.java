package com.example.farmtoflat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText mname, mphone, mflat;
    Spinner mapartment;
    EditText memail, mpassword, mrepassword;
    Button mcreateAccount;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }


        mname = findViewById(R.id.name);
        //mphone = findViewById(R.id.mobile);
        mflat = findViewById(R.id.flat);
        //  memail = findViewById(R.id.email);
        //  mpassword = findViewById(R.id.password);
        //  mrepassword = findViewById(R.id.reenterpassword);
        mcreateAccount = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        mapartment = (Spinner) findViewById(R.id.apartment);


        db = FirebaseFirestore.getInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.apartment, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapartment.setAdapter(adapter);


        mcreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String email = memail.getText().toString();
//                String password = mpassword.getText().toString();
//                String flat = mflat.getText().toString();
//                String email = memail.getText().toString();


//                if (!mpassword.getText().toString().equals(mrepassword.getText().toString())) {
//                    Toast.makeText(com.example.farmtoflat.RegisterActivity.this, "Password do no match", Toast.LENGTH_LONG).show();
//                    return;
//                }

                if (mname.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Name can't be empty", Toast.LENGTH_LONG).show();
                    return;
                } else if (mflat.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Flat can't be empty", Toast.LENGTH_LONG).show();
                    return;
//                } else if (mphone.getText().toString().isEmpty()) {
//                    Toast.makeText(RegisterActivity.this, "MobileNo. can't be empty", Toast.LENGTH_LONG).show();
//                    return;
//                } else if (memail.getText().toString().isEmpty()) {
//                    Toast.makeText(com.example.farmtoflat.RegisterActivity.this, "email can't be empty", Toast.LENGTH_LONG).show();
//                    return;
//                } else if (mpassword.getText().toString().isEmpty()) {
//                    Toast.makeText(com.example.farmtoflat.RegisterActivity.this, "Password can't be empty", Toast.LENGTH_LONG).show();
//                    return;
//                } else if (mrepassword.getText().toString().isEmpty()) {
//                    Toast.makeText(com.example.farmtoflat.RegisterActivity.this, "Re enter password can't be empty", Toast.LENGTH_LONG).show();
//                    return;
//                } else if (mrepassword.getText().toString().length() < 6) {
                    //                   Toast.makeText(com.example.farmtoflat.RegisterActivity.this, "Password should be of 6 characters minimum", Toast.LENGTH_LONG).show();
                } else {
                    Map<String, Object> user = new HashMap<>();
                    user.put("Name", mname.getText().toString());
                    user.put("Flat No.", mflat.getText().toString());
                    //user.put("Mobile", mphone.getText().toString());
                    user.put("Aparment", mapartment.toString());
                    //  user.put("Email", memail.getText().toString());

                    db.collection("Users").document(mAuth.getUid())
                            .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.i("Storing data....", "Successful");
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else
                                Log.i("Storing data...", "Failed");
                        }
                    });


                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    //intent.putExtra("phonenumber", phonenumber);
                    startActivity(intent);

//


                }
            }
        });
    }
}
