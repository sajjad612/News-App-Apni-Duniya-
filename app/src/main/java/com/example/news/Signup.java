package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    Button BTNsignin;
    TextInputLayout fullname_var,username_var,email_var,phonenumber_var,password_var;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullname_var = findViewById(R.id.fullname_field);
        username_var = findViewById(R.id.username_field);
        email_var = findViewById(R.id.email_field);
        phonenumber_var = findViewById(R.id.phone_number_field);
        password_var = findViewById(R.id.password_field);


        BTNsignin = findViewById(R.id.btnSignIn);

        BTNsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend = new Intent(Signup.this, Login.class);
                startActivity(intend);
                finish();
            }
        });

    }

    public void registerbuttonclick(View view) {
        String fullname = fullname_var.getEditText().getText().toString();
        String username = username_var.getEditText().getText().toString();
        String email = email_var.getEditText().getText().toString();
        String phonenumber = phonenumber_var.getEditText().getText().toString();
        String password = password_var.getEditText().getText().toString();

        if (!fullname.isEmpty()){
            fullname_var.setError(null);
            fullname_var.setErrorEnabled(false);
            if (!username.isEmpty()){
                username_var.setError(null);
                username_var.setErrorEnabled(false);
                if (!email.isEmpty()){
                    email_var.setError(null);
                    email_var.setErrorEnabled(false);
                    if (!phonenumber.isEmpty()){
                        phonenumber_var.setError(null);
                        phonenumber_var.setErrorEnabled(false);
                        if (!password.isEmpty()){
                            password_var.setError(null);
                            password_var.setErrorEnabled(false);
                            if(!email.matches("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b")){

                                firebaseDatabase = FirebaseDatabase.getInstance();
                                reference = firebaseDatabase.getReference("datauser");

                                String fullname_ = fullname_var.getEditText().getText().toString();
                                String username_ = username_var.getEditText().getText().toString();
                                String email_ = email_var.getEditText().getText().toString();
                                String phonenumber_ = phonenumber_var.getEditText().getText().toString();
                                String password_ = password_var.getEditText().getText().toString();

                                storingdata storingdata = new storingdata(fullname_,username_,email_,phonenumber_,password_);

                                reference.child(username_).setValue(storingdata);

                                Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                                finish();

                            }else{
                                email_var.setError("Invalid Email");
                            }

                        }else{
                            password_var.setError("Please Enter Password");
                        }

                    }else{
                        phonenumber_var.setError("Please Enter Phone Number");
                    }

                }else{
                    email_var.setError("Please Enter Email");
                }

            }else{
                username_var.setError("Please Enter Username");
            }

        }else{
            fullname_var.setError("Please Enter your name");
        }
    }
}