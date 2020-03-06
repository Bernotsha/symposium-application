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

public class blind extends AppCompatActivity {
    TextView blindwinner1,blindwinner2,blindwinner3;
    ImageView blindbefore,blindafter;
    ImageButton blindhome;
    ImageView blindphone;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blind);
        blindphone = findViewById(R.id.blindphone);
        blindhome = findViewById(R.id.blindhome);
        blindbefore = findViewById(R.id.blindbefore);
        blindafter = findViewById(R.id.blindafter);
        blindwinner1 = findViewById(R.id.blindwinnner1);
        blindwinner2 = findViewById(R.id.blindwinner2);
        blindwinner3 = findViewById(R.id.blindwinner3);
        ref = FirebaseDatabase.getInstance().getReference().child("blindwinner");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.child("winner1").getValue().toString();
                blindwinner1.setText(value1);
                String value2 = dataSnapshot.child("winner2").getValue().toString();
                blindwinner2.setText(value2);
                String value3 = dataSnapshot.child("winner3").getValue().toString();
                blindwinner3.setText(value3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        blindhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        blindbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),spot.class));
                finish();
            }
        });
        blindphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Calling to co-ordinator Aishwarya", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(blind.this,call.class);
                i.putExtra("key","9524813712");
                startActivity(i);
                finish();
            }
        });
        blindafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),photography.class));
                finish();
            }
        });
    }
}
