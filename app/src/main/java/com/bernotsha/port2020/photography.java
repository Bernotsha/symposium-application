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

public class photography extends AppCompatActivity {
    ImageButton photographyhome;
    ImageView photographybefore, photographyafter,photographyphone;
    TextView photographywinner1,photographywinner2,photographywinner3;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography);
        photographywinner1 = findViewById(R.id.photographywinnner1);
        photographywinner2 = findViewById(R.id.photographywinner2);
        photographywinner3  = findViewById(R.id.photographywinner3);
        photographyhome = findViewById(R.id.photographyhome);
        photographybefore=findViewById(R.id.photographybefore);
        photographyafter=findViewById(R.id.photographyafter);
        photographyphone = findViewById(R.id.photographyphone);
        ref = FirebaseDatabase.getInstance().getReference().child("photographywinner");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.child("winner1").getValue().toString();
                photographywinner1.setText(value1);
                String value2 = dataSnapshot.child("winner2").getValue().toString();
                photographywinner2.setText(value2);
                String value3 = dataSnapshot.child("winner3").getValue().toString();
                photographywinner3.setText(value3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        photographyhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        photographyphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Calling to co-ordinator Rajiv", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(photography.this,call.class);
                i.putExtra("key","8508110199");
                startActivity(i);
                finish();
            }
        });
        photographyafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),hunt.class));
                finish();
            }
        });
        photographybefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),blind.class));
                finish();
            }
        });
    }
}
