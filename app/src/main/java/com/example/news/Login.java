package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {

    TextView TVskip;
    Button BTNsignup,BTNlogin;
    TextInputLayout username_var,password_var;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BTNlogin = findViewById(R.id.btnLogin);
        BTNsignup = findViewById(R.id.btnSignUp);

        TVskip = findViewById(R.id.TVSkip);

        username_var = findViewById(R.id.username_text_field_design);
        password_var = findViewById(R.id.password_input_field);



        BTNlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String username = username_var.getEditText().getText().toString();
             String password = password_var.getEditText().getText().toString();

             if (!username.isEmpty()){
                 username_var.setError(null);
                 username_var.setErrorEnabled(false);
                 if (!password.isEmpty()){
                     password_var.setError(null);
                     password_var.setErrorEnabled(false);

                     final String username_data = username_var.getEditText().getText().toString();
                     final String password_data = password_var.getEditText().getText().toString();

                     FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                     DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");

                     Query check_username = databaseReference.orderByChild("username").equalTo(username_data);

                     check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                             if (snapshot.exists()){
                                 username_var.setError(null);
                                 username_var.setErrorEnabled(false);
                                 String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);
                                 if (passwordcheck.equals(password_data)){
                                     password_var.setError(null);
                                     password_var.setErrorEnabled(false);

                                     Toast.makeText(getApplicationContext(),"login Successfully",Toast.LENGTH_LONG).show();
                                     Intent intent = new Intent(getApplicationContext(),ChooseCategory.class);
                                     startActivity(intent);
                                     finish();

                                 }else{
                                     password_var.setError("Wrong Password");
                                 }

                             }else{
                                 username_var.setError("User does not Exist");
                             }


                         }

                         @Override
                         public void onCancelled(@NonNull @NotNull DatabaseError error) {

                         }
                     });

                 }else{
                     password_var.setError("Please Enter a Password");
                 }

             }else{
                 username_var.setError("please Enter a Username");
             }

            }
        });



        BTNsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend = new Intent(Login.this,Signup.class);
                startActivity(intend);
                finish();
            }
        });

        TVskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend = new Intent(Login.this,ChooseCategory.class);
                startActivity(intend);
            }
        });

    }


}