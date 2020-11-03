package com.example.ossdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
    }

    public void selectsem(View view){
        int id =view.getId();
        switch (id){
            case R.id.cse:
                Intent i = new Intent(MainActivity.this,select_sem.class);
                i.putExtra("branch","cse");
                startActivity(i);
                break;
            case R.id.mee:
                Intent i1 = new Intent(MainActivity.this,select_sem.class);
                i1.putExtra("branch","mee");
                startActivity(i1);
                break;
            case R.id.ece:
                Intent i2 = new Intent(MainActivity.this,select_sem.class);
                i2.putExtra("branch","ece");
                startActivity(i2);
                break;
            case R.id.eee:
                Intent i3 = new Intent(MainActivity.this,select_sem.class);
                i3.putExtra("branch","eee");
                startActivity(i3);
                break;
            case R.id.eie:
                Intent i4 = new Intent(MainActivity.this,select_sem.class);
                i4.putExtra("branch","eie");
                startActivity(i4);
                break;
        }
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_logout,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                startActivity(new Intent(MainActivity.this,Aboutactivity.class));
                break;
            case R.id.logout:
                auth.signOut();
                Intent i = new Intent(MainActivity.this,Loginactivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
        }
        return true;
    }


}
