package com.example.ossdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Aboutactivity extends AppCompatActivity {

    Button yourstaus;
    FirebaseDatabase database;
    TextView name,specialization,genderdisplay;
    FirebaseAuth auth;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutactivity);

        yourstaus=findViewById(R.id.yourstaus);
        name=findViewById(R.id.name);
        database = FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        pbar=findViewById(R.id.pbar);

        specialization=findViewById(R.id.spec);
        genderdisplay=findViewById(R.id.gender);

        DatabaseReference userref = database.getReference().child("users").child(auth.getCurrentUser().getUid());
        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pbar.setVisibility(View.VISIBLE);
                String uname = dataSnapshot.child("username").getValue().toString();
                String gender = dataSnapshot.child("gender").getValue().toString();
                String spec = dataSnapshot.child("Specialization").getValue().toString();
                genderdisplay.setText(gender);
                specialization.setText(spec);

                name.setText(uname);
                pbar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        yourstaus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Aboutactivity.this,yourstatusactivity.class));
            }
        });
    }
}
