package com.example.task61dsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    EditText fullName, email, phone, address, password;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullName = findViewById(R.id.txtName);
        email = findViewById(R.id.txtEmail);
        phone = findViewById(R.id.txtPhone);
        address = findViewById(R.id.txtAddress);
        password = findViewById(R.id.txtPassword);

        signup = findViewById(R.id.btnSignup);
    }
}