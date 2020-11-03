package com.example.ossdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Loginactivity extends AppCompatActivity {

    EditText email,password;
    Button login;
    Button signuppage;
    FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);


        email=findViewById(R.id.email);
        signuppage=findViewById(R.id.signuppage);
        password = findViewById(R.id.password);
        login=findViewById(R.id.login);
        auth=FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty()){
                    email.setError("Email can not be empty");
                    email.requestFocus();
                }
                else if(password.getText().toString().isEmpty()){
                    password.setError("Password can not be empty");
                    password.requestFocus();
                }
                else {
                    auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent i =new Intent(Loginactivity.this,MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Loginactivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });
        signuppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginactivity.this,Registeractivity.class));
                finish();
            }
        });
    }
}
