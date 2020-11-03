package com.example.ossdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

public class ratings_display extends AppCompatActivity {

    FloatingActionButton rys;
    FirebaseAuth auth;
    String rating="2";
    FirebaseDatabase db;
    RecyclerView recyclerView;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings_display);

        final String sub = getIntent().getStringExtra("sub");
        db=FirebaseDatabase.getInstance();

        recyclerView=findViewById(R.id.ratingsrv);

        ref=db.getReference().child("subjects").child(sub);



        auth=FirebaseAuth.getInstance();
        rys=findViewById(R.id.rateyourself);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        rys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder a = new AlertDialog.Builder(ratings_display.this);
                a.setTitle("Select your rating in " + sub);
                final String[] ratingnum = {"1", "2", "3", "4", "5"};

                int checkedItem = 1;
                int citem = 1;
                a.setSingleChoiceItems(ratingnum, citem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rating = ratingnum[which];
                        /*switch (which){
                            case 0:
                                rating=Integer.parseInt(ratingnum[which]);
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;*/
                        Toast.makeText(ratings_display.this,rating,Toast.LENGTH_LONG).show();


                    }

                });
                a.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar s=Snackbar.make(findViewById(R.id.ratingsdisplaylaout),rating,Snackbar.LENGTH_LONG);
                        s.show();
                        final HashMap<String,Object> subrating = new HashMap<>();
                        subrating.put("rating",rating);
                        db.getReference().child("users").child(auth.getCurrentUser().getUid()).child("subjects").child(sub).setValue(subrating)

                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        db.getReference().child("subjects").child(sub).child(auth.getCurrentUser().getUid()).setValue(subrating);
                                        Toast.makeText(ratings_display.this, "rating updated thank you!", Toast.LENGTH_LONG).show();
                                        Log.d("subs","success");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("sub",e.toString());
                                Toast.makeText(ratings_display.this, e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });


                AlertDialog d = a.create();

                d.show();
            }

        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Ratings> options=new FirebaseRecyclerOptions.Builder<Ratings>().setQuery(ref,Ratings.class)
                .build();
       adapter a = new adapter(options);
       a.startListening();
       recyclerView.setAdapter(a);
    }
}


class adapter extends FirebaseRecyclerAdapter<Ratings,adapter.vholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapter(@NonNull FirebaseRecyclerOptions<Ratings> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull adapter.vholder holder, int position, @NonNull Ratings model) {
        holder.rating.setRating(Integer.parseInt(model.getRating()));
        String uid=getRef(position).getKey();
        FirebaseDatabase.getInstance().getReference().child("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @NonNull
    @Override
    public adapter.vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.statusview,parent,false);

        return new adapter.vholder(v);
    }



    class vholder extends RecyclerView.ViewHolder{
        TextView Name;
        TextView ton;
        RatingBar rating;
        TextView sem;

        public vholder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.subjectstatusrv);
            ton=itemView.findViewById(R.id.teaching_or_not);
            sem=itemView.findViewById(R.id.semstatusrv);
            rating=itemView.findViewById(R.id.ratingstatusrv);
        }
    }

}


