package com.bernotsha.port2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class shortfilm extends AppCompatActivity {
    ImageView filmbefore,filmafter,filmphone;
    ImageButton filmhome;
    TextView flimwinner1,flimwinner2,flimwinner3;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortfilm);
        filmphone=findViewById(R.id.filmphone);
        filmhome = findViewById(R.id.filmhome);
        filmafter = findViewById(R.id.filmafter);
        filmbefore = findViewById(R.id.filmbefore);
        flimwinner1=findViewById(R.id.filmwinnner1);
        flimwinner2 = findViewById(R.id.filmwinner2);
        flimwinner3 = findViewById(R.id.filmwinner3);
        ref= FirebaseDatabase.getInstance().getReference().child("filmwinner");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.child("winner1").getValue().toString();
                flimwinner1.setText(value1);
                String value2 = dataSnapshot.child("winner2").getValue().toString();
                flimwinner2.setText(value2);
                String value3 = dataSnapshot.child("winner3").getValue().toString();
                flimwinner3.setText(value3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        filmbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),coding.class));
                finish();
            }
        });
        filmphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Calling to co-ordinator Rajiv", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(shortfilm.this,call.class);
                i.putExtra("key","8508110199");
                startActivity(i);
                finish();
            }
        });
        filmhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        filmafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),spot.class));
                finish();
            }
        });
    }
}
