package com.example.task61dsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText fullName, email, phone, address, password;
    Button signup;
    DataBase dataBase;

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

        dataBase = new DataBase(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FullName = fullName.getText().toString();
                String Email = email.getText().toString();
                String Phone = phone.getText().toString();
                String Address = address.getText().toString();
                String Password = password.getText().toString();

                if(FullName.equals("") || Email.equals("") || Phone.equals("") || Address.equals("") || Password.equals("")){
                    Toast.makeText(Signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkUser = dataBase.checkUserName(Email);
                    if (checkUser == false){
                        Boolean insert = dataBase.insertData(FullName, Email, Phone, Address, Password);
                        if(insert == true){
                            Toast.makeText(Signup.this, "Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomePage.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Signup.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Signup.this, "User already exists. Please Login !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}