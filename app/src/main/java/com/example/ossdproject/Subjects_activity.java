package com.example.ossdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Subjects_activity extends AppCompatActivity {


    ListView subjectslist;
    String selectedsub;

    ArrayList<String> sub = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_activity);

        subjectslist=findViewById(R.id.subjectslist);

        sub=getIntent().getStringArrayListExtra("subjectslist");

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,sub);
        subjectslist.setAdapter(adapter);

        subjectslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedsub=parent.getItemAtPosition(position).toString();
                Intent i = new Intent(Subjects_activity.this,ratings_display.class);
                i.putExtra("sub",selectedsub);
                startActivity(i);
            }
        });
    }
}
