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

public class paper extends AppCompatActivity {
    ImageView paperbefore,paperafter,paperphone;
    ImageButton paperhome;
    TextView paperwinner1,paperwinner2,paperwinner3;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        paperphone=findViewById(R.id.paperphone);
        paperphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Calling to co-ordinator Avinash", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(paper.this,call.class);
                i.putExtra("key","9787912799");
                startActivity(i);
                finish();
            }
        });
        paperwinner1 = findViewById(R.id.paperwinnner1);
        paperwinner2 = findViewById(R.id.paperwinner2);
        paperwinner3 = findViewById(R.id.paperwinner3);
        ref = FirebaseDatabase.getInstance().getReference().child("paperwinner");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.child("winner1").getValue().toString();
                paperwinner1.setText(value1);
                String value2 = dataSnapshot.child("winner2").getValue().toString();
                paperwinner2.setText(value2);
                String value3 = dataSnapshot.child("winner3").getValue().toString();
                paperwinner3.setText(value3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        paperhome = findViewById(R.id.paperhome);
        paperafter = findViewById(R.id.paperafter);
        paperbefore = findViewById(R.id.paperbefore);
        paperhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        paperbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),sql.class));
                finish();
            }
        });
        paperafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
            }
        });
    }
}
