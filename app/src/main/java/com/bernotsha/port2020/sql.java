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

public class sql extends AppCompatActivity {
    ImageButton sqlhome;
    ImageView sqlbefore,sqlafter,sqlphone;
    TextView sqlwinner1,sqlwinner2,sqlwinner3;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        sqlwinner1 = findViewById(R.id.sqlwinnner1);
        sqlwinner2=findViewById(R.id.sqlwinner2);
        sqlwinner3 = findViewById(R.id.sqlwinner3);
        sqlphone = findViewById(R.id.sqlphone);
        ref = FirebaseDatabase.getInstance().getReference().child("sqlwinner");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.child("winner1").getValue().toString();
                sqlwinner1.setText(value1);
                String value2 = dataSnapshot.child("winner2").getValue().toString();
                sqlwinner2.setText(value2);
                String value3 = dataSnapshot.child("winner3").getValue().toString();
                sqlwinner3.setText(value3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sqlhome = findViewById(R.id.sqlhome);
        sqlafter = findViewById(R.id.sqlafter);
        sqlbefore = findViewById(R.id.sqlbefore);
        sqlhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        sqlafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),paper.class));
                finish();
            }
        });
        sqlphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Calling to co-ordinator Rahul Hari", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(sql.this,call.class);
                i.putExtra("key","9791753527");
                startActivity(i);
                finish();
            }
        });
        sqlbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),hunt.class));
                finish();
            }
        });
    }
}
