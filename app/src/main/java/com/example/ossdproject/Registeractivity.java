package com.example.ossdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registeractivity extends AppCompatActivity {
    Button signup;
    EditText un,regem,regpass,spec,confirmpassword;
    RadioGroup gender;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
        signup=findViewById(R.id.signup);
        un=findViewById(R.id.regname);
        confirmpassword=findViewById(R.id.confirmpassword);
        regem=findViewById(R.id.regemail);
        regpass=findViewById(R.id.regpassword);
        spec=findViewById(R.id.spec);
        gender=findViewById(R.id.gender);

        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String unamestr=un.getText().toString();
                final String emailstr=regem.getText().toString();
                final String passstr=regpass.getText().toString();
                final String specstr = spec.getText().toString();

                RadioButton g = findViewById( gender.getCheckedRadioButtonId());

                if(g==null)
                {
                    Toast.makeText(Registeractivity.this,"Select gender can not be empty",Toast.LENGTH_SHORT).show();
                    gender.requestFocus();
                }

                if(!passstr.equals(confirmpassword.getText().toString())){
                    Toast.makeText(Registeractivity.this,"Passwoprd and confirmpassword should match",Toast.LENGTH_SHORT).show();
                    confirmpassword.requestFocus();
                }




                if(TextUtils.isEmpty(unamestr))
                {
                    Toast.makeText(Registeractivity.this,"Username can not be empty",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(emailstr))
                {
                    Toast.makeText(Registeractivity.this,"Email can not be empty",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(passstr))
                {
                    Toast.makeText(Registeractivity.this,"Password can not be empty",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(specstr))
                {
                    Toast.makeText(Registeractivity.this,"Password can not be empty",Toast.LENGTH_SHORT).show();
                }
                else {

                    final String genderstr = g.getText().toString();

                    auth.createUserWithEmailAndPassword(emailstr,passstr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            final Intent i = new Intent(Registeractivity.this,MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            String userid = auth.getCurrentUser().getUid();
                            //
                            DatabaseReference db = firebaseDatabase.getReference().child("users").child(userid);

                            users u = new users(unamestr,passstr,emailstr,genderstr,specstr);

                            Map<String,Object> user = new HashMap<>();
                            user.put("username",unamestr);
                            user.put("email",emailstr);
                            user.put("gender",genderstr);
                            user.put("Specialization",specstr);
                            user.put("password",passstr);

                            db.updateChildren(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Registeractivity.this,"Profile Saved",Toast.LENGTH_LONG).show();
                                    startActivity(i);
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("signuperror",e.toString());
                            Toast.makeText(Registeractivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });


                }

            }
        });




    }
}
