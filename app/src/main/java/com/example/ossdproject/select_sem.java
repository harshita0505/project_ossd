package com.example.ossdproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class select_sem extends AppCompatActivity {

    TextView branch;

    CardView firstsem,ses,ts,fs,fis,sis,sevs,es;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sem);

       final String s = getIntent().getStringExtra("branch");

        firstsem=findViewById(R.id.onesem);
        ses=findViewById(R.id.secondsem);
        ts=findViewById(R.id.thirdsem);
        fs=findViewById(R.id.fourthsem);
        fis=findViewById(R.id.fifthsem);
        sis=findViewById(R.id.sixthsem);
        sevs=findViewById(R.id.seventhsem);
        es=findViewById(R.id.eighthsem);



        firstsem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.equals("mee")){
                    ArrayList<String> sublistarray = new ArrayList<>();
                    sublistarray.add("CTPS");
                    sublistarray.add("physics");
                    sublistarray.add("maths");
                    sublistarray.add("CAD");
                    sublistarray.add("english");
                    Intent i = new Intent(select_sem.this,Subjects_activity.class);
                    i.putStringArrayListExtra("subjectslist",sublistarray);
                    startActivity(i);
                }
                if(s.equals("cse")){
                    ArrayList<String> sublistarray = new ArrayList<>();
                    sublistarray.add("cse11");
                    sublistarray.add("cse12");
                    sublistarray.add("cse13");
                    sublistarray.add("cse14");
                    sublistarray.add("cse15");
                    Intent i = new Intent(select_sem.this,Subjects_activity.class);
                    i.putStringArrayListExtra("subjectslist",sublistarray);
                    startActivity(i);
                }

            }
        });
        ses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.equals("mee")){
                    ArrayList<String> sublistarray = new ArrayList<>();
                    sublistarray.add("mech21");
                    sublistarray.add("mech22");
                    sublistarray.add("mechh23");
                    sublistarray.add("mech24");
                    Intent i = new Intent(select_sem.this,Subjects_activity.class);
                    i.putStringArrayListExtra("subjectslist",sublistarray);
                    startActivity(i);
                }
                if(s.equals("cse")) {
                    ArrayList<String> sublistarray = new ArrayList<>();
                    sublistarray.add("cse21");
                    sublistarray.add("cse22");
                    sublistarray.add("cse23");
                    sublistarray.add("cse24");
                    Intent i = new Intent(select_sem.this, Subjects_activity.class);
                    i.putStringArrayListExtra("subjectslist", sublistarray);
                    startActivity(i);
                }
                if(s.equals("eie")) {
                    ArrayList<String> sublistarray = new ArrayList<>();
                    sublistarray.add("eie21");
                    sublistarray.add("eie22");
                    sublistarray.add("eie23");
                    sublistarray.add("eie24");
                    Intent i = new Intent(select_sem.this, Subjects_activity.class);
                    i.putStringArrayListExtra("subjectslist", sublistarray);
                    startActivity(i);
                }
                if(s.equals("eee")) {
                    ArrayList<String> sublistarray = new ArrayList<>();
                    sublistarray.add("eee21");
                    sublistarray.add("eee22");
                    sublistarray.add("eee23");
                    sublistarray.add("eee24");
                    Intent i = new Intent(select_sem.this, Subjects_activity.class);
                    i.putStringArrayListExtra("subjectslist", sublistarray);
                    startActivity(i);
                }
            }
        });
        ts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.equals("eee")) {
                    ArrayList<String> sublistarray = new ArrayList<>();
                    sublistarray.add("eee31");
                    sublistarray.add("eee32");
                    sublistarray.add("eee33");
                    sublistarray.add("eee34");
                    Intent i = new Intent(select_sem.this, Subjects_activity.class);
                    i.putStringArrayListExtra("subjectslist", sublistarray);
                    startActivity(i);
                }

            }
        });



        //branch.setText(s);
    }
}
