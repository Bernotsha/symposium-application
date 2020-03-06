package com.bernotsha.port2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ignite extends AppCompatActivity {
    ImageButton ignitehome;
    TextView ignite1,ignite2,ignite3;
    DatabaseReference reference;
    ImageView ignitebefore,igniteafter,ignitephone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ignite);
        ignitephone = findViewById(R.id.ignitephone);
        ignitehome=findViewById(R.id.ignitehome);
        ignite1=findViewById(R.id.ignitewinnner1);
        ignite2=findViewById(R.id.ignitewinner2);
        ignite3=findViewById(R.id.ignitewinner3);
        ignitebefore = findViewById(R.id.ignitebefore);
        igniteafter = findViewById(R.id.igniteafter);
        reference= FirebaseDatabase.getInstance().getReference().child("ignitewinners");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.child("winner1").getValue().toString();
                ignite1.setText(value1);
                String value2 = dataSnapshot.child("winner2").getValue().toString();
                ignite2.setText(value2);
                String value3 = dataSnapshot.child("winner3").getValue().toString();
                ignite3.setText(value3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ignite.this,"Error",Toast.LENGTH_LONG).show();

            }
        });
        ignitehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        ignitephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Calling to co-ordinator Rasika", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(ignite.this,call.class);
                i.putExtra("key","8248456049");
                startActivity(i);
                finish();
            }
        });
        igniteafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),kahoot.class));
                finish();
            }
        });
        ignitebefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),lunch.class));
                finish();
            }
        });





    }
}
