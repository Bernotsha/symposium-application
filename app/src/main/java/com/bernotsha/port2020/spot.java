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
import com.shashank.sony.fancytoastlib.FancyToast;

public class spot extends AppCompatActivity {
    ImageButton spothome;
    TextView spotwinner1,spotwinner2,spotwinner3;
    ImageView spotbefore,spotafter,spotphone;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);
        spothome = findViewById(R.id.spothome);
        spotphone = findViewById(R.id.spotphone);
        spotbefore = findViewById(R.id.spotbefore);
        spotafter = findViewById(R.id.spotafter);
        spotwinner1 = findViewById(R.id.spotwinner1);
        spotwinner2= findViewById(R.id.spotwinner2);
        spotwinner3 = findViewById(R.id.spotwinner3);
        reff = FirebaseDatabase.getInstance().getReference().child("spotwinner");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.child("winner1").getValue().toString();
                spotwinner1.setText(value1);
                String value2 = dataSnapshot.child("winner2").getValue().toString();
                spotwinner2.setText(value2);
                String value3 = dataSnapshot.child("winner3").getValue().toString();
                spotwinner3.setText(value3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                FancyToast.makeText(spot.this,"Not Connected to internet",FancyToast.LENGTH_SHORT,FancyToast.INFO, false).show();

            }
        });
        spotbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),shortfilm.class));
                finish();
            }
        });
        spotphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Calling to co-ordinator Yukesh", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(spot.this,call.class);
                i.putExtra("key","9566719513");
                startActivity(i);
                finish();
            }
        });
        spothome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        spotafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),blind.class));
                finish();
            }
        });
    }
}
