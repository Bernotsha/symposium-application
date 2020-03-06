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

public class coding extends AppCompatActivity {
    ImageButton codinghome;
    ImageView codingbefore, codingafter,codingphone;
    TextView codingwinner1,codingwinner2,codingwinner3;
    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding);
        codingphone = findViewById(R.id.codingphone);
        codinghome = findViewById(R.id.codinghome);
        codingafter = findViewById(R.id.codingafter);
        codingbefore = findViewById(R.id.codingbefore);
        codingwinner1 = findViewById(R.id.codingwinnner1);
        codingwinner2 = findViewById(R.id.codingwinner2);
        codingwinner3 = findViewById(R.id.codingwinner3);
        ref = FirebaseDatabase.getInstance().getReference().child("codingwinner");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.child("winner1").getValue().toString();
                codingwinner1.setText(value1);
                String value2 = dataSnapshot.child("winner2").getValue().toString();
                codingwinner2.setText(value2);
                String value3 = dataSnapshot.child("winner3").getValue().toString();
                codingwinner3.setText(value3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        codinghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        codingphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Calling to co-ordinator Aishwarya", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(coding.this,call.class);
                i.putExtra("key","9524813712");
                startActivity(i);
                finish();
            }
        });
        codingbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),kahoot.class));
                finish();
            }
        });
        codingafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),shortfilm.class));
                finish();
            }
        });
    }
}
