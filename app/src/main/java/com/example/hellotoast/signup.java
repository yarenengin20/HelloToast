package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    EditText signupName, signupEmail, signupUsername, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText= findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                database= FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name= signupName.getText().toString();
                String email= signupEmail.getText().toString();
                String username= signupUsername.getText().toString();
                String password= signupPassword.getText().toString();
                HelperClass helperClass= new HelperClass(name, email,username,password);
                reference.child(username).setValue(helperClass);
                Toast.makeText(signup.this, "You have successfully signed up!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(signup.this, loginpage.class);
                startActivity(intent);

            }

        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, loginpage.class);
                startActivity(intent);
            }
        });
    }


    public void cprofile(View view) {
        Intent intent = new Intent(this, createprofilepage.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id1006:
                Intent i = new Intent(this, addbookpage.class);
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}