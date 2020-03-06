package com.bernotsha.port2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ml extends AppCompatActivity {
    ImageButton mlhome;
    ImageView mlbefore;
    ImageView mlafter;
    ImageView mlphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml);
        mlhome = findViewById(R.id.mlhome);
        mlbefore=findViewById(R.id.mlbefore);
        mlafter = findViewById(R.id.mlafter);
        mlphone = findViewById(R.id.mlphone);
        mlhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        mlbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        mlafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),android.class));
            }
        });
        mlphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ml.this,call.class);
                i.putExtra("key","9445820924");
                startActivity(i);
                finish();
            }
        });

    }
}
