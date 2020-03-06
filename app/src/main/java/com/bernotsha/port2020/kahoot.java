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

public class kahoot extends AppCompatActivity {
    ImageView kahootafter,kahootbefore,kahootphone;
    TextView kahootwinner1,kahootwinner2,kahootwinner3;
    ImageButton kahoothome;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kahoot);
        kahootphone = findViewById(R.id.kahootphone);
        kahoothome = findViewById(R.id.kahoothome);
        kahootafter = findViewById(R.id.kahootafter);
        kahootwinner1 = findViewById(R.id.kahootwinnner1);
        kahootwinner2 = findViewById(R.id.kahootwinner2);
        kahootwinner3 = findViewById(R.id.kahootwinner3);

        kahootbefore = findViewById(R.id.kahootbefore);
        reff = FirebaseDatabase.getInstance().getReference().child("kahootwinner");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("winner1").getValue().toString();
                kahootwinner1.setText(value);
                String value2= dataSnapshot.child("winner2").getValue().toString();
                kahootwinner2.setText(value2);
                String value3 = dataSnapshot.child("winner3").getValue().toString();
                kahootwinner3.setText(value3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        kahootbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ignite.class));
                finish();
            }
        });
        kahootphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Calling to co-ordinator Meena", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(kahoot.this,call.class);
                i.putExtra("key","9751210169");
                startActivity(i);
                finish();
            }
        });
        kahootafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),coding.class));
                finish();
            }
        });
        kahoothome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
    }
}
