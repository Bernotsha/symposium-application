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

public class hunt extends AppCompatActivity {
    ImageView huntbefore,huntafter,huntphone;
    ImageButton hunthome;
    TextView huntwinner1,huntwinner2,huntwinner3;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunt);
        huntphone = findViewById(R.id.huntphone);
        huntwinner1 = findViewById(R.id.huntwinnner1);
        huntwinner2 = findViewById(R.id.huntwinner2);
        huntwinner3 = findViewById(R.id.huntwinner3);
        hunthome = findViewById(R.id.hunthome);
        huntbefore = findViewById(R.id.huntbefore);
        huntafter = findViewById(R.id.huntafter);
        ref = FirebaseDatabase.getInstance().getReference().child("huntwinner");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.child("winner1").getValue().toString();
                huntwinner1.setText(value1);
                String value2 = dataSnapshot.child("winner2").getValue().toString();
                huntwinner2.setText(value2);
                String value3 = dataSnapshot.child("winner3").getValue().toString();
                huntwinner3.setText(value3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        hunthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        huntphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Calling to co-ordinator Sundaravalli", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(hunt.this,call.class);
                i.putExtra("key","9787461711");
                startActivity(i);
                finish();
            }
        });
        huntafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),sql.class));
                finish();
            }
        });
        huntbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),photography.class));
                finish();
            }
        });
    }
}
